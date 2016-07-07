package com.rtmap.wx.sdk.mp.event;

/**
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[LOCATION]]></Event>
			<Latitude>23.137466</Latitude>
			<Longitude>113.352425</Longitude>
			<Precision>119.385040</Precision>
	</xml>
 */
/**
 * 
 * InLocationEvent.上报地理位置事件
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InLocationEvent extends EventInMsg {
	
	private String latitude;
	private String longitude;
	private String precision;
	
	public InLocationEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}

	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getPrecision() {
		return precision;
	}
	
	public void setPrecision(String precision) {
		this.precision = precision;
	}
}




