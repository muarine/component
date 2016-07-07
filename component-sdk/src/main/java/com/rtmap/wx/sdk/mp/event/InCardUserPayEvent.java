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
            <ToUserName><![CDATA[gh_e2243xxxxxxx]]></ToUserName>
            <FromUserName><![CDATA[oo2VNuOUuZGMxxxxxxxx]]></FromUserName>
            <CreateTime>1442390947</CreateTime>
            <MsgType><![CDATA[event]]></MsgType>
            <Event><![CDATA[user_pay_from_pay_cell]]></Event>
                <CardId><![CDATA[po2VNuCuRo-8sxxxxxxxxxxx]]></CardId>
                <UserCardCode><![CDATA[38050000000]]></UserCardCode>
                <TransId><![CDATA[10022403432015000000000]]></TransId>
                <LocationId>291710000</LocationId>
                <Fee><![CDATA[10000]]></Fee>
                <OriginalFee><![CDATA[10000]]> </OriginalFee>
        </xml>

 */
/**
 * InCardUserGetEvent.  用户买单卡券事件推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardUserPayEvent extends EventInMsg{
    
    private String cardId;
    private String userCardCode;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String transId;         // 赠送方账号（一个OpenID），"IsGiveByFriend”为1时填写该参数。
    private String locationId;      // 是否为转赠，1代表是，0代表否。
    private String fee;             // 转赠前的code序列号。
    private String originalFee;     // 领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardUserPayEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
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
    public String getTransId() {
        return transId;
    }
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public String getFee() {
        return fee;
    }
    public void setFee(String fee) {
        this.fee = fee;
    }
    public String getOriginalFee() {
        return originalFee;
    }
    public void setOriginalFee(String originalFee) {
        this.originalFee = originalFee;
    }
    
}
