/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.config;

/**
 * MpConstant.  Common常量key
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class MpConstant {
    /**
     * APPID
     */
    public final static String APPID = "component.appid";
    /**
     * app.secret
     */
    public final static String APPSECRET = "component.appsecret";
    /**
     * app.token
     */
    public final static String APPTOKEN = "component.token";
    /**
     * oauth.url
     */
    public final static String OAUTH_URL = "component.oauth";
    /**
     * 密文
     */
    public final static String EncodingAESKey = "component.encodingkey";
    /**
     * 域名
     */
    public final static String DOMAIN = "domain";
    /**
     * 网页Oauth回调接口
     */
    public final static String CallBackOAuthUrl = "callback.oauth.url";
    /**
     * 授权后回调URI
     */
    public final static String CallBackLoginOauth = "callback.login.oauth";
    
    public static class Authorizer{
        public final static Integer AUTH_ACCEPT = 1;
        public final static Integer AUTH_CANCEL = 2;
    }
}
