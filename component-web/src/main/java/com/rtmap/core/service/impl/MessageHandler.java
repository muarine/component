package com.rtmap.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.rtmap.core.config.CardConstant;
import com.rtmap.utils.http.TulingRobot;
import com.rtmap.wx.sdk.exp.AesException;
import com.rtmap.wx.sdk.model.OutMsgXmlBuilder;
import com.rtmap.wx.sdk.mp.event.*;
import com.rtmap.wx.sdk.mp.in.*;
import com.rtmap.wx.sdk.mp.out.OutCustomMsg;
import com.rtmap.wx.sdk.mp.out.OutMsg;
import com.rtmap.wx.sdk.mp.out.OutTextMsg;
import com.rtmap.wx.sdk.sign.WXBizMsgCrypt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * MessageHandler.  微信推送消息处理工厂类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月31日
 * @since 1.0.0
 */
@Service
public class MessageHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);
	
	@Autowired
	private ShakeParkService shakeParkService;
	@Autowired
	private PoiService poiService;
	@Autowired
	private CardService cardService;

	/**
	 * 逻辑判断消息类型，并返回相应的XML文本
	 * @throws AesException 
	 */
	public String processMsg(String appid , 
	                            String appsecret , 
	                            InMsg inMsg , 
	                            String authAppid , 
	                            String appToken , 
	                            String encodingAESKey) throws AesException
	{
		String resultXML = this.handler(appid , appsecret , inMsg , authAppid);
		if(LOGGER.isDebugEnabled())  LOGGER.debug("回复消息:{}",resultXML);
		if(StringUtils.isNotBlank(resultXML)){
			WXBizMsgCrypt crypt = new WXBizMsgCrypt(appToken,encodingAESKey,appid);
			return crypt.encryptMsg(resultXML, System.currentTimeMillis()/1000+"", crypt.getRandomStr());
		}
		return "";
	}
	
	/**
	 * 逻辑处理消息 
	 * @param msg
	 * @return
	 */
	public String handler(String appid , String appsecret , InMsg msg , String authAppid){
		String result = null;
		if (msg instanceof InTextMsg)
			result = processInTextMsg((InTextMsg) msg);
		else if (msg instanceof InShakearoundUserShakeEvent)
			result = processInShakearoundUserShakeEvent((InShakearoundUserShakeEvent)msg);
		else if (msg instanceof InLocationEvent)
			result = processInLocationEvent((InLocationEvent) msg);
		else if (msg instanceof InShakearoundLotteryBindEvent)
			result = processInShakearoundLotteryBindEvent((InShakearoundLotteryBindEvent) msg);
		else if (msg instanceof InFollowEvent)
			result = processInFollowEvent(appid , appsecret , (InFollowEvent) msg , authAppid);
		else if(msg instanceof InCardPassCheckEvent)
		    result = processInCardPassCheckEvent((InCardPassCheckEvent) msg , authAppid);
		else if(msg instanceof InCardConsumeEvent)
		    result = processInCardConsumeEvent((InCardConsumeEvent) msg , authAppid);
		else if(msg instanceof InCardUserDelEvent)
		    result = processInCardUserDelEvent((InCardUserDelEvent) msg , authAppid);
		else if(msg instanceof InCardUserGetEvent)
		    result = processInCardUserGetEvent((InCardUserGetEvent) msg , authAppid);
		else if(msg instanceof InCardRemindEvent)
		    result = processInCardRemindEvent((InCardRemindEvent) msg , authAppid);
		else if(
                msg instanceof InCardUserPayEvent || 
                msg instanceof InCardUserEnterSessionEvent ||
                msg instanceof InCardUserViewEvent || 
                msg instanceof InCardMemberUpdateEvent ||
                msg instanceof InCardPayOrderEvent)
            result = processInCardAllEvent((EventInMsg)msg , authAppid);
		else if(msg instanceof InPoiCheckNotifyEvent)
		    result = processInPoiCheckEvent((InPoiCheckNotifyEvent)msg, authAppid);
		else if (msg instanceof InMenuEvent)
			result = processInMenuEvent((InMenuEvent) msg);
		else if (msg instanceof InImageMsg)
			result = processInImageMsg((InImageMsg) msg);
		else if (msg instanceof InVoiceMsg)
			result = processInVoiceMsg((InVoiceMsg) msg);
		else if (msg instanceof InVideoMsg)
			result = processInVideoMsg((InVideoMsg) msg);
		else if (msg instanceof InShortVideoMsg)   //支持小视频
			result = processInShortVideoMsg((InShortVideoMsg) msg);
		else if (msg instanceof InLocationMsg)
			result = processInLocationMsg((InLocationMsg) msg);
		else if (msg instanceof InLinkMsg)
			result = processInLinkMsg((InLinkMsg) msg);
        else if (msg instanceof InCustomEvent)
        	result = processInCustomEvent((InCustomEvent) msg);
		else if (msg instanceof InQrCodeEvent)
			result = processInQrCodeEvent((InQrCodeEvent) msg);
        else if (msg instanceof InMassEvent)
        	result = processInMassEvent((InMassEvent) msg);
		else if (msg instanceof InSpeechRecognitionResults)
			result = processInSpeechRecognitionResults((InSpeechRecognitionResults) msg);
		else if (msg instanceof InTemplateMsgEvent)
			result = processInTemplateMsgEvent((InTemplateMsgEvent)msg);
		else if (msg instanceof InVerifyFailEvent)
		    result = processInVerifyFailEvent((InVerifyFailEvent)msg);
		else if (msg instanceof InVerifySuccessEvent)
		    result = processInVerifySuccessEvent((InVerifySuccessEvent)msg);
		else
			LOGGER.error("未能识别的消息类型。 消息 xml 内容为：\n" + JSON.toJSONString(msg));
		
		return result;
	}
	
	/**
     * 卡券库存报警
     * 
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInCardRemindEvent(InCardRemindEvent msg, String authAppid) {
        LOGGER.info("卡券库存报警,cardid:{}" , msg.getCardId());
        return null;
    }

    /**
     * 用户领取卡券事件推送
     * 
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInCardUserGetEvent(InCardUserGetEvent msg, String authAppid) {
        LOGGER.info("用户领取卡券事件推送");
//        mqService.createPrizeMQ(msg.getCardId(), 
//                                msg.getFromUserName(), 
//                                msg.getUserCardCode(), 
//                                "getCode");
        return null;
    }

    /**
     * 用户删除卡券
     * 
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInCardUserDelEvent(InCardUserDelEvent msg, String authAppid) {
        LOGGER.info("用户删除卡券事件");
//        mqService.createPrizeMQ(msg.getCardId(), 
//                                msg.getFromUserName(), 
//                                msg.getUserCardCode(), 
//                                "deleteCode");
        return null;
    }

    /**
     * 用户核销卡券事件推送
     * 
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInCardConsumeEvent(InCardConsumeEvent msg, String authAppid) {
        LOGGER.info("用户核销卡券事件");
//        mqService.createPrizeMQ(msg.getCardId(), 
//                                msg.getFromUserName(), 
//                                msg.getUserCardCode(), 
//                                "consumeCode");
        return null;
    }

    /**
     * 卡券审核通过通知
     * 
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInCardPassCheckEvent(InCardPassCheckEvent msg, String authAppid) {
        LOGGER.info("卡券审核通过通知");
        String cardId   = msg.getCardId();
        String event    = msg.getEvent();
        if(event.equals("card_pass_check")){
            cardService.cardPass(cardId , CardConstant.State.CHECK_PASS);
        }else{
            cardService.cardPass(cardId , CardConstant.State.CHECK_FAIL);
        }
        return null;
    }

    /**
	 * 资质认证成功、名称认证成功、年审通知
     * @param msg
     * @return
     */
    private String processInVerifySuccessEvent(InVerifySuccessEvent msg) {
        LOGGER.info("资料认证成功: event:{} , 发送方:{} , 到期时间:{}" , msg.getEvent() , msg.getFromUserName() , msg.getExpiredTime());
        return null;
    }

    /**
     * 资质认证失败、名称认证失败、认证过期失效
     * @param msg
     * @return
     */
    private String processInVerifyFailEvent(InVerifyFailEvent msg) {
        LOGGER.info("资料认证失败: event:{} , 发送方:{} , 失败发生时间:{} , 失败原因:{}" , msg.getEvent() , msg.getFromUserName() , msg.getFailTime() , msg.getFailReason());
        return null;
    }

    /**
     * 门店审核通知
     * @param msg
     * @param authAppid
     * @return
     */
    private String processInPoiCheckEvent(InPoiCheckNotifyEvent msg, String authAppid) {
        LOGGER.info("门店审核通知 UniqId:{} , PoiId:{} , Result:{} , Msg:{}" , 
                msg.getUniqId() , msg.getPoiId() , msg.getResult() , msg.getMsg());
        
        String result = msg.getResult();
        if(result.equals("succ")){
            String unqId = msg.getUniqId();
            String poiId = msg.getPoiId();
            poiService.update(unqId, poiId);
            
        }else{
            LOGGER.info("门店审核失败. 失败原因：{}" , msg.getMsg());
        }
        
        return null;
    }

    /**
     * 卡券消息推送
     * @param msg
     * @param appid
     * @return
     */
    private String processInCardAllEvent(EventInMsg msg, String appid) {
        if(LOGGER.isDebugEnabled())    LOGGER.debug("卡券事件消息推送");
        return null;
    }

    /**
	 * 摇红包领取成功推送
	 * @param msg
	 * @return
	 */
	private String processInShakearoundLotteryBindEvent(InShakearoundLotteryBindEvent msg) {
		return null;
	}

	/**
	 * 消息处理 
	 * @param msg
	 * @return
	 */
	private String render(OutMsg msg){
		String outMsgXml = null;
		if(msg != null)	
			outMsgXml = OutMsgXmlBuilder.build(msg);
		return outMsgXml;
	}
	
	/**
	 * 文本消息 
	 * @param inTextMsg
	 * @return
	 */
	private String processInTextMsg(InTextMsg inTextMsg)
	{
		if(LOGGER.isDebugEnabled()) LOGGER.debug("文本消息:{}",inTextMsg.getContent());
		OutTextMsg textMsg = new OutTextMsg(inTextMsg);
		// 1. 优先检测openid关键字
		if(inTextMsg.getContent().toLowerCase().contains("openid")){
			textMsg.setContent(inTextMsg.getFromUserName());
			return this.render(textMsg);
		}
		// 3. 调取图灵机器人
		String s = TulingRobot.getInstance().getOutText(inTextMsg.getContent());
		if(s != null){
			textMsg.setContent(s);
			return this.render(textMsg);
		}
		// 4. 最后无匹配项则返回原文
		String content = inTextMsg.getContent();
		textMsg.setContent("对于您说的：'" + content + "' 我不是很理解\ue412\ue412\ue412");
		return this.render(textMsg);
	}
	/**
	 * 摇一摇事件
	 * @param msg
	 */
	private String processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("摇一摇事件");
		try {
			shakeParkService.parseShakeAround(msg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		// 不返回任何信息
		return null;
	}
	
	/**
	 * 接收 关注/取消关注事件
	 * @param msg
	 */
	private String processInFollowEvent(String appid , String appsecret , InFollowEvent msg , String authAppid) {
		OutTextMsg outMsg = new OutTextMsg(msg);
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(msg.getEvent()))
		{
			if(LOGGER.isDebugEnabled())  LOGGER.debug("关注：{}" , msg.getFromUserName());
			// 1. 取出openid 2. 查询用户信息 3. 入库
			Map<String,Object> selectMap = new HashMap<String,Object>();
			selectMap.put("type", 1);	// type = 1 关注
			selectMap.put("rule_name", msg.getToUserName());
			outMsg.setContent("感谢您的关注!");
		}
		// 如果为取消关注事件，将无法接收到传回的信息
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(msg.getEvent()))
		{
			if(LOGGER.isDebugEnabled())  LOGGER.debug("取消关注：{}" , msg.getFromUserName());
//			outMsg.setContent("您已取消关注");
			outMsg = null;
		}
		return this.render(outMsg);
	}
	
	/**
	 * 菜单事件
	 * @param msg
	 */
	private String processInMenuEvent(InMenuEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("菜单事件：" + msg.getFromUserName());
		return null;
	}
	
	/**
	 * 上报地理位置事件
	 * @param msg
	 */
	private String processInLocationEvent(InLocationEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("上报地理位置事件：{}" , msg.getFromUserName());
		return null;
	}
	
	/**
	 * 短视频消息
	 * @param msg
	 */
	private String processInShortVideoMsg(InShortVideoMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("短视频消息");
		return null;
	}

	/**
	 * 视频消息
	 * @param msg
	 */
	private String processInVideoMsg(InVideoMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("视频消息");
		return null;
	}

	/**
	 * 语音消息
	 * @param msg
	 */
	private String processInVoiceMsg(InVoiceMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("语音消息");
		return null;
	}

	/**
	 * 图片消息
	 * @param msg
	 */
	private String processInImageMsg(InImageMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("图片消息");
		return null;
	}

	/**
	 * 模版消息推送
	 * @param msg
	 */
	private String processInTemplateMsgEvent(InTemplateMsgEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("模版消息");
		return null;
	}

	/**
	 * 
	 * @param msg
	 */
	private String processInSpeechRecognitionResults(InSpeechRecognitionResults msg) {
		OutTextMsg outMsg = new OutTextMsg(msg);
		outMsg.setContent("语音识别内容是：" + msg.getRecognition());
		return this.render(outMsg);
	}

	/**
	 * 接收群发事件消息
	 * @param msg
	 */
	private String processInMassEvent(InMassEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("群发事件消息");
		return null;
	}

	/**
	 * 扫码事件消息
	 * @param msg
	 */
	private String processInQrCodeEvent(InQrCodeEvent msg) {
		if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(msg.getEvent()))
		{
			LOGGER.info("扫码未关注：{}" , msg.getFromUserName());
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(msg.getEvent()))
		{
			LOGGER.info("扫码已关注：{}" , msg.getFromUserName());
		}
		return null;
	}

	/**
	 * 自定义推送
	 * @param msg
	 */
	private String processInCustomEvent(InCustomEvent msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("自定义推送");
		return null;
	}

	/**
	 * 链接消息
	 * @param msg
	 */
	private String processInLinkMsg(InLinkMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("链接消息");
		OutCustomMsg outCustomMsg = new OutCustomMsg(msg);
		return this.render(outCustomMsg);
	}

	/**
	 * 位置推送
	 * @param msg
	 */
	private String processInLocationMsg(InLocationMsg msg) {
		if(LOGGER.isDebugEnabled())  LOGGER.debug("位置推送");
		OutCustomMsg outCustomMsg = new OutCustomMsg(msg);
		return this.render(outCustomMsg);
	}

}
