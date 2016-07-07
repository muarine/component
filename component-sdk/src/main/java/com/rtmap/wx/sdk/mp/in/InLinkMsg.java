package com.rtmap.wx.sdk.mp.in;

/**
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>1351776360</CreateTime>
		<MsgType><![CDATA[link]]></MsgType>
			<Title><![CDATA[公众平台官网链接]]></Title>
			<Description><![CDATA[公众平台官网链接]]></Description>
			<Url><![CDATA[url]]></Url>
			<MsgId>1234567890123456</MsgId>
	</xml>
*/
/**
 * 
 * InLinkMsg. 接收图片消息
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InLinkMsg extends InMsg {
	
	private String title;
	private String description;
	private String url;
	private String msgId;
	
	public InLinkMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}



