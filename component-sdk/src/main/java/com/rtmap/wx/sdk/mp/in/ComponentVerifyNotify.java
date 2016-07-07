/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.mp.in;
/**
 *      <xml>
            <AppId> </AppId>
            <CreateTime>1413192605 </CreateTime>
            <InfoType> </InfoType>
            <ComponentVerifyTicket> </ComponentVerifyTicket>
        </xml>
 */
/**
 * ComponentVerifyNotify.   推送component_verify_ticket协议
 *      
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
public class ComponentVerifyNotify extends ComponentNotify{
    
    private String componentVerifyTicket;   // 每隔十分钟推送一次ticket
    
    
    /**
     * Create a new ComponentVerifyNotify.
     * 
     * @param appid
     * @param createTime
     * @param infoType
     */
    public ComponentVerifyNotify(String appid, String createTime, String infoType) {
        // FIXME ComponentVerifyNotify constructor
        super(appid, createTime, infoType);
    }


    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }

    public void setComponentVerifyTicket(String componentVerifyTicket) {
        this.componentVerifyTicket = componentVerifyTicket;
    }
    
    
}
