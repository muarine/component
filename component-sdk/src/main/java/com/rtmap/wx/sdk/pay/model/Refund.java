package com.rtmap.wx.sdk.pay.model;


import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.io.InputStream;
import java.util.Map;

/**
 * Refund
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:55
 * @since 2.0.0
 */
public class Refund extends PayHandler {


    private String refundId;
    private String refundChannel;
    private Integer refundFee;
    private Integer settlementRefundFee;
    private String feeType;
    private Integer cashFee;
    private Integer cashFefundFee;
    private String outRefundNo;
    private String refundAccount;


    /**
     * 申请退款
     *
     * 需要双向证书
     *
     * @param requestParam
     * @return
     */
    public static Refund create(Map<String ,Object> requestParam , String key , InputStream in) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        Object mchId = requestParam.get("mch_id");
        if(mchId == null || mchId.equals("")){
            throw new RtmapInvalidException("mch_id 不能为空");
        }
        return request(PayAPI.getRefund() , requestParam, key ,Refund.class ,mchId.toString() , in);
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getCashFefundFee() {
        return cashFefundFee;
    }

    public void setCashFefundFee(Integer cashFefundFee) {
        this.cashFefundFee = cashFefundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }
}
