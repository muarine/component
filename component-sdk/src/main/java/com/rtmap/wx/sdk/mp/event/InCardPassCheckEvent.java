package com.rtmap.wx.sdk.mp.event;

/**
	<xml> <ToUserName><![CDATA[toUser]]></ToUserName> 
    <FromUserName><![CDATA[FromUser]]></FromUserName> 
    <CreateTime>123456789</CreateTime> 
    <MsgType><![CDATA[event]]></MsgType> 
    <Event><![CDATA[card_pass_check]]></Event>  //不通过为card_not_pass_check
        <CardId><![CDATA[cardid]]></CardId> 
    </xml>
 */
/**
 * 
 * InCardPassCheckEvent.卡券审核通过事件推送
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InCardPassCheckEvent extends EventInMsg {
	
	private String cardId;
	
	public InCardPassCheckEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

	
}




