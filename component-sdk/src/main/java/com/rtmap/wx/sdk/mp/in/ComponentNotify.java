/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.mp.in;

/**
 * ComponentNotify. 平台推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
public abstract class ComponentNotify {
    
    protected String appid;
    protected String createTime;
    protected String infoType;
    
    /**
     * Create a new ComponentNotify.
     * 
     * @param appid
     * @param createTime
     * @param infoType
     */
    public ComponentNotify(String appid, String createTime, String infoType) {
        super();
        this.appid = appid;
        this.createTime = createTime;
        this.infoType = infoType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    
}
