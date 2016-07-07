/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

import com.rtmap.utils.string.UrlParser;

/**
 * WebServiceAPI.   网页服务API
 * <pre>
 * 1) 发起微信内网页授权的权限
 * 2) 授权后获取授权用户的基本信息（权限根据应用授权作用域scope的不同而不同：scope为snsapi_base时不弹出授权页面但只能获取用户openid）；scope为snsapi_userinfo时弹出授权页面但可通过openid拿到用户昵称、性别、所在地）
 * 3）代替公众号使用JS SDK
 * </pre>
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class WebServiceAPI {
    
    //网页授权OAuth2.0获取code
    private static final String GET_OAUTH_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";
    //网页授权OAuth2.0获取token
    private static final String GET_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s&component_access_token=%s";
    
    //网页授权OAuth2.0获取用户信息
    private static final String GET_OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    
    //JSAPI_TICKET
    private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=%s";
    
    /**
     * 网页授权OAuth2.0获取code
     * @param appId
     * @param redirectUrl
     * @param scope
     * @param state
     * @return
     */
    public static String getOAuthCodeUrl(String appid ,String redirectUrl ,String scope ,String state,String component_appid){
        return String.format(GET_OAUTH_CODE, appid, UrlParser.encode(redirectUrl), scope, state ,component_appid);
    }
    
    /**
     * 网页授权OAuth2.0获取token
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    public static String getOAuthTokenUrl(String appid ,String code ,String component_appid , String component_access_token){
        return String.format(GET_OAUTH_TOKEN, appid,code,component_appid , component_access_token);
    }
    
    /**
     * 网页授权OAuth2.0获取用户信息
     * @param token
     * @param openid
     * @return
     */
    public static String getOAuthUserinfoUrl(String token ,String openid){
        return String.format(GET_OAUTH_USERINFO, token, openid);
    }
    
    /**
     * JSAPI_TICKET
     * @param token
     * @return
     */
    public static String getJsapiTicketUrl(String token , String type){
        return String.format(JSAPI_TICKET, token , type);
    }
    
    /**
     * card ticket
     * @param token
     * @return
     */
    public static String getCardTicketUrl(String token , String type){
        return String.format(JSAPI_TICKET, token , type);
    }
}
