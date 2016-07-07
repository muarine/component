package com.rtmap.core.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.domain.Authorizer;
import com.rtmap.core.service.impl.AuthService;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.utils.string.StreamUtil;
import com.rtmap.wx.sdk.api.ComponentAPI;
import com.rtmap.wx.sdk.model.InMsgParser;
import com.rtmap.wx.sdk.mp.in.*;
import com.rtmap.wx.sdk.sign.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * AuthAction. 平台授权相关请求处理
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
@ControllerAdvice
@RestController
@RequestMapping("/auth")
public class AuthAction extends AbstractAction {
	@Autowired
	private AuthService authService;
	
	/**
     * 登录授权跳转
     * 
     * @return
     */
    @RequestMapping("/jumpLogin")
    public ModelAndView jumpLogin(){
        String appid        = commonMap.get(MpConstant.APPID);
        String appsecret    = commonMap.get(MpConstant.APPSECRET);
        String accessToken  = AuthManager.getAccessToken(appid, appsecret);
        JsonNode node = RestfulTemplate
                                .INSTANCE
                                .postForObject(ComponentAPI.getCreatePreAuthCode(accessToken), "{\"component_appid\":\""+appid+"\"}", JsonNode.class);
        LOGGER.info("获取到预授权码:" + node.toString());
        ModelAndView mv = new ModelAndView("redirect:" + 
                                ComponentAPI.getComponentloginpage(
                                                commonMap.get(MpConstant.APPID), 
                                                node.get("pre_auth_code").asText(), 
                                                commonMap.get(MpConstant.CallBackLoginOauth)
                                                )
                                );
        return mv;
    }
    
    /**
     * 公众号授权回调地址
     * 
     * auth_code 授权码
     * expires_in 有效期
     * 
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @RequestMapping("/index")
    public ModelAndView CallbackAuthUrl(HttpServletRequest request , String auth_code , Integer expires_in) throws JsonParseException, JsonMappingException, IOException{
        LOGGER.info("授权码:{}" , auth_code);
        Authorizer authorizer = this.refreshAuthCode(auth_code);
        WebUtils.setSessionAttribute(request, "authorizer", authorizer);
        return new ModelAndView("redirect:/index");
    }

    /**
     * 刷新授权Code,更新授权方信息
     * @param auth_code     授权码
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    private Authorizer refreshAuthCode(String auth_code)
            throws IOException, JsonParseException, JsonMappingException {
        // 1. 授权码换取公众号的授权信息
        RestTemplate template = RestfulTemplate.INSTANCE;
        String accessToken  = this.getAccessToken();
        String postdata1     = "{\"component_appid\":\"" + commonMap.get(MpConstant.APPID) + "\",\"authorization_code\":\"" + auth_code + "\"}";
        JsonNode authorization_info_result = template.postForObject(ComponentAPI.getQueryauth(accessToken), postdata1, JsonNode.class);
        if(LOGGER.isDebugEnabled()) LOGGER.debug("请求数据:{} 响应数据:{}" , postdata1 , authorization_info_result.toString());
        // {"authorization_info": {"authorizer_appid": "wxf8b4f85f3a794e77",...
        JsonNode authorization_info        = authorization_info_result.get("authorization_info");
        Assert.notNull(authorization_info , "使用授权码换取公众号的接口调用凭据和授权信息失败，NULL");
        LOGGER.info("授权信息：{}" , authorization_info.toString());
        // 2. 检测数据有效性,并入库
        String authAppid        = authorization_info.get("authorizer_appid").asText();
        String authAccessToken  = authorization_info.get("authorizer_access_token").asText();
        String authRefreshToken = authorization_info.get("authorizer_refresh_token").asText();
        String funcInfo     = authorization_info.get("func_info").toString();
        String postdata2    = "{\"component_appid\":\"" + commonMap.get(MpConstant.APPID) + "\",\"authorizer_appid\":\"" + authAppid + "\"}";
        JsonNode authorizer_info_result    = template.postForObject(ComponentAPI.getGetauthorizerinfo(accessToken), postdata2, JsonNode.class);
        if(LOGGER.isDebugEnabled()) LOGGER.debug("请求数据:{} 响应数据:{}" , postdata2 , authorizer_info_result.toString());
        // {"authorizer_info": {"nick_name": "微信SDK Demo Special","head_img":... 
        JsonNode authorizer_info           = authorizer_info_result.get("authorizer_info");
        Assert.notNull(authorizer_info , "获取授权方的公众号帐号基本信息失败，NULL");
        String nick_name    = authorizer_info.get("nick_name").asText();
        String head_img     = authorizer_info.get("head_img").asText();
        String user_name    = authorizer_info.get("user_name").asText();
        String alias        = authorizer_info.get("alias").asText();
        Integer service_type_info   = authorizer_info.get("service_type_info").get("id").asInt();
        Integer verify_type_info    = authorizer_info.get("verify_type_info").get("id").asInt();
        String qrcode_url   = authorizer_info.get("qrcode_url") == null ? "":authorizer_info.get("qrcode_url").asText();
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType   = mapper.getTypeFactory().constructParametrizedType(ArrayList.class, null, JsonNode.class);
        List<JsonNode> list = mapper.readValue(funcInfo, javaType);
        Integer scope = 0;
        StringBuffer buffer = new StringBuffer();
        for (JsonNode jsonNode : list) {
            scope = jsonNode.get("funcscope_category").get("id").asInt();
            buffer.append(scope).append(",");
        }
        String funcscopeCategory = buffer.substring(0, buffer.length()-1);
        
        // 先查库是否有记录
        Authorizer old_authorizer = authService.getBaseInfo(authAppid);
        Authorizer authorizer = new Authorizer(authAppid, authRefreshToken, 
                                                nick_name, head_img, service_type_info, 
                                                verify_type_info, user_name, alias, 
                                                qrcode_url, funcscopeCategory, 1);
        if(null != old_authorizer){
            authorizer.setId(old_authorizer.getId());
            authorizer.setCreateTime(old_authorizer.getCreateTime());
            authService.update(authorizer);
        }else{
            authorizer.setCreateTime(new Date());
            authService.insert(authorizer);
        }
        // 4. 更新缓存
        AuthManager.setAuthorizerAT(authAppid , authAccessToken);
        AuthManager.setAuthorizerRT(authAppid , authRefreshToken);
        AuthManager.setAuthAppidLegal(authAppid , MpConstant.Authorizer.AUTH_ACCEPT);
        return authorizer;
    }
	
    /**
     * <pre>
     * 授权事件接收URL
     * 1) 接收ticket推送
     * 2) 取消授权推送
     * </pre>
     * 
     * @return
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request,String msg_signature , 
                        String timestamp , String nonce , String encrypt_type){
        
        try {
            String postdata = StreamUtil.bufferedReaderToString(request.getReader());
            // 初始化加密构造函数
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("msg_signature:{},timestamp:{},nonce:{},encrypt_type:{}" , msg_signature , timestamp , nonce , encrypt_type);
                LOGGER.debug("微信推送平台data:{}" , postdata);
            }
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(
                                                    commonMap.get(MpConstant.APPTOKEN), 
                                                    commonMap.get(MpConstant.EncodingAESKey), 
                                                    commonMap.get(MpConstant.APPID)
                                                    );
            // 解密
            String result =  crypt.decryptMsg(msg_signature, timestamp, nonce, postdata);
            LOGGER.info("notify_msg:{}" , result);
            ComponentNotify notify = InMsgParser.parseNotify(result);           // 获取发送的消息
            if(notify instanceof ComponentVerifyNotify){                        // 平台verify票据
                LOGGER.info("推送component_verify_ticket协议通知");
                ComponentVerifyNotify verifyNotify = (ComponentVerifyNotify) notify;
                AuthManager.setVerifyTicket(verifyNotify.getComponentVerifyTicket());
            }else if(notify instanceof ComponentUnAuthNotify){                  
                LOGGER.info("取消授权通知");
                ComponentUnAuthNotify unAuthNotify = (ComponentUnAuthNotify) notify;
                authService.cancelAuth(unAuthNotify.getAuthorizerAppid());
            }else if(notify instanceof ComponentUpdateAuthNotify){             
                LOGGER.info("授权更新通知");
                ComponentUpdateAuthNotify updateAuthNotify = (ComponentUpdateAuthNotify) notify;
                this.refreshAuthCode(updateAuthNotify.getAuthorizationCode());
            }else if(notify instanceof ComponentAuthNotify){                    
                LOGGER.info("授权成功通知");
                ComponentAuthNotify authNotify = (ComponentAuthNotify) notify;
                this.refreshAuthCode(authNotify.getAuthorizationCode());
            }else{
                LOGGER.error("无法解析的通知类型:{} , 请及时规避错误！" , notify.getInfoType());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
        
        return "success";
    }
}
