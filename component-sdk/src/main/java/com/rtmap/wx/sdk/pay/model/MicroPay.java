/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2017 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.pay.model;


import com.rtmap.wx.sdk.exp.RtmapConnectException;
import com.rtmap.wx.sdk.exp.RtmapInvalidException;
import com.rtmap.wx.sdk.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.util.Map;

/**
 * @author maoyun@rtmap.com
 * @project rtmap-sdk
 * @package com.rtmap.pay.model
 * @date 4/23/17
 */
public class MicroPay extends PayHandler {

    private String openid;
    private String isSubscribe;
    private String subOpenid;
    private String subIsSubscribe;
    private String tradeType;
    private String bankType;
    private String feeType;
    private String cashFeeType;
    private String cashFee;
    private String attach;
    private String timeEnd;

    /**
     * POS支付
     *
     * @param requestParam 请求参数
     * @return
     */
    public static MicroPay create(Map<String, Object> requestParam, String key) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {

        return _request(PayAPI.getMicroPay(), requestParam, key, MicroPay.class);
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public String getSubIsSubscribe() {
        return subIsSubscribe;
    }

    public void setSubIsSubscribe(String subIsSubscribe) {
        this.subIsSubscribe = subIsSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}