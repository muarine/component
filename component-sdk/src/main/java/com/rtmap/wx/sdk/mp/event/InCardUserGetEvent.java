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
        <FriendUserName><![CDATA[FriendUser]]></FriendUserName> 
        <CreateTime>123456789</CreateTime> 
        <MsgType><![CDATA[event]]></MsgType> 
        <Event><![CDATA[user_get_card]]></Event> 
            <CardId><![CDATA[cardid]]></CardId> 
            <IsGiveByFriend>1</IsGiveByFriend>
            <UserCardCode><![CDATA[12312312]]></UserCardCode>
            <OuterId>0</OuterId>
        </xml>

 */
/**
 * InCardUserGetEvent.  用户领取卡券事件推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardUserGetEvent extends EventInMsg{
    
    private String cardId;
    private String friendUserName;  // 赠送方账号（一个OpenID），"IsGiveByFriend”为1时填写该参数。
    private String isGiveByFriend;  // 是否为转赠，1代表是，0代表否。
    private String userCardCode;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String oldUserCardCode; // 转赠前的code序列号。
    private String outerId;         // 领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardUserGetEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
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
    public String getFriendUserName() {
        return friendUserName;
    }
    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }
    public String getIsGiveByFriend() {
        return isGiveByFriend;
    }
    public void setIsGiveByFriend(String isGiveByFriend) {
        this.isGiveByFriend = isGiveByFriend;
    }
    public String getUserCardCode() {
        return userCardCode;
    }
    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }
    public String getOldUserCardCode() {
        return oldUserCardCode;
    }
    public void setOldUserCardCode(String oldUserCardCode) {
        this.oldUserCardCode = oldUserCardCode;
    }
    public String getOuterId() {
        return outerId;
    }
    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }
    
}
