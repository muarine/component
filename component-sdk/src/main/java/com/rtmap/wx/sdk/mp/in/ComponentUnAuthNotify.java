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
        <InfoType>unauthorized</InfoType>
        <AuthorizerAppid>公众号appid</AuthorizerAppid>
    </xml>
 */
/**
 * ComponentUnAuthNotify.   取消授权推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
public class ComponentUnAuthNotify extends ComponentNotify {
    
    private String authorizerAppid;
    
    /**
     * Create a new ComponentUnAuthNotify.
     * 
     * @param appid
     * @param createTime
     * @param infoType
     */
    public ComponentUnAuthNotify(String appid, String createTime, String infoType) {
        // FIXME ComponentUnAuthNotify constructor
        super(appid, createTime, infoType);
    }

    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
    }

}
