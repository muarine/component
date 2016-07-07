package com.rtmap.wx.sdk.mp.event;

import com.rtmap.wx.sdk.mp.in.InMsg;
/**
 * 
 * EventInMsg. 接收事件消息
 * 
 * @author muarine
 * @since 2.1.6
 */
public abstract class EventInMsg extends InMsg
{
    protected String event;

    public EventInMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event)
    {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }
}
