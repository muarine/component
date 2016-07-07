package com.rtmap.core.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.config.ResponseFactory;
import com.rtmap.core.service.impl.MessageHandler;
import com.rtmap.core.vo.Code;
import com.rtmap.core.vo.Result;
import com.rtmap.utils.string.StreamUtil;
import com.rtmap.wx.sdk.model.InMsgParser;
import com.rtmap.wx.sdk.mp.in.InMsg;
import com.rtmap.wx.sdk.sign.Sign;
import com.rtmap.wx.sdk.sign.WXBizMsgCrypt;
/**
 * 微信服务器与开发者服务器之间的认证，消息收发
 * 
 * @author maoyun@rtmap.com
 *
 */
@ControllerAdvice
@RestController
@RequestMapping("/mp")
public class MpAction extends AbstractAction {
	private final Logger log = LoggerFactory.getLogger(MpAction.class);
	@Autowired
	private MessageHandler factoryService;
	
	/**
	 * <pre>
	 * GET请求：回调地址Token 认证，认证成功往后微信也会不定期请求此接口
	 * 1) 将token、timestamp、nonce三个参数进行字典序排序
	 * 2) 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 3) 这里可以添加多个账户，不同的{account}/message，提供给微信平台，只要验证通过即可
	 * </pre>
	 * @param appid		微信公众号APPID
	 * @param request	请求request
	 * @return
	 */
	@RequestMapping(value = "/{authAppid}/message", method = RequestMethod.GET)
	public String doGet(@PathVariable("authAppid") String authAppid , HttpServletRequest request) {
		String token      = commonMap.get(MpConstant.APPTOKEN);		                // 获取token，进行验证；
		String signature  = request.getParameter("signature");	// 微信加密签名
		String timestamp  = request.getParameter("timestamp");	// 时间戳
		String nonce      = request.getParameter("nonce");			// 随机数
		String echostr    = request.getParameter("echostr");		// 随机字符串
		// 校验成功返回 echostr，成功成为开发者；否则返回error，接入失败
		if (Sign.signature(signature, token, timestamp, nonce)) {
			return echostr;
		}
		log.error("sign error");
		return "error";
	}

	/**
	 * POST请求：微信消息推送入口
	 * @param authAppid			公众号APPID
	 * @param request			请求request
	 * @param msg_signature		签名
	 * @param timestamp			时间戳
	 * @param nonce				随机数
	 * @param encrypt_type		加密方式
	 * @return
	 */
	@RequestMapping(value = "/{authAppid}/message", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doPost(@PathVariable("authAppid") String authAppid , HttpServletRequest request,
						String msg_signature , String timestamp , String nonce , String encrypt_type) {
		try {
			String appid         = commonMap.get(MpConstant.APPID);
			String appsecret     = commonMap.get(MpConstant.APPSECRET);
			String apptoken      = commonMap.get(MpConstant.APPTOKEN);
			String encodingAESKey = commonMap.get(MpConstant.EncodingAESKey);
			String postdata      = StreamUtil.bufferedReaderToString(request.getReader());
			// 初始化加密构造函数
			WXBizMsgCrypt crypt = new WXBizMsgCrypt(apptoken , encodingAESKey , appid);
			// 解密
			String result =  crypt.decryptMsg(msg_signature, timestamp, nonce, postdata);
			if(log.isDebugEnabled()){
				log.debug("微信消息推送：{}",result);
			}
			InMsg inMsg = InMsgParser.parse(result);// 获取发送的消息
			return factoryService.processMsg(appid , appsecret , inMsg , authAppid , apptoken , encodingAESKey);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "error";
		}
	}
	
	/**
	 * 获取授权方access_token接口
	 **/
	@RequestMapping(value = "/{authAppid}/token")
	public Result authAccessToken(@PathVariable("authAppid") String authAppid){
	    // 检测appid是否已授权
	    JSONObject data        = new JSONObject();
	    String appid           = commonMap.get(MpConstant.APPID);
        String appsecret       = commonMap.get(MpConstant.APPSECRET);
	    if(!AuthManager.isLegal(authAppid)){
	        return ResponseFactory.build(Code.Account.INVAILD_APPID);
	    }else{
	        String auth_access_token = AuthManager.getAuthAccessToken(appid , appsecret , authAppid);
	        data.put("auth_access_token", auth_access_token);
	        data.put("auth_appid", authAppid);
	        return ResponseFactory.build(data);
	    }
	}
	
	/**
	 * 获取平台的access_token接口
	 **/
	@RequestMapping(value = "/component/token")
	public Result accessToken(){
	    // 检测appid是否已授权
	    JSONObject data        = new JSONObject();
	    String appid           = commonMap.get(MpConstant.APPID);
	    String appsecret       = commonMap.get(MpConstant.APPSECRET);
        String access_token = AuthManager.getAccessToken(appid , appsecret);
        data.put("access_token", access_token);
        return ResponseFactory.build(data);
	}

	/**
	 * 强制刷新授权方access_token接口
	 * 
	 **/
	@RequestMapping(value = "/{authAppid}/token/refresh")
	public Result refreshAuthAccessToken(@PathVariable("authAppid") String authAppid) {
	    try {
	        String appid        = commonMap.get(MpConstant.APPID);
            String appsecret    = commonMap.get(MpConstant.APPSECRET);
	        String auth_access_token = AuthManager.refreshAuthAccessToken(appid , appsecret , authAppid);
	        JSONObject json = new JSONObject();
	        json.put("auth_access_token", auth_access_token);
	        json.put("auth_appid", authAppid);
	        return ResponseFactory.build(json);
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        return ResponseFactory.build(Code.ERROR);
	    }
	}
	
	/**
	 * 强制刷新平台access_token
	 * 
	 **/
	@RequestMapping(value = "/component/token/refresh")
	public Result refreshComponentAccessToken() {
	    JSONObject data = new JSONObject();
	    try {
	        String appid       = commonMap.get(MpConstant.APPID);
	        String appsecret   = commonMap.get(MpConstant.APPSECRET);
	        String refresh_access_token = AuthManager.refreshAccessToken(appid , appsecret);
	        data.put("access_token", refresh_access_token);
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        return ResponseFactory.build(Code.ERROR);
	    }
	    return ResponseFactory.build(data);
	}

}
