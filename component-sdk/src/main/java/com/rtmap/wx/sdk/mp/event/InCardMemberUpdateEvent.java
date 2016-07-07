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
            <ToUserName><![CDATA[gh_9e1765b5568e]]></ToUserName>
            <FromUserName><![CDATA[ojZ8YtyVyr30HheH3CM73y7h4jJE]]></FromUserName>
            <CreateTime>1445507140</CreateTime>
            <MsgType><![CDATA[event]]></MsgType>
            <Event><![CDATA[update_member_card]]></Event>
                <CardId><![CDATA[pjZ8Ytx-nwvpCRyQneH3Ncmh6N94]]></CardId>
                <UserCardCode><![CDATA[485027611252]]></UserCardCode>
                <ModifyBonus>3</ModifyBonus>
                <ModifyBalance>0</ModifyBalance>
        </xml>


 */
/**
 * InCardUserGetEvent.  进入会员卡事件推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardMemberUpdateEvent extends EventInMsg{
    
    private String cardId;
    private String userCardCode;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String modifyBonus;
    private String modifyBalance;
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardMemberUpdateEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
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
    public String getModifyBonus() {
        return modifyBonus;
    }
    public void setModifyBonus(String modifyBonus) {
        this.modifyBonus = modifyBonus;
    }
    public String getModifyBalance() {
        return modifyBalance;
    }
    public void setModifyBalance(String modifyBalance) {
        this.modifyBalance = modifyBalance;
    }
    
}
