package com.rtmap.wx.sdk.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.rtmap.wx.sdk.mp.event.InCardConsumeEvent;
import com.rtmap.wx.sdk.mp.event.InCardMemberUpdateEvent;
import com.rtmap.wx.sdk.mp.event.InCardPassCheckEvent;
import com.rtmap.wx.sdk.mp.event.InCardPayOrderEvent;
import com.rtmap.wx.sdk.mp.event.InCardRemindEvent;
import com.rtmap.wx.sdk.mp.event.InCardUserDelEvent;
import com.rtmap.wx.sdk.mp.event.InCardUserEnterSessionEvent;
import com.rtmap.wx.sdk.mp.event.InCardUserGetEvent;
import com.rtmap.wx.sdk.mp.event.InCardUserPayEvent;
import com.rtmap.wx.sdk.mp.event.InCardUserViewEvent;
import com.rtmap.wx.sdk.mp.event.InCustomEvent;
import com.rtmap.wx.sdk.mp.event.InFollowEvent;
import com.rtmap.wx.sdk.mp.event.InLocationEvent;
import com.rtmap.wx.sdk.mp.event.InMassEvent;
import com.rtmap.wx.sdk.mp.event.InMenuEvent;
import com.rtmap.wx.sdk.mp.event.InPoiCheckNotifyEvent;
import com.rtmap.wx.sdk.mp.event.InQrCodeEvent;
import com.rtmap.wx.sdk.mp.event.InShakearoundLotteryBindEvent;
import com.rtmap.wx.sdk.mp.event.InShakearoundUserShakeEvent;
import com.rtmap.wx.sdk.mp.event.InShakearoundUserShakeEvent.AroundBeacon;
import com.rtmap.wx.sdk.mp.event.InTemplateMsgEvent;
import com.rtmap.wx.sdk.mp.event.InVerifyFailEvent;
import com.rtmap.wx.sdk.mp.event.InVerifySuccessEvent;
import com.rtmap.wx.sdk.mp.event.ScanCodeInfo;
import com.rtmap.wx.sdk.mp.in.ComponentAuthNotify;
import com.rtmap.wx.sdk.mp.in.ComponentNotify;
import com.rtmap.wx.sdk.mp.in.ComponentUnAuthNotify;
import com.rtmap.wx.sdk.mp.in.ComponentUpdateAuthNotify;
import com.rtmap.wx.sdk.mp.in.ComponentVerifyNotify;
import com.rtmap.wx.sdk.mp.in.InImageMsg;
import com.rtmap.wx.sdk.mp.in.InLinkMsg;
import com.rtmap.wx.sdk.mp.in.InLocationMsg;
import com.rtmap.wx.sdk.mp.in.InMsg;
import com.rtmap.wx.sdk.mp.in.InShortVideoMsg;
import com.rtmap.wx.sdk.mp.in.InSpeechRecognitionResults;
import com.rtmap.wx.sdk.mp.in.InTextMsg;
import com.rtmap.wx.sdk.mp.in.InVideoMsg;
import com.rtmap.wx.sdk.mp.in.InVoiceMsg;
/**
 * 解析接收的消息
 * InMsgParser.
 * 
 * @author muarine
 * @since 2.1.6
 */
public class InMsgParser {
	
	private InMsgParser() {}
	
	/**
	 * 从 xml 中解析出各类消息与事件
	 */
	public static InMsg parse(String xml) {
		try {
			return doParse(xml);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 解析平台消息通知
	 * @param xml
	 * @return
	 */
	public static ComponentNotify parseNotify(String xml){
	    try {
            return doNotifyParse(xml);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
     * 平台通知
     * 
     * @param xml
     * @return
	 * @throws DocumentException 
     */
    private static ComponentNotify doNotifyParse(String xml) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        String appid = root.elementText("Appid");
        String createTime   = root.elementText("CreateTime");
        String infoType     = root.elementText("InfoType");
        if("component_verify_ticket".equals(infoType))
            return parseComponentVerfiyNotify(root, appid, createTime, infoType);
        if("authorized".equals(infoType))
            return parseComponentAuthNotify(root, appid, createTime, infoType);
        if("unauthorized".equals(infoType))
            return parseComponentUnAuthNotify(root, appid, createTime, infoType);
        if("updateauthorized".equals(infoType))
            return parseComponentUpdateNotify(root, appid, createTime, infoType);
        throw new RuntimeException("无法识别的推送的授权通知 " + infoType + "，请查阅微信开放平台文档");
    }
    
    private static ComponentNotify parseComponentVerfiyNotify(Element root, String appid, String createTime, String infoType) {
        ComponentVerifyNotify notify = new ComponentVerifyNotify(appid, createTime, infoType);
        notify.setComponentVerifyTicket(root.elementText("ComponentVerifyTicket"));
        return notify;
    }
    private static ComponentNotify parseComponentUnAuthNotify(Element root, String appid, String createTime, String infoType) {
        ComponentUnAuthNotify notify = new ComponentUnAuthNotify(appid, createTime, infoType);
        notify.setAuthorizerAppid(root.elementText("AuthorizerAppid"));
        return notify;
    }
    private static ComponentNotify parseComponentUpdateNotify(Element root, String appid, String createTime, String infoType) {
        ComponentUpdateAuthNotify notify = new ComponentUpdateAuthNotify(appid, createTime, infoType);
        notify.setAuthorizationCode(root.elementText("AuthorizationCode"));
        notify.setAuthorizationCodeExpiredTime(root.elementText("AuthorizationCodeExpiredTime"));
        notify.setAuthorizerAppid(root.elementText("AuthorizerAppid"));
        return notify;
    }
    private static ComponentNotify parseComponentAuthNotify(Element root, String appid, String createTime, String infoType) {
        ComponentAuthNotify notify = new ComponentAuthNotify(appid, createTime, infoType);
        notify.setAuthorizationCode(root.elementText("AuthorizationCode"));
        notify.setAuthorizationCodeExpiredTime(root.elementText("AuthorizationCodeExpiredTime"));
        notify.setAuthorizerAppid(root.elementText("AuthorizerAppid"));
        return notify;
    }

    /**
	 * 消息类型
	 * 1：text 文本消息
	 * 2：image 图片消息
	 * 3：voice 语音消息
	 * 4：video 视频消息
	 *	shortvideo 小视频消息
	 * 5：location 地址位置消息
	 * 6：link 链接消息
	 * 7：event 事件
	 */
	private static InMsg doParse(String xml) throws DocumentException {
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		String toUserName = root.elementText("ToUserName");
		String fromUserName = root.elementText("FromUserName");
		Integer createTime = Integer.parseInt(root.elementText("CreateTime"));
		String msgType = root.elementText("MsgType");
		if ("text".equals(msgType))
			return parseInTextMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("image".equals(msgType))
			return parseInImageMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("voice".equals(msgType))
			return parseInVoiceMsgAndInSpeechRecognitionResults(root, toUserName, fromUserName, createTime, msgType);
		if ("video".equals(msgType))
			return parseInVideoMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("shortvideo".equals(msgType))	 //支持小视频
			return parseInShortVideoMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("location".equals(msgType))
			return parseInLocationMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("link".equals(msgType))
			return parseInLinkMsg(root, toUserName, fromUserName, createTime, msgType);
		if ("event".equals(msgType))
			return parseInEvent(root, toUserName, fromUserName, createTime, msgType);
		throw new RuntimeException("无法识别的消息类型 " + msgType + "，请查阅微信公众平台开发文档");
	}
	
	private static InMsg parseInTextMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InTextMsg msg = new InTextMsg(toUserName, fromUserName, createTime, msgType);
		msg.setContent(root.elementText("Content"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	private static InMsg parseInImageMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InImageMsg msg = new InImageMsg(toUserName, fromUserName, createTime, msgType);
		msg.setPicUrl(root.elementText("PicUrl"));
		msg.setMediaId(root.elementText("MediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	private static InMsg parseInVoiceMsgAndInSpeechRecognitionResults(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		String recognition = root.elementText("Recognition");
		if (StringUtils.isBlank(recognition)) {
			InVoiceMsg msg = new InVoiceMsg(toUserName, fromUserName, createTime, msgType);
			msg.setMediaId(root.elementText("MediaId"));
			msg.setFormat(root.elementText("Format"));
			msg.setMsgId(root.elementText("MsgId"));
			return msg;
		}
		else {
			InSpeechRecognitionResults msg = new InSpeechRecognitionResults(toUserName, fromUserName, createTime, msgType);
			msg.setMediaId(root.elementText("MediaId"));
			msg.setFormat(root.elementText("Format"));
			msg.setMsgId(root.elementText("MsgId"));
			msg.setRecognition(recognition);			// 与 InVoiceMsg 唯一的不同之处
			return msg;
		}
	}
	
	private static InMsg parseInVideoMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InVideoMsg msg = new InVideoMsg(toUserName, fromUserName, createTime, msgType);
		msg.setMediaId(root.elementText("MediaId"));
		msg.setThumbMediaId(root.elementText("ThumbMediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}

	private static InMsg parseInShortVideoMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InShortVideoMsg msg = new InShortVideoMsg(toUserName, fromUserName, createTime, msgType);
		msg.setMediaId(root.elementText("MediaId"));
		msg.setThumbMediaId(root.elementText("ThumbMediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}

	private static InMsg parseInLocationMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InLocationMsg msg = new InLocationMsg(toUserName, fromUserName, createTime, msgType);
		msg.setLocation_X(root.elementText("Location_X"));
		msg.setLocation_Y(root.elementText("Location_Y"));
		msg.setScale(root.elementText("Scale"));
		msg.setLabel(root.elementText("Label"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	private static InMsg parseInLinkMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		InLinkMsg msg = new InLinkMsg(toUserName, fromUserName, createTime, msgType);
		msg.setTitle(root.elementText("Title"));
		msg.setDescription(root.elementText("Description"));
		msg.setUrl(root.elementText("Url"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}

	// 解析各种事件
	@SuppressWarnings("rawtypes")
	private static InMsg parseInEvent(Element root, String toUserName, String fromUserName, Integer createTime, String msgType) {
		String event = root.elementText("Event");
		String eventKey = root.elementText("EventKey");
		
		// 关注/取消关注事件（包括二维码扫描关注，二维码扫描关注事件与扫描带参数二维码事件是两回事）
		if (("subscribe".equals(event) || "unsubscribe".equals(event)) && StringUtils.isBlank(eventKey)) {
			return new InFollowEvent(toUserName, fromUserName, createTime, msgType, event);
		}
		// 扫描带参数二维码事件之一		1: 用户未关注时，进行关注后的事件推送
		String ticket = root.elementText("Ticket");
		if ("subscribe".equals(event) && StringUtils.isNotBlank(eventKey) && eventKey.startsWith("qrscene_")) {
			InQrCodeEvent e = new InQrCodeEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			e.setTicket(ticket);
			return e;
		}
		// 微信摇一摇事件
		if ("ShakearoundUserShake".equals(event)){
			String pageId = root.elementText("ChosenPageId");
			Long chosenPageId = Long.valueOf(StringUtils.isNotBlank(pageId)? pageId : "0");
			InShakearoundUserShakeEvent e = new InShakearoundUserShakeEvent(toUserName, fromUserName, createTime, msgType,chosenPageId);
			e.setEvent(event);
			Element c = root.element("ChosenBeacon");
			e.setUuid(c.elementText("Uuid"));
			e.setMajor(Integer.parseInt(c.elementText("Major")));
			e.setMinor(Integer.parseInt(c.elementText("Minor")));
			e.setDistance(Float.parseFloat(c.elementText("Distance")));
			Element beacons = root.element("AroundBeacons");
			if(beacons != null){
				List list = root.element("AroundBeacons").elements("AroundBeacon");
				if (list != null && list.size() > 0) {
					AroundBeacon aroundBeacon = null;
					List<AroundBeacon> aroundBeacons = new ArrayList<AroundBeacon>();
					for (Iterator it = list.iterator(); it.hasNext();) {
						Element elm = (Element) it.next();
						aroundBeacon = new AroundBeacon();
						aroundBeacon.setUuid(elm.elementText("Uuid"));
						aroundBeacon.setMajor(Integer.parseInt(elm.elementText("Major")));
						aroundBeacon.setMinor(Integer.parseInt(elm.elementText("Minor")));
						aroundBeacon.setDistance(Float.parseFloat(elm.elementText("Distance")));
						aroundBeacon.setRssi(Float.parseFloat(elm.elementText("Rssi")==null?"-1":elm.elementText("Rssi")));
						aroundBeacon.setMeasure(Float.parseFloat(elm.elementText("Measurepower")==null?"-1":elm.elementText("Measurepower")));
						aroundBeacons.add(aroundBeacon);
					}
					e.setAroundBeaconList(aroundBeacons);
				}
			}
			return e;
		 }
		// 摇红包事件
		if ("ShakearoundLotteryBind".equals(event)){
			InShakearoundLotteryBindEvent e = new InShakearoundLotteryBindEvent(toUserName, fromUserName, createTime, msgType);
			e.setBindTime(root.elementText("BindTime"));
			e.setLotteryId(root.elementText("LotteryId"));
			e.setMoney(root.elementText("Money"));
			e.setTicket(root.elementText("Ticket"));
			return e;
		}
		// 上报地理位置事件
		if ("LOCATION".equals(event)) {
			InLocationEvent e = new InLocationEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setLatitude(root.elementText("Latitude"));
			e.setLongitude(root.elementText("Longitude"));
			e.setPrecision(root.elementText("Precision"));
			return e;
		}
		// 卡券审核事件推送
        if("card_pass_check".equals(event) || "card_not_pass_check".equals(event)){
            InCardPassCheckEvent e = new InCardPassCheckEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            return e;
        }
        // 卡券领取事件推送
        if("user_get_card".equals(event)){
            InCardUserGetEvent e = new InCardUserGetEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            String isGiveByFriend = root.elementText("IsGiveByFriend");
            e.setIsGiveByFriend(isGiveByFriend);
            if(StringUtils.isNotBlank(isGiveByFriend) && isGiveByFriend.equals("1")){
                e.setFriendUserName(root.elementText("FriendUserName"));
                e.setOldUserCardCode("OldUserCardCode");
            }
            e.setOuterId(root.elementText("OuterId"));            // 渠道值
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            return e;
        }
        
        // 卡券删除事件推送
        if("user_del_card".equals(event)){
            InCardUserDelEvent e = new InCardUserDelEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            return e;
        }
        // 卡券核销事件推送
        if("user_consume_card".equals(event)){
            InCardConsumeEvent e = new InCardConsumeEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            e.setConsumeSource(root.elementText("ConsumeSource"));
            e.setLocationId(root.elementText("LocationId"));
            e.setOutTradeNo(root.elementText("OutTradeNo"));
            e.setTransId(root.elementText("TransId"));
            e.setStaffOpenId(root.elementText("StaffOpenId"));
            return e;
        }
        // 卡券买单事件推送
        if("user_pay_from_pay_cell".equals(event)){
            InCardUserPayEvent e = new InCardUserPayEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            e.setLocationId(root.elementText("LocationId"));
            e.setTransId(root.elementText("TransId"));
            e.setFee(root.elementText("Fee"));
            e.setOriginalFee(root.elementText("OriginalFee"));
            return e;
        }
        // 进入会员卡事件推送
        if("user_view_card".equals(event)){
            InCardUserViewEvent e = new InCardUserViewEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            return e;
        }
        // 从卡券进入公众号会话事件推送
        if("user_enter_session_from_card".equals(event)){
            InCardUserEnterSessionEvent e = new InCardUserEnterSessionEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            return e;
        }
        // 会员卡内容更新事件
        if("update_member_card".equals(event)){
            InCardMemberUpdateEvent e = new InCardMemberUpdateEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setUserCardCode(root.elementText("UserCardCode"));  // 支持自定义code和非自定义code
            e.setModifyBalance(root.elementText("ModifyBonus"));
            e.setModifyBonus(root.elementText("ModifyBalance"));
            return e;
        }
        // 库存报警事件
        if("card_sku_remind".equals(event)){
            InCardRemindEvent e = new InCardRemindEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setCardId(root.elementText("CardId"));
            e.setDetail(root.elementText("Detail"));
            return e;
        }
        // 券点流水详情事件
        if("card_sku_remind".equals(event)){
            InCardPayOrderEvent e = new InCardPayOrderEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setOrderId(root.elementText("OrderId"));
            e.setOrderType(root.elementText("Status"));
            return e;
        }
        // 券点流水详情事件
        if("poi_check_notify".equals(event)){
            InPoiCheckNotifyEvent e = new InPoiCheckNotifyEvent(toUserName, fromUserName, createTime, msgType, event);
            e.setMsg(root.elementText("Msg"));
            e.setPoiId(root.elementText("PoiId"));
            e.setResult(root.elementText("Result"));
            e.setUniqId(root.elementText("UniqId"));
            return e;
        }
		
		// 扫描带参数二维码事件之二		2: 用户已关注时的事件推送
		if ("SCAN".equals(event)) {
			InQrCodeEvent e = new InQrCodeEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			e.setTicket(ticket);
			return e;
		}
		// 自定义菜单事件之一			1：点击菜单拉取消息时的事件推送
		if ("CLICK".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// 自定义菜单事件之二			2：点击菜单跳转链接时的事件推送
		if ("VIEW".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// 扫码推事件
		if ("scancode_push".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			Element scanCodeInfo = root.element("ScanCodeInfo");
			String scanType = scanCodeInfo.elementText("ScanType");
			String scanResult = scanCodeInfo.elementText("ScanResult");
			e.setScanCodeInfo(new ScanCodeInfo(scanType, scanResult));
			return e;
		}
		// 扫码推事件且弹出“消息接收中”提示框
		if ("scancode_waitmsg".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			Element scanCodeInfo = root.element("ScanCodeInfo");
			String scanType = scanCodeInfo.elementText("ScanType");
			String scanResult = scanCodeInfo.elementText("ScanResult");
			e.setScanCodeInfo(new ScanCodeInfo(scanType, scanResult));
			return e;
		}
		// 5. pic_sysphoto：弹出系统拍照发图，这个后台其实收不到该菜单的消息，点击它后，调用的是手机里面的照相机功能，而照相以后再发过来时，就收到的是一个图片消息了
		if ("pic_sysphoto".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_photo_or_album：弹出拍照或者相册发图
		if ("pic_photo_or_album".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_weixin：弹出微信相册发图器
		if ("pic_weixin".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// location_select：弹出地理位置选择器
		if ("location_select".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// media_id：下发消息（除文本消息）
		if ("media_id".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// view_limited：跳转图文消息URL
		if ("view_limited".equals(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setEventKey(eventKey);
			return e;
		}
		// 模板消息是否送达成功通知事件
		if ("TEMPLATESENDJOBFINISH".equals(event)) {
			InTemplateMsgEvent e = new InTemplateMsgEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setMsgId(root.elementText("MsgID"));
			e.setStatus(root.elementText("Status"));
			return e;
		}
		// 群发任务结束时是否送达成功通知事件
		if ("MASSSENDJOBFINISH".equals(event)) {
			InMassEvent e = new InMassEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setMsgId(root.elementText("MsgID"));
			e.setStatus(root.elementText("Status"));
			e.setTotalCount(root.elementText("TotalCount"));
			e.setFilterCount(root.elementText("FilterCount"));
			e.setSentCount(root.elementText("SentCount"));
			e.setErrorCount(root.elementText("ErrorCount"));
			return e;
		}
		// 多客服接入会话事件
		if ("kf_create_session".equals(event)) {
			InCustomEvent e = new InCustomEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setKfAccount(root.elementText("KfAccount"));
			return e;
		}
		// 多客服关闭会话事件
		if ("kf_close_session".equals(event)) {
			InCustomEvent e = new InCustomEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setKfAccount(root.elementText("KfAccount"));
			return e;
		}
		// 多客服转接会话事件
		if ("kf_switch_session".equals(event)) {
			InCustomEvent e = new InCustomEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setKfAccount(root.elementText("KfAccount"));
			e.setToKfAccount(root.elementText("ToKfAccount"));
			return e;
		}
		// 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
		if ("qualification_verify_success".equals(event) || "naming_verify_success".equals(event)
				 || "annual_renew".equals(event) || "verify_expired".equals(event)) {
			InVerifySuccessEvent e = new InVerifySuccessEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setExpiredTime(root.elementText("expiredTime"));
			return e;
		}
		// 资质认证失败 || 名称认证失败
		if ("qualification_verify_fail".equals(event) || "naming_verify_fail".equals(event)) {
			InVerifyFailEvent e = new InVerifyFailEvent(toUserName, fromUserName, createTime, msgType, event);
			e.setFailTime(root.elementText("failTime"));
			e.setFailReason(root.elementText("failReason"));
			return e;
		}

		throw new RuntimeException("无法识别的事件类型" + event + "，请查阅微信公众平台开发文档");
	}

}