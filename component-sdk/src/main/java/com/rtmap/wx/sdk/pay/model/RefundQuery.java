package com.rtmap.wx.sdk.pay.model;


import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.util.Map;

/**
 * RefundQuery  退款查询
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:53
 * @since 2.0.0
 */
public class RefundQuery extends PayHandler {

    private String openid;
    private String isSubscribe;
    private String subOpenid;
    private String subIsSubscribe;
    private String tradeType;
    private String tradeState;
    private String bankType;
    private String detail;
    private String feeType;
    private String cashFee;
    private String cashFeeType;
    private String couponFee;
    private String couponCount;
    private String attach;
    private String timeEnd;
    private String tradeStateDesc;


    /**
     * 查询订单
     *
     * @param requestParam
     * @return
     */
    public static RefundQuery create(Map<String ,Object> requestParam , String key) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {

        return request(PayAPI.getOrderQuery() , requestParam ,key , RefundQuery.class);
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

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
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

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
