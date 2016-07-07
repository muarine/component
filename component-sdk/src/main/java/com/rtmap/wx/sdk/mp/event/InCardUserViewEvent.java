/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.mp.event;
/**

        <xml>
            <ToUserName><![CDATA[toUser]]></ToUserName> 
            <FromUserName><![CDATA[FromUser]]></FromUserName> 
            <CreateTime>123456789</CreateTime> 
            <MsgType><![CDATA[event]]></MsgType> 
            <Event><![CDATA[user_view_card]]></Event> 
                <CardId><![CDATA[cardid]]></CardId> 
                <UserCardCode><![CDATA[12312312]]></UserCardCode>
        </xml>

 */
/**
 * InCardUserGetEvent.  进入会员卡事件推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardUserViewEvent extends EventInMsg{
    
    private String cardId;
    private String userCardCode;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardUserViewEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
            String event) {
        // FIXME InCardUserGetEvent constructor
        super(toUserName, fromUserName, createTime, msgType, event);
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getUserCardCode() {
        return userCardCode;
    }
    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }
}
