package com.rtmap.core.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.rtmap.core.cache.AccountManager;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.MpConstant;

/**
 * 
 * AbstractAction.  抽象Action
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月24日
 * @since 1.0.0
 */
public class AbstractAction extends AbstractJsonpResponseBodyAdvice{
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 全局返回码
	 */
	protected Map<String,String> codeMap ;
	/**
	 * 全局常量Map
	 */
	protected Map<String,String> commonMap ;
	
	/**
	 * Create a new BaseController.
	 * 
	 * @param queryParamNames
	 */
	public AbstractAction(String... queryParamNames) {
		super("callback");
	}
	
	/**
	 * 授权方的access_token
	 * 
	 * @return
	 */
	public String getAuthAccessToken(String authAppid){
	    String appid       = codeMap.get(MpConstant.APPID);
	    String appsecret   = codeMap.get(MpConstant.APPSECRET);
	    return AuthManager.getAuthAccessToken(appid, appsecret, authAppid);
	}
	
	/**
	 * 平台的access_token
	 * 
	 * @return
	 */
	public String getAccessToken(){
	    String appid       = codeMap.get(MpConstant.APPID);
	    String appsecret   = codeMap.get(MpConstant.APPSECRET);
	    return AuthManager.getAccessToken(appid, appsecret);
	}
	
	/**
	 * 授权方主键ID
	 * 
	 * @param authAppid
	 * @return
	 */
	public Integer getAuthId(String authAppid){
	    return AccountManager.getAuthId(authAppid);
	}
	
	
	
	public Map<String, String> getCodeMap() {
		return codeMap;
	}
	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}
	public Map<String, String> getCommonMap() {
		return commonMap;
	}
	public void setCommonMap(Map<String, String> commonMap) {
		this.commonMap = commonMap;
	}

}
