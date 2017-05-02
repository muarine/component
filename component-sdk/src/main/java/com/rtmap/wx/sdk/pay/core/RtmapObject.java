package com.rtmap.wx.sdk.pay.core;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * RtmapObject  基类
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:32
 * @since 2.0.0
 */
public abstract class RtmapObject {

    private String returnCode;
    private String returnMsg;

    private String resultCode;
    private String errCode;
    private String errCodeDes;

    // common
    private String appid;
    private String mchId;
    private String subAppid;
    private String subMchId;
    private String deviceInfo;
    private String nonceStr;
    private String transactionId;
    private String outTradeNo;
    private String totalFee;
    private String sign;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    protected static <T> T _mapToInstance(Map<String, Object> resultMap, Class<T> clazz){
        // Map to Json
        String mapJson = JSON.toJSONString(resultMap);

        // Json to Object

        return JSON.parseObject(mapJson , clazz);
    }


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
