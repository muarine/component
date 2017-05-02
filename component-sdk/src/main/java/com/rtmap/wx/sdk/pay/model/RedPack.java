package com.rtmap.wx.sdk.pay.model;


import com.rtmap.wx.sdk.exp.RtmapConnectException;
import com.rtmap.wx.sdk.exp.RtmapInvalidException;
import com.rtmap.wx.sdk.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.io.InputStream;
import java.util.Map;

/**
 * RedPack
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/22/16 12:56
 * @since 2.0.0
 */
public class RedPack extends PayHandler {

    private String mchBillno;
    private String wxappid;
    private String msgappid;
    private String sendName;
    private String reOpenid;
    private String totalAmount;
    private String sendListid;

    /**
     * 现金红包
     *
     * @param requestParam
     * @return
     */
    public static RedPack create(Map<String ,Object> requestParam , String key , InputStream certInputStream) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        Object mchId = requestParam.get("mch_id");
        if(mchId == null || mchId.equals("")){
            throw new RtmapInvalidException("mch_id 不能为空");
        }
        return _requestNoCheckSign(PayAPI.getSendRedPack() , requestParam, key ,RedPack.class ,mchId.toString() , certInputStream);
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getMsgappid() {
        return msgappid;
    }

    public void setMsgappid(String msgappid) {
        this.msgappid = msgappid;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSendListid() {
        return sendListid;
    }

    public void setSendListid(String sendListid) {
        this.sendListid = sendListid;
    }
}
