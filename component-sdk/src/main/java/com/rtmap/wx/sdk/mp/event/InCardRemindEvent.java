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
            <ToUserName><![CDATA[gh_2d62d*****0]]></ToUserName>
            <FromUserName><![CDATA[oa3LFuBvWb7*********]]></FromUserName> 
            <CreateTime>1443838506</CreateTime>
            <MsgType><![CDATA[event]]></MsgType>
            <Event><![CDATA[card_sku_remind]]></Event>
                <CardId><![CDATA[pa3LFuAh2P65**********]]></CardId>
                <Detail><![CDATA[the card's quantity is equal to 0]]></Detail>
        </xml>

 */
/**
 * InCardUserGetEvent.  库存报警事件
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月25日
 * @since 1.0.0
 */
public class InCardRemindEvent extends EventInMsg{
    
    private String cardId;
    private String detail;    // code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
    /**
     * Create a new InCardUserGetEvent.
     * 
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param event
     */
    public InCardRemindEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
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
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
