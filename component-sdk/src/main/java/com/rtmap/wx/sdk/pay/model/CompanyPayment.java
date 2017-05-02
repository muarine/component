package com.rtmap.wx.sdk.pay.model;

import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.io.InputStream;
import java.util.Map;

/**
 * CompanyPayment
 *
 * @author Muarine<maoyun0903@163.com>
 * @date 2016 11/16/16 20:07
 * @since 2.0.0
 */
public class CompanyPayment extends PayHandler {

    private String partnerTradeNo;
    private String paymentNo;
    private String paymentTime;


    /**
     * 企业付款
     *
     * @param requestParam  请求参数
     * @param key           支付密钥
     * @param in            证书输入流
     * @return
     */
    public static CompanyPayment create(Map<String ,Object> requestParam , String key , InputStream in) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        Object mchId = requestParam.get("mchid");
        if(mchId == null || mchId.equals("")){
            throw new RtmapInvalidException("mchid 不能为空");
        }
        return requestNoCheckSign(PayAPI.getPromotionTransfers() , requestParam, key ,CompanyPayment.class ,mchId.toString() , in);
    }


    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
}
