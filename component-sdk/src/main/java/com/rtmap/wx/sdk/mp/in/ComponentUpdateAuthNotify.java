/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.mp.in;
/**
 * <xml>
        <AppId>第三方平台appid</AppId>
        <CreateTime>1413192760</CreateTime>
        <InfoType>updateauthorized</InfoType>
        <AuthorizerAppid>公众号appid</AuthorizerAppid>
        <AuthorizationCode>授权码（code）</AuthorizationCode>
        <AuthorizationCodeExpiredTime>过期时间</AuthorizationCodeExpiredTime>
    </xml>

 */
/**
 * ComponentUpdateAuthNotify.   授权更新通知
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
public class ComponentUpdateAuthNotify extends ComponentNotify{
    
    private String authorizerAppid;                 // 授权方appid
    private String authorizationCode;               // 授权码
    private String authorizationCodeExpiredTime;    // 过期时间
    
    /**
     * Create a new ComponentUpdateAuthNotify.
     * 
     * @param appid
     * @param createTime
     * @param infoType
     */
    public ComponentUpdateAuthNotify(String appid, String createTime, String infoType) {
        super(appid, createTime, infoType);
    }

    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCodeExpiredTime() {
        return authorizationCodeExpiredTime;
    }

    public void setAuthorizationCodeExpiredTime(String authorizationCodeExpiredTime) {
        this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
    }
    
}
