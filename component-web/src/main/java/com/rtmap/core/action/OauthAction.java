package com.rtmap.core.action;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.cache.TicketManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.utils.string.UrlParser;
import com.rtmap.wx.sdk.handler.WebServiceHandler;
import com.rtmap.wx.sdk.token.OauthToken;

/**
 * 
 * OauthAction. Oauth授权
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
@RestController
@ControllerAdvice
@RequestMapping("/oauth")
public class OauthAction extends AbstractAction{
	
	/**
	 * Oauth网页认证
	 * @Description: 
	 * @param request
	 * @param response
	 * @return void    
	 */
	@RequestMapping("/{authAppid}/snsapi_base")
	public ModelAndView OauthSnsapiBaseNew(@PathVariable("authAppid") String authAppid , HttpServletRequest request , String code , String state){
		try {
		    String component_appid        = commonMap.get(MpConstant.APPID);
		    String component_appsecret    = commonMap.get(MpConstant.APPSECRET);
		    
			OauthToken token = WebServiceHandler.instance.getOAuthToken( 
			                            authAppid,
                    					code,
                    					commonMap.get(MpConstant.APPID),
                    					AuthManager.getAccessToken(component_appid, component_appsecret)
                    				);
			state = this._commonParseState(authAppid , state , token);
			state = this._commonFilterUrl(state);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return new ModelAndView("redirect:" + state);
	}
	
	/**
	 * Oauth网页认证
	 * @Description: 
	 * @param request
	 * @param response
	 * @return void    http://www.baidu.com/?aaa=xxx
	 */
	@RequestMapping("/{authAppid}/snsapi_userinfo")
	public ModelAndView OauthUserinfo(@PathVariable("authAppid") String authAppid , HttpServletRequest request , String code , String state){
		try {
		    String component_appid        = commonMap.get(MpConstant.APPID);
            String component_appsecret    = commonMap.get(MpConstant.APPSECRET);
            
            OauthToken token = WebServiceHandler.instance.getOAuthUserinfo( 
                                        authAppid,
                                        code,
                                        commonMap.get(MpConstant.APPID),
                                        AuthManager.getAccessToken(component_appid, component_appsecret)
                                    );
			state = this._commonParseState(authAppid , state , token);
			// 数据埋点
			String nickname = token.getNickname();
			String headimgurl = token.getHeadimgurl();
			state = state + "&nickname=" + UrlParser.encode(UrlParser.encode(nickname)) + "&headimgurl=" + headimgurl;
			state = this._commonFilterUrl(state);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return new ModelAndView("redirect:" + state);
	}
	
	/** 
     * 解析state  json to Map
     * 
     * @param state
     * @return
     */
    @SuppressWarnings("unchecked")
    private String _commonParseState(String authAppid , String state , OauthToken token) {
        String value = TicketManager.getOauthState(authAppid , state);
        LOGGER.info("第一次握手-缓存key:{},value:{}",state,value);
        Map<String,String> newMap = JSONObject.parseObject(value, Map.class);
        Iterator<Entry<String,String>> it = newMap.entrySet().iterator();
        StringBuffer buffer = new StringBuffer("?openid=");
        buffer.append(token.getOpenid());
        String redirectURL = null;
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if(entry.getKey().equals("r")){
                redirectURL = entry.getValue();
            }else{
                buffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        redirectURL = redirectURL + buffer.toString();
        LOGGER.info("r:{}", redirectURL);
        return redirectURL;
    }
	
	/**
	 * 
	 * http:// 检测是否有前缀
	 * 替换多个?的特殊字符
	 * 
	 * @param state
	 * @return
	 */
	private String _commonFilterUrl(String state) {
		// 检测是否含有http://
		if(!state.startsWith("http://")&& !state.startsWith("https://")){
			state = "http://" + state;
		}
		// 检测是否有多个?
		int count = org.apache.commons.lang.StringUtils.countMatches(state, "?");
		if(count > 1){
			// 第一个?左边的字符串
			String temp1 = state.substring(0,state.indexOf("?")+1);
			// 第一个?右边的字符串
			String temp2 = state.substring(state.indexOf("?")+1);
			temp2 = temp2.replaceAll("\\?", "&");
			state = temp1 + temp2;
		}
		return state;
	}
	
}
