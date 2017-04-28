/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.service.impl.Memcache;
import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.handler.WebServiceHandler;

/**
 * TicketManager.   票据管理
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class TicketManager extends AbstractCache{
    
    private final static Memcache memcache = ctx.getBean(Memcache.class);
    
    /**
     * 获取jsapi_ticket
     * 
     * @param appid         平台的appid
     * @param appsecret     平台的secret
     * @param authAppid     授权方的appid
     * @return
     */
    public static String getJsapiTicket(String appid , String appsecret , String authAppid , boolean refresh) {
        String key = String.format(KeyConfig.JSSDK_key.JS_TICKET_KEY, authAppid);
        String value = memcache.getString(key);
        String jsapi_ticket = null;
        if(refresh || StringUtils.isBlank(value)){
            JsonNode node = WebServiceHandler.instance.getJsapiTicket(AuthManager.getAuthAccessToken(appid, appsecret, authAppid));
            jsapi_ticket = node.get("ticket").asText();
            memcache.setKV(key, jsapi_ticket , node.get("expires_in").asInt());
            
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("*******refresh******");
                LOGGER.debug("jsapi_ticket:{}",jsapi_ticket);
            }
        }
        return jsapi_ticket;
    }
    
    /**
     * 获取wx_card ticket
     * 
     * @param appid         平台的appid
     * @param appsecret     平台的secret
     * @param authAppid     授权方appid
     * @param refresh       是否强制刷新ticket
     * @return
     */
    public static String getCardTicket(String appid , String appsecret , String authAppid , boolean refresh) {
        String key = String.format(KeyConfig.JSSDK_key.CARD_TICKET_KEY, authAppid);
        String value = memcache.getString(key);
        if(refresh || StringUtils.isBlank(value)){
            JsonNode node = WebServiceHandler.instance.getCardTicket(AuthManager.getAuthAccessToken(appid, appsecret, authAppid));
            value = node.get("ticket").asText();
            memcache.setKV(key, value , node.get("expires_in").asInt());
            
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("*******refresh******");
                LOGGER.debug("card_ticket:{}",value);
            }
        }
        return value;
    }
    
    /**
     * 网页授权Oauth2.0 code换取access_token 
     * @param authAppid     授权方的appid
     * @param appid         平台的appid
     * @param appsecret     平台的appsecret
     * @param code          Oauth授权码
     * @return
     * @throws Exception
     */
//    public  OauthToken getOAuthToken(String authAppid , String appid, String appsecret,String code) throws Exception {
//        String oauth_access_token = memcache.getString(String.format(KeyConfig.JSSDK_key.JS_ACCESS_TOKEN , authAppid));
//        OauthToken oauthToken = null;
//        if(StringUtils.isBlank(oauth_access_token)){
//            oauthToken = _createOAuthToken(appid, appsecret,code);
//        }else{
//            oauthToken = JsonMapper.fromJsonString(oauth_access_token, OauthToken.class);
//        }
//        return oauthToken;
//        
//    }
    
    /**
     * 获取网页授权前置跳转时存入缓存中的自定义state
     * @param authAppid 授权方appId
     * @param key       自定义缓存key
     * @return
     */
    public static String getOauthState(String authAppid , String key) {
        return memcache.getString(String.format(KeyConfig.JSSDK_key.Oauth_State , authAppid , key));
    }
    
    /**
     * 网页授权前置跳转缓存自定义State
     *
     * @param authAppid 授权方appId
     * @param key       自定义缓存key
     */
    public static void setOauthState(String authAppid , String key , String value){
        memcache.setKV(String.format(KeyConfig.JSSDK_key.Oauth_State, authAppid , key), value, KeyConfig.MINUTE_10);
    }
}
