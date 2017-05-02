package com.rtmap.wx.sdk.pay.model;


import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.io.InputStream;
import java.util.Map;

/**
 * GroupRedPack
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/22/16 12:59
 * @since 2.0.0
 */
public class GroupRedpack extends PayHandler {

    private String mchBillno;
    private String wxappid;
    private String msgappid;
    private String sendName;
    private String reOpenid;
    private String totalAmount;
    private String sendListid;

    /**
     * 裂变红包
     *
     * @param requestParam  请求参数
     * @param key           密钥
     * @param in            证书
     * @return
     */
    public static GroupRedpack create(Map<String ,Object> requestParam , String key , InputStream in) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        Object mchId = requestParam.get("mch_id");
        if(mchId == null || mchId.equals("")){
            throw new RtmapInvalidException("mch_id 不能为空");
        }
        return requestNoCheckSign(PayAPI.getOrderQuery() , requestParam, key ,GroupRedpack.class ,mchId.toString() , in);
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
