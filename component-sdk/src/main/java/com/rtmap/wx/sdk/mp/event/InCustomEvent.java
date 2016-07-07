package com.rtmap.wx.sdk.mp.event;

/**
 接入会话：
 <xml>
 <ToUserName><![CDATA[touser]]></ToUserName>
 <FromUserName><![CDATA[fromuser]]></FromUserName>
 <CreateTime>1399197672</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[kf_create_session]]></Event>
 <KfAccount><![CDATA[test1@test]]></KfAccount>
 </xml>

 关闭会话：
 <xml>
 <ToUserName><![CDATA[touser]]></ToUserName>
 <FromUserName><![CDATA[fromuser]]></FromUserName>
 <CreateTime>1399197672</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[kf_close_session]]></Event>
 <KfAccount><![CDATA[test1@test]]></KfAccount>
 </xml>

 转接会话：
 <xml>
 <ToUserName><![CDATA[touser]]></ToUserName>
 <FromUserName><![CDATA[fromuser]]></FromUserName>
 <CreateTime>1399197672</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[kf_switch_session]]></Event>
 <FromKfAccount><![CDATA[test1@test]]></FromKfAccount>
 <ToKfAccount><![CDATA[test2@test]]></ToKfAccount>
 </xml>
 */
/**
 * 
 * InCustomEvent. 接入会话，关闭会话，转接会话
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InCustomEvent extends EventInMsg
{
    // 接入会话：kf_create_session
    public static final String EVENT_INCUSTOM_KF_CREATE_SESSION = "kf_create_session";
    // 关闭会话：kf_close_session
    public static final String EVENT_INCUSTOM_KF_CLOSE_SESSION = "kf_close_session";
    // 转接会话：kf_switch_session
    public static final String EVENT_INCUSTOM_KF_SWITCH_SESSION = "kf_switch_session";

    private String kfAccount;
    private String toKfAccount;

    public InCustomEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event)
    {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public String getKfAccount()
    {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount)
    {
        this.kfAccount = kfAccount;
    }

    public String getToKfAccount()
    {
        return toKfAccount;
    }

    public void setToKfAccount(String toKfAccount)
    {
        this.toKfAccount = toKfAccount;
    }

}






