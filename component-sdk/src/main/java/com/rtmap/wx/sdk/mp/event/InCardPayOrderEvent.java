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
         <ToUserName><![CDATA[gh_7223c83d4be5]]></ToUserName>
         <FromUserName><![CDATA[ob5E7s-HoN9tslQY3-0I4qmgluHk]]></FromUserName>
         <CreateTime>1453295737</CreateTime>
         <MsgType><![CDATA[event]]></MsgType>
         <Event><![CDATA[card_pay_order]]></Event>
             <OrderId><![CDATA[404091456]]></OrderId>
             <Status><![CDATA[ORDER_STATUS_FINANCE_SUCC]]></Status>
             <CreateTime>1453295737</CreateTime>
             <PayFinishTime>0</PayFinishTime>
             <Desc><![CDATA[]]></Desc>
             <FreeCoinCount><![CDATA[200]]></FreeCoinCount>
             <PayCoinCount><![CDATA[0]]></PayCoinCount>
             <RefundFreeCoinCount><![CDATA[0]]></RefundFreeCoinCount>
             <RefundPayCoinCount><![CDATA[0]]></RefundPayCoinCount>
             <OrderType><![CDATA[ORDER_TYPE_SYS_ADD]]></OrderType>
             <Memo><![CDATA[开通账户奖励]]></Memo>
             <ReceiptInfo><![CDATA[]]></ReceiptInfo>
        </xml>


 */
/**
 * InCardUserGetEvent.  券点流水详情事件
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardPayOrderEvent extends EventInMsg{
    
    private String orderId;         // 本次推送对应的订单号
    /**
     本次订单号的状态,
         ORDER_STATUS_WAITING 等待支付 
         ORDER_STATUS_SUCC 支付成功 
         ORDER_STATUS_FINANCE_SUCC 加代币成功 
         ORDER_STATUS_QUANTITY_SUCC 加库存成功 
         ORDER_STATUS_HAS_REFUND 已退币 
         ORDER_STATUS_REFUND_WAITING 等待退币确认 
         ORDER_STATUS_ROLLBACK 已回退,系统失败 
         ORDER_STATUS_HAS_RECEIPT 已开发票
     */
    private String status;          // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String payFinishTime;   // 购买券点时，实际支付成功的时间
    private String desc;            // 支付方式，一般为微信支付充值
    private String freeCoinCount;   // 剩余免费券点数量
    private String payCoinCount;    // 剩余付费券点数量
    private String refundFreeCoinCount;     // 本次变动的免费券点数量
    private String refundPayCoinCount;      // 本次变动的付费券点数量
    /**
     所要拉取的订单类型
        ORDER_TYPE_SYS_ADD 平台赠送券点 
        ORDER_TYPE_WXPAY 充值券点 
        ORDER_TYPE_REFUND 库存未使用回退券点 
        ORDER_TYPE_REDUCE 券点兑换库存 
        ORDER_TYPE_SYS_REDUCE 平台扣减
     */
    private String orderType;       // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    private String memo;            // 系统备注，说明此次变动的缘由，如开通账户奖励、门店奖励、核销奖励以及充值、扣减。
    private String receiptInfo;     // 所开发票的详情
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardPayOrderEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
            String event) {
        // FIXME InCardUserGetEvent constructor
        super(toUserName, fromUserName, createTime, msgType, event);
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPayFinishTime() {
        return payFinishTime;
    }
    public void setPayFinishTime(String payFinishTime) {
        this.payFinishTime = payFinishTime;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getFreeCoinCount() {
        return freeCoinCount;
    }
    public void setFreeCoinCount(String freeCoinCount) {
        this.freeCoinCount = freeCoinCount;
    }
    public String getPayCoinCount() {
        return payCoinCount;
    }
    public void setPayCoinCount(String payCoinCount) {
        this.payCoinCount = payCoinCount;
    }
    public String getRefundFreeCoinCount() {
        return refundFreeCoinCount;
    }
    public void setRefundFreeCoinCount(String refundFreeCoinCount) {
        this.refundFreeCoinCount = refundFreeCoinCount;
    }
    public String getRefundPayCoinCount() {
        return refundPayCoinCount;
    }
    public void setRefundPayCoinCount(String refundPayCoinCount) {
        this.refundPayCoinCount = refundPayCoinCount;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getReceiptInfo() {
        return receiptInfo;
    }
    public void setReceiptInfo(String receiptInfo) {
        this.receiptInfo = receiptInfo;
    }
    
}
