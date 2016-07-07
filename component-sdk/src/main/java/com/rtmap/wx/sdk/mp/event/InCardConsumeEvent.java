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
            <Event><![CDATA[user_consume_card]]></Event> 
                <CardId><![CDATA[cardid]]></CardId> 
                <UserCardCode><![CDATA[12312312]]></UserCardCode>
                <ConsumeSource><![CDATA[(FROM_API)]]></ConsumeSource>
                <OutTradeNo><![CDATA[aaaaaaaaaaaa]]></OutTradeNo>
                <TransId><![CDATA[bbbbbbbbbb]]></TransId>
                <LocationId><![CDATA[222222]]></LocationId>
                <StaffOpenId><![CDATA[obLatjjwDolFjRRd3doGIdwNqRXw]></StaffOpenId>
        </xml>

 */
/**
 * InCardConsumeEvent.  用户核销卡券事件推送
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardConsumeEvent extends EventInMsg{
    
    private String cardId;          // 卡券ID
    private String userCardCode;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String consumeSource;   // 核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）
    private String outTradeNo;      // 商户订单号（只有使用买单功能核销的卡券才会出现）
    private String transId;         // 微信支付交易订单号（只有使用买单功能核销的卡券才会出现）
    private String locationId;      // 门店名称，当前卡券核销的门店名称（只有通过卡券商户助手和买单核销时才会出现）
    private String staffOpenId;     // 核销该卡券核销员的openid（只有通过卡券商户助手核销时才会出现）
    
    /**
     * Create a new InCardConsumeEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardConsumeEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
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
    public String getConsumeSource() {
        return consumeSource;
    }
    public void setConsumeSource(String consumeSource) {
        this.consumeSource = consumeSource;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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
    public String getStaffOpenId() {
        return staffOpenId;
    }
    public void setStaffOpenId(String staffOpenId) {
        this.staffOpenId = staffOpenId;
    }
    
    
}
