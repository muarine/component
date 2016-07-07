/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.mp.event;

import com.rtmap.wx.sdk.mp.in.InMsg;

/**
 * <xml>
	  <ToUserName><![CDATA[gh_1127dff68423]]></ToUserName>  
	  <FromUserName><![CDATA[oikd5jkgO39tQ6xm-SYh1keQZc4k]]></FromUserName>  
	  <CreateTime>1446033138</CreateTime>  
	  <MsgType><![CDATA[event]]></MsgType>  
	  <Event><![CDATA[ShakearoundLotteryBind]]></Event>
	  <LotteryId><![CDATA[x9p1yLpYO0ZkCzV0RPwMNA]]></LotteryId>
	  <Ticket><![CDATA[v1|MJ0C2o8W0f4WAW5JOtIajc4ohkEPVKEGqbFzajaXwhv1DneVCRF2LsEjgmfN+OY8Q9abjPKprkI+pSRC09iYpq2uDVbKGpJY1YtiI5SwVWQf1MdDC2uyRcUyHiYCDqUNzdvPE1rycff2DG659AI8Aw==]]></Ticket>
	  <Money>888</Money>
	  <BindTime>1446033138</BindTime>
	</xml>
 */
/**
 * InShakearoundLotteryBind.  摇红包推送(只要摇中了，不管用户有没有领取,此红包就和用户绑定了，并推送事件消息)
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InShakearoundLotteryBindEvent extends InMsg{

	private String event;//事件
	private String lotteryId;
	private String ticket;
	private String money;
	private String bindTime;//设备与用户的距离（浮点数；单位：米）
	
	/**
	 * Create a new InShakearoundLotteryBind.
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 */
	public InShakearoundLotteryBindEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

}
