/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.domain.Authorizer;
import com.rtmap.core.exp.Assert;
import com.rtmap.core.mapper.AuthorizerMapper;
import com.rtmap.core.service.impl.Memcache;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.api.ComponentAPI;

/**
 * AuthManager.      
 * <pre>
 * 1. 平台授权相关缓存
 * 2. 提供获取，刷新token函数
 * </pre>
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class AuthManager extends AbstractCache{
    
    private final static Memcache memcache = ctx.getBean(Memcache.class);
    
    /**
     * 获取平台access_token
     * @return
     */
    public static String getAccessToken(String appid , String appsecret){
        String value = memcache.getString(KeyConfig.Component_Key.Component_A_T);
        if(StringUtils.isBlank(value)){
            value = refreshAccessToken(appid , appsecret);
            if(LOGGER.isDebugEnabled()) LOGGER.debug("access_token已刷新");
        }
        if(LOGGER.isDebugEnabled()) LOGGER.debug("access_token:{}" , value);
        return value;
    }

    /**
     * 强制刷新平台的access_token
     * @param appid 平台appid
     * @return
     */
    public static String refreshAccessToken(String appid , String appsecret) {
        String value = null;
        String verifyTicket = memcache.getString(KeyConfig.Component_Key.Component_V_T);
        Assert.hasText(verifyTicket, "component_verify_ticket is required , it must not be empty");
        String postdata = "{\"component_appid\":\"" + appid + "\","
                            + "\"component_appsecret\": \"" + appsecret + "\","
                            + "\"component_verify_ticket\": \"" + verifyTicket + "\"}";
        JsonNode node = RestfulTemplate.INSTANCE
                                        .postForObject(ComponentAPI.getComponenttoken(), postdata , JsonNode.class);
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("******refresh******");
            LOGGER.debug("强制刷新平台的access_token request:{}" , postdata);
            LOGGER.debug("强制刷新平台的access_token response:{}", node.toString());
        }
        // {"errcode":61004,"errmsg":"access clientip is not registered hint: [OOcsrA0577oxy3] requestIP: 123.57.208.67"}
        value = node.get("component_access_token").asText();
        memcache.setKV(KeyConfig.Component_Key.Component_A_T , value , KeyConfig.HOUR_2);
        return value;
    }
    
    /**
     * 
     * 每十分钟微信服务器推送一次ticket,有效期：十分钟
     * 
     * @param verifyTicket
     */
    public static void setVerifyTicket(String verifyTicket){
        LOGGER.info("Component_V_T:{}",verifyTicket);
        memcache.setKV(KeyConfig.Component_Key.Component_V_T , 
                        verifyTicket , 
                        KeyConfig.MINUTE_10
                      ); 
    }
    
    /**
     * 设置授权方的缓存
     * @param authAppid
     * @param authAccessToken
     */
    public static void setAuthorizerAT(String authAppid , String authAccessToken) {
        LOGGER.info("Component_A{}_A_T:{}" , authAppid , authAccessToken);
        memcache.setKV(String.format(KeyConfig.Component_Key.Component_A_A_T, authAppid) , 
                        authAccessToken , 
                        KeyConfig.HOUR_2
                      ); // APPID_ACCESS_TOKEN
    }

    /**
     * 授权公众号信息
     * @param appid
     * @return
     */
    public static Authorizer getAuthorizer(String appid) {
        
        Authorizer authorizer = null;
        try {
            String value = memcache.getString(String.format(KeyConfig.Authorizer_key.INFO , appid));
            if(StringUtils.isBlank(value)){
                authorizer = JsonMapper.fromJsonString(value , Authorizer.class);
                return authorizer;
            }
            AuthorizerMapper authorizerMapper = ctx.getBean(AuthorizerMapper.class);
            authorizer = authorizerMapper.getBaseInfo(appid);
            String json = JsonMapper.toJsonString(authorizer);
            setAuthorizerInfo(appid,json);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
        return authorizer;
        
    }
    
    
    /**
     * 设置授权方用户信息
     * @param appid
     * @param json
     */
    private static void setAuthorizerInfo(String appid, String json) {
        memcache.setKV(String.format(
                                    KeyConfig.Authorizer_key.INFO , 
                                    appid 
                                    ) , json , KeyConfig.HOUR_2);
    }

    /**
     * 
     * 获取授权方access_token
     * 
     * @param authAppid 授权方appid
     * @return
     */
    public static String getAuthAccessToken(String appid , String appsecret , String authAppid){
        String value = memcache.getString(String.format(KeyConfig.Component_Key.Component_A_A_T, authAppid));
        if(StringUtils.isBlank(value)){
            value = refreshAuthAccessToken(appid , appsecret , authAppid);
        }
        return value;
    }

    /**
     * 强制刷新授权方的AccessToken
     * @param authAppid
     * @return
     */
    public static String refreshAuthAccessToken(String appid , String appsecret , String authAppid) {
        String value = null;
        // 刷新授权方access_token
        String postdata = "{\"component_appid\":\"" + appid + "\",\"authorizer_appid\":\"" + authAppid + "\",\"authorizer_refresh_token\":\"" + getAuthorizerRT(authAppid) + "\"}";
        JsonNode cac = RESTFUL.postForObject(ComponentAPI.getAuthorizertoken(getAccessToken(appid , appsecret)), postdata , JsonNode.class);
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("******refresh******");
            LOGGER.debug("refresh authorizer access_token request:{}" , postdata);
            LOGGER.debug("refresh authorizer access_token response:{}" , appid , authAppid , cac.toString());
        }
        value = cac.get("authorizer_access_token").asText();
        // 重新设置缓存
        memcache.setKV(String.format(KeyConfig.Component_Key.Component_A_A_T , authAppid) , value , KeyConfig.HOUR_2);
        // 同步更新数据库中的refresh_token
        String refresh_token = cac.get("authorizer_refresh_token").asText();
        setAuthorizerRT(authAppid, refresh_token);
        return value;
    }
    
    public static void main(String[] args) {
        String postdata = "{\"component_appid\":\"\",\"authorizer_appid\":\"\",\"authorizer_refresh_token\":\"\"}";
        JsonNode cac = RESTFUL.postForObject(ComponentAPI.getAuthorizertoken(""), postdata , JsonNode.class);
        System.out.println(cac.toString());
    }
    
    /**
     * 设置授权方的永久缓存,默认存储时长一个月
     * @param authAppid
     * @param authAccessToken
     */
    public static void setAuthorizerRT(String authAppid, String authRefreshToken) {
        LOGGER.debug("Component_A{}_R_T:{}",authAppid,authRefreshToken);
        memcache.setKV(String.format(KeyConfig.Component_Key.Component_A_R_T, authAppid) , authRefreshToken , KeyConfig.MONTH_1);    // APPID_REFRESH_TOKEN
        // 同步数据库
        AuthorizerMapper authorizerMapper = ctx.getBean(AuthorizerMapper.class);
        String refreshToken  = authorizerMapper.getRefreshToken(authAppid);
        Integer result = authorizerMapper.updateRefreshToken(authAppid , refreshToken);
        LOGGER.info("同步更新授权方:{}的refresh_token:{},数据库同步状态：{}", authAppid , authRefreshToken , result);
        
    }
    /**
     * 获取授权方Refresh_token
     * 
     * @param authAppid
     * @return
     */
    public static String getAuthorizerRT(String authAppid){
        String value = memcache.getString(String.format(KeyConfig.Component_Key.Component_A_R_T, authAppid));
        if(StringUtils.isBlank(value)){
            AuthorizerMapper authorizerMapper = ctx.getBean(AuthorizerMapper.class);
            String refreshToken = authorizerMapper.getRefreshToken(authAppid);
            setAuthorizerRT(authAppid, refreshToken);
        }
        return value;
    }

    /**
     * 检测authAppid是否合法
     * @return
     */
    public static boolean isLegal(String authAppid) {
        boolean isLegal = false;
        String key = String.format(KeyConfig.Authorizer_key.ISLEGAL , authAppid);
        Integer state = memcache.getInteger(key);
        if(state == null){
            AuthorizerMapper authorizerMapper = ctx.getBean(AuthorizerMapper.class);
            try {
                state = authorizerMapper.getState(authAppid);
                state = state == null ? MpConstant.Authorizer.AUTH_CANCEL : state;
                memcache.setKV(key , state, KeyConfig.DAY_1);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return false;
            }
        }
        if(state == MpConstant.Authorizer.AUTH_ACCEPT){
            isLegal = true;
        }
        return isLegal;
    }

    /**
     * 设置授权方合法性
     * 1. 合法
     * 2. 不合法
     * @param authAppid
     */
    public static void setAuthAppidLegal(String authAppid , Integer state) {
        String key = String.format(KeyConfig.Authorizer_key.ISLEGAL , authAppid);
        memcache.setKV(key, state , KeyConfig.DAY_1);
    }

}
