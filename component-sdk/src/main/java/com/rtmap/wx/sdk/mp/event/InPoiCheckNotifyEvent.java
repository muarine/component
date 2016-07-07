package com.rtmap.wx.sdk.mp.event;

/**
 * <xml>
        <ToUserName><![CDATA[toUser]]></ToUserName>
        <FromUserName><![CDATA[fromUser]]></FromUserName>
        <CreateTime>1408622107</CreateTime>
        <MsgType><![CDATA[event]]></MsgType>
        <Event><![CDATA[poi_check_notify]]></Event>
        <UniqId><![CDATA[123adb]]></UniqId>
        <PoiId><![CDATA[123123]]></PoiId>
        <Result><![CDATA[fail]]></Result>
        <Msg><![CDATA[xxxxxx]]></Msg>
    </xml>
 */
/**
 * 
 * InPoiCheckNotifyEvent.   
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月31日
 * @since 1.0.0
 */
public class InPoiCheckNotifyEvent extends EventInMsg {
	private String uniqId;     // 商户自己内部ID，即字段中的sid
	private String poiId;      // 微信的门店ID，微信内门店唯一标示ID
	private String result;     // 审核结果，成功succ 或失败fail
	private String msg;        // 成功的通知信息，或审核失败的驳回理由
	
	public InPoiCheckNotifyEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	
	
}




