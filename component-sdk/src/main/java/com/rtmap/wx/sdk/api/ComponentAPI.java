package com.rtmap.wx.sdk.api;

/**
 * 
 * ComponentAPI.    公众平台授权相关API
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class ComponentAPI {
	
	//1. 获取第三方平台access_token
	private static final String ComponentToken = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	
	//2. 获取预授权码
	private static final String CreatePreAuthCode = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=%s";
	
	//3. 使用授权码换取公众号的授权信息
	private static final String QueryAuth = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%s";
	
	//4. 获取（刷新）授权公众号的令牌
	private static final String AuthorizerToken = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=%s";
	
	//5. 获取授权方的信息
	private static final String GetAuthorizerInfo = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=%s";
	
	//6、获取授权方的选项设置信息
	private static final String GetAuthorizerOption = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option?component_access_token=%s";
	
	//7、设置授权方的选项信息
	private static final String SetAuthorizerOption = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option?component_access_token=%s";
	
	//8、 公众号登录授权页面
	private static final String ComponentLoginPage = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";
	
	/***
	 * 获取第三方平台access_token
	 * 
	 * @return
	 */
	public static String getComponenttoken() {
		return ComponentToken;
	}
	
	/**
	 * 公众号登录授权页面
	 * @param appid
	 * @param pre_auth_code
	 * @param redirect_uri
	 * @return
	 */
	public static String getComponentloginpage(String appid , String pre_auth_code , String redirect_uri) {
		return String.format(ComponentLoginPage,appid , pre_auth_code , redirect_uri);
	}
	/**
	 * 生成预授权码pre_auth_code
	 * @param component_access_token
	 * @return
	 */
	public static String getCreatePreAuthCode(String component_access_token) {
		return String.format(CreatePreAuthCode,component_access_token);
	}
	
	/**
	 * 使用授权码换取公众号的授权信息
	 * @param component_access_token
	 * @return
	 */
	public static String getQueryauth(String component_access_token) {
		return String.format(QueryAuth,component_access_token);
	}
	
	/**
	 * 获取（刷新）授权公众号的令牌
	 * 
	 * @param component_access_token
	 * @return
	 */
	public static String getAuthorizertoken(String component_access_token) {
		return String.format(AuthorizerToken,component_access_token);
	}
	
	/**
	 * 获取授权方的信息
	 * @param component_access_token
	 * @return
	 */
	public static String getGetauthorizerinfo(String component_access_token) {
		return String.format(GetAuthorizerInfo,component_access_token);
	}
	
	/**
	 * 获取授权方的选项设置信息
	 * @param component_access_token
	 * @return
	 */
	public static String getGetauthorizeroption(String component_access_token) {
		return String.format(GetAuthorizerOption,component_access_token);
	}
	
	/**
	 * 公众号登录授权页面
	 * @param component_access_token
	 * @return
	 */
	public static String getSetauthorizeroption(String component_access_token) {
		return String.format(SetAuthorizerOption,component_access_token);
	}
	
}