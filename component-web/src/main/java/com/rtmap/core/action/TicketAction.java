/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.cache.TicketManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.config.ResponseFactory;
import com.rtmap.core.exp.Assert;
import com.rtmap.core.exp.InvalidArgumentException;
import com.rtmap.core.vo.CardParam;
import com.rtmap.core.vo.Code;
import com.rtmap.core.vo.Result;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.api.ShakeAroundAPI;
import com.rtmap.wx.sdk.sign.Sign;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TicketAction.    ticket相关接口，包含jsapi和wx_card相关ticket
 * <pre>
 * 详见官网文档：http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html
 * </pre>
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
@ControllerAdvice
@RestController
@RequestMapping("/ticket")
public class TicketAction extends AbstractAction{
    
    /**
     * jsapi ticket
     *  
     * @param authAppid 受理方appId
     * @param url       注册地址,需进行URLEncode
     * @return
     */
    @RequestMapping("/{authAppid}/jsapi_sign")
    public Result getJsapiTicket(@PathVariable("authAppid") String authAppid , String url){
        try {
            Assert.hasText(url , "url is required , it must not be null");
            String appid        = commonMap.get(MpConstant.APPID);
            String appsecret    = commonMap.get(MpConstant.APPSECRET);
            String jsapi_ticket = TicketManager.getJsapiTicket(appid , appsecret , authAppid , false);
            Map<String,String> data =  Sign.sign(appid,jsapi_ticket, url);
            return ResponseFactory.build(data);
        } catch (InvalidArgumentException e) {
            return ResponseFactory.build(Code.INVALID_PARAM , e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseFactory.build(Code.ERROR);
        }
    }
    
    /**
     * 强制刷新jsapi ticket
     * 
     * @param authAppid 受理方appId
     * @return
     */
    @RequestMapping("/{authAppid}/jsapi_sign/refresh")
    public Result refreshJsapiTicket(@PathVariable("authAppid") String authAppid){
        String appid        = commonMap.get(MpConstant.APPID);
        String appsecret    = commonMap.get(MpConstant.APPSECRET);
        String jsapi_ticket = TicketManager.getJsapiTicket(appid , appsecret , authAppid , true);
        return ResponseFactory.build(jsapi_ticket);
    }
    
    /**
     * 获取wx_card的js-sdk ticket
     * 
     * @param authAppid 受理方appId
     * @param param     卡券参数
     * @return
     */
    @RequestMapping("/{authAppid}/card_sign")
    public Result getCardTicket(@PathVariable("authAppid") String authAppid , CardParam param){
        try {
            String appid        = commonMap.get(MpConstant.APPID);
            String appsecret    = commonMap.get(MpConstant.APPSECRET);
            String jsapi_ticket = TicketManager.getCardTicket(appid, appsecret, authAppid, false);
            String timestamp  = System.currentTimeMillis()/1000+"";
            String card_id  = param.getCard_id();
            String code     = param.getCode();
            String nonce_str = StringUtils.generateUUID();
            String openid   = param.getOpenid();
            Map<String,Object> data = Sign.sign(jsapi_ticket , timestamp, card_id, code, openid, nonce_str);
            return ResponseFactory.build(data);
        } catch (InvalidArgumentException e) {
            return ResponseFactory.build(Code.INVALID_PARAM , e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseFactory.build(Code.ERROR);
        }
        
    }
    
    /**
     * 强制刷新jsapi ticket
     * 
     * @param authAppid 受理方appId
     * @return
     */
    @RequestMapping("/{authAppid}/card_sign/refresh")
    public Result refreshCardTicket(@PathVariable("authAppid") String authAppid){
        String appid        = commonMap.get(MpConstant.APPID);
        String appsecret    = commonMap.get(MpConstant.APPSECRET);
        String card_ticket = TicketManager.getJsapiTicket(appid , appsecret , authAppid , true);
        return ResponseFactory.build(card_ticket);
    }
    
    /**
     * ticket获取openid
     * 
     * @param authAppid 授权方appId
     * @param ticket    票据
     * @return
     */
    @RequestMapping(value = "/{authAppid}/token/ticket")
    public Result ticketAccessTokenNew(@PathVariable("authAppid") String authAppid , String ticket) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Assert.hasText(ticket, "ticket is required , it must not be null");
            String appid = commonMap.get(MpConstant.APPID);
            String appsecret = commonMap.get(MpConstant.APPSECRET);
            String url = ShakeAroundAPI.getUserShakeInfo(AuthManager.getAuthAccessToken(appid, appsecret, authAppid));
            map.put("ticket", ticket);
            JsonNode node = RestfulTemplate.INSTANCE.postForObject(url, map, JsonNode.class);
            int code = node.get("errcode").asInt();
            if (code == 0) {
                String openId = node.get("data").get("openid").asText();
                map.put("openId", openId);
                return ResponseFactory.build(map);
            }else{
                return ResponseFactory.build(Code.INVALID_PARAM , node.toString());
            }
        } catch (InvalidArgumentException e) {
            return ResponseFactory.build(Code.INVALID_PARAM);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return ResponseFactory.build(Code.ERROR);
        }
    }
    
    
}
