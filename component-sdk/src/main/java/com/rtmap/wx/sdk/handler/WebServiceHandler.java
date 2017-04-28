/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.wx.sdk.api.WebServiceAPI;
import com.rtmap.wx.sdk.token.OauthToken;

/**
 * CacheService.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月8日
 * @since 2.0
 */
public class WebServiceHandler extends AbstractHandler{
	
    private static class SingleTonHolder{
        private final static WebServiceHandler INSTANCE = new WebServiceHandler(); 
    }
    
    private WebServiceHandler(){}
    
    public static WebServiceHandler instance = SingleTonHolder.INSTANCE;
    
    /**
     * 创建Oauth网页认证
     * 
     * @param appid
     * @param code
     * @param component_appid
     * @param component_access_token
     * @return
     */
    public OauthToken getOAuthToken(String appid ,String code , String component_appid , String component_access_token){
        if(LOGGER.isDebugEnabled()) 
            LOGGER.debug("appid:{},code:{},componnet_appid:{},component_access_token:{}",appid,code,component_appid , component_access_token);
        String result = RESTFUL.postForObject(WebServiceAPI.getOAuthTokenUrl(appid, code ,component_appid, component_access_token), null, String.class);
        JsonNode node = JsonMapper.fromJsonString(result, JsonNode.class);
        if(LOGGER.isDebugEnabled())  
            LOGGER.debug("静默授权第二次握手获取openid:{}" , node);
        OauthToken token = new OauthToken();
        if (null != node && null == node.get("errcode")) {
            token.setAccessToken(node.get("access_token").asText());
            token.setExpiresIn(node.get("expires_in").asInt());
            token.setOpenid(node.get("openid").asText());
            token.setScope(node.get("scope").asText());
            token.setTime(System.currentTimeMillis());
        }else{
            token.setErrcode(node.get("errcode").asInt());
            token.setErrmsg(node.get("errmsg").asText());
            LOGGER.error("静默授权code换取网页授权access_token,出错！ OauthToken:{}" , token.toString());
        }
        return token;
    }
    
    /**
    * 获取OAuth2.0 UserInfo
    * 若code换取access_token失败,则不把token对象放入缓存
    * @param appid
    * @param code
     * @param component_appid
     * @param component_access_token
     * @return JSONObject
     */
    public OauthToken getOAuthUserinfo(String appid,String code , String component_appid ,String component_access_token) throws Exception{
        LOGGER.debug("appid:{},code:{},component_appid:{},component_access_token:{}",appid,code,component_appid,component_access_token);
        String result = RESTFUL.getForObject(WebServiceAPI.getOAuthTokenUrl(appid, code , component_appid , component_access_token) , String.class);
        JsonNode node = JsonMapper.fromJsonString(result, JsonNode.class);
        LOGGER.debug("非静默授权第二次握手获取基本信息:{}",node);
        OauthToken oauthToken = new OauthToken();
        if (null != node && node.get("errcode") == null) {
            try {
                String openid = node.get("openid").asText();
                String access_token = node.get("access_token").asText();
                result = RESTFUL.getForObject(WebServiceAPI.getOAuthUserinfoUrl(access_token, openid), String.class);
                LOGGER.debug("非静默授权第三次握手获取基本信息:{}" , node);
                node = JsonMapper.fromJsonString(result, JsonNode.class);
                oauthToken.setAccessToken(access_token);
                oauthToken.setNickname(node.get("nickname").asText());
                oauthToken.setOpenid(node.get("openid").asText());
                oauthToken.setUnionid(node.get("unionid").asText());
                oauthToken.setHeadimgurl(node.get("headimgurl").asText());
            } catch (JSONException e) {
                LOGGER.error(e.getMessage(),e);
            }
        }else{
            oauthToken.setErrcode(node.get("errcode").asInt());
            oauthToken.setErrmsg(node.get("errmsg").asText());
            LOGGER.error("非静默授权code换取网页授权access_token,出错！, OauthToken:{}" , oauthToken.toString());
        }
        return oauthToken;
    }
    
    /**
     * jsapi ticket 有效期7200
     * 
     * @param access_token
     * @return
     * {
     *       "errcode":0,
     *       "errmsg":"ok",
     *       "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
     *       "expires_in":7200
     *   }
     */
    public JsonNode getJsapiTicket(String access_token){
        logDebug("request:{}={}", "get jsapi ticket access_token" , access_token);
        JsonNode node = RESTFUL.getForObject(WebServiceAPI.getJsapiTicketUrl(access_token , "jsapi") , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * jsapi ticket 有效期7200
     * 
     * @param access_token
     * @return
     * {
     *   "errcode":0,
     *   "errmsg":"ok",
     *   "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKdvsdshFKA",
     *   "expires_in":7200
     *   }
     */
    public JsonNode getCardTicket(String access_token){
        logDebug("request:{}={}", "get card ticket access_token" , access_token);
        JsonNode node = RESTFUL.getForObject(WebServiceAPI.getJsapiTicketUrl(access_token , "wx_card") , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }

}
