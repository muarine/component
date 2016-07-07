/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.CardConstant;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.config.ResponseFactory;
import com.rtmap.core.domain.Card;
import com.rtmap.core.exp.Assert;
import com.rtmap.core.exp.BusinessException;
import com.rtmap.core.exp.EmptyArgumentException;
import com.rtmap.core.exp.InvalidArgumentException;
import com.rtmap.core.service.impl.CardService;
import com.rtmap.core.vo.CardParam;
import com.rtmap.core.vo.Code;
import com.rtmap.core.vo.Result;
import com.rtmap.utils.http.CheckUrlUtil;
import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.handler.CardHandler;
import com.rtmap.wx.sdk.model.card.MCard;

/**
 * CardAction.  微信卡券
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
@RequestMapping("/card")
@RestController
public class CardAction extends AbstractAction{
    @Autowired
    private CardService cardService;
    
    /**
     * 创建卡券
     * 
     * @param param
     * @return
     */
    @RequestMapping("/{authAppid}/create")
    public Result create(@PathVariable("authAppid") String authAppid , CardParam param , HttpServletRequest request){
        if(LOGGER.isDebugEnabled()) LOGGER.debug("/card/{authAppid}/create param: \n{}" , param.toString());
        LOGGER.info("charset:{}" , request.getCharacterEncoding());
        try {
            // 校验参数
            this._checkParam(param);
            // 校验promoid和prizeid
            com.rtmap.core.domain.Card card = cardService.getCardByPromoAndPrizeId(param.getPromoid() , param.getPrizeid());
            
            Assert.b_isTrue(null != card , Code.Card.EXISTING);
            // 装载Card实体
            MCard mCard = this._mountCard(param , authAppid);
            Integer fkAuthId = this.getAuthId(authAppid);
            // 创建卡券
            JsonNode node = CardHandler.instance.createCard(AuthManager.getAuthAccessToken(
                                                                            commonMap.get(MpConstant.APPID), 
                                                                            commonMap.get(MpConstant.APPSECRET), 
                                                                            authAppid), 
                                                            mCard);
            
            Assert.b_isTrue(node.get("errcode").asInt() != 0 , Code.Card.ERROR);
            String cardId = node.get("card_id").asText();
            // 入库
            cardService.createCard(param.getPromoid() , param.getPrizeid() , 
                                                    mCard , cardId , fkAuthId);
            Map<String,Object> map = new LinkedHashMap<String, Object>();
            map.put("authAppid", authAppid);
            map.put("card_id", cardId);
            // 返回结果
            return ResponseFactory.build(map);
            
        } catch (EmptyArgumentException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(Code.NULL_PARAM , e.getMessage());
        } catch (InvalidArgumentException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(Code.INVALID_PARAM , e.getMessage());
        } catch (BusinessException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(e.getCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage() , e);
        }
        return ResponseFactory.build(Code.ERROR);
    }
    
    /**
     * 根据营销平台活动维度
     * 
     * @param authAppid 授权方appid
     * @param param     红包参数
     * @return
     */
    @RequestMapping("/list/state")
    public Result getPromoCardList(CardParam param){
        if(LOGGER.isDebugEnabled()) LOGGER.debug("/card/list/state param: \n{}" , param.toString());
        try {
            Integer promoid = param.getPromoid();
            Assert.notNull(param.getPromoid() , "promoid");
            List<Card> cards = cardService.stateList(promoid);
            List<Map<String,Object>> returns = new ArrayList<Map<String,Object>>();
            if(null != cards && !cards.isEmpty()){
                for (Card card : cards) {
                    Map<String,Object> map = new LinkedHashMap<String,Object>();
                    map.put("promoid", card.getFkPromoId());
                    map.put("prizeid", card.getFkPrizeId());
                    map.put("cardid", card.getCardId());
                    map.put("state", card.getState());
                    returns.add(map);
                }
            }
            // 返回结果
            return ResponseFactory.build(returns);
        } catch (EmptyArgumentException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(Code.NULL_PARAM , e.getMessage());
        } catch (InvalidArgumentException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(Code.INVALID_PARAM , e.getMessage());
        } catch (BusinessException e) {
            LOGGER.debug(e.getMessage());
            return ResponseFactory.build(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage() , e);
        }
        return ResponseFactory.build(Code.ERROR);
    }
    
    /**
     * 
     * 
     * @param authAppid 授权方appid
     * @param param     红包参数
     * @return
     */
    @RequestMapping("/{authAppid}/consume")
    public Result consumeCode(@PathVariable("authAppid") String authAppid , CardParam param){
        if(LOGGER.isDebugEnabled()) LOGGER.debug("/card/{authAppid}/consume params: \n{}" , param.toString());
        try {
            Assert.notNull(param.getPromoid() , "promoid");
            Assert.notNull(param.getPrizeid() , "prizeid");
            Assert.hasText(param.getCode() , "code");
            Card card = cardService.getCardByPromoAndPrizeId(param.getPromoid(), param.getPrizeid());
            Assert.b_isTrue(card == null, Code.NO_MATCHING);
            String cardId   = card.getCardId();
            String code     = param.getCode();
            // 对接微信核销
            JsonNode node = CardHandler.instance.consumeCode(
                                                        AuthManager.getAuthAccessToken(commonMap.get(MpConstant.APPID), 
                                                                                       commonMap.get(MpConstant.APPSECRET), 
                                                                                       authAppid), 
                                                        cardId, 
                                                        code);
            LOGGER.info(node.toString());
        } catch (EmptyArgumentException e) {
            LOGGER.debug(e.getMessage());
        } catch (InvalidArgumentException e) {
            LOGGER.debug(e.getMessage());
        } catch (BusinessException e) {
            LOGGER.debug(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage() , e);
        }
        return ResponseFactory.build();
    }
    
    

    /**
     * 将传递的卡券参数装载进MCard
     * 
     * @return
     * @throws IOException 
     */
    private MCard _mountCard(CardParam param , String authAppid) throws IOException {
        MCard mcard = new MCard().initGeneralCoupon(param.getDefault_detail());
        
        // 上传卡券logo
        JsonNode node = CardHandler.instance.uploadLogo(
                                    AuthManager.getAuthAccessToken(
                                                commonMap.get(MpConstant.APPID) , 
                                                commonMap.get(MpConstant.APPSECRET) , 
                                                authAppid), param.getLogo_url());
        Integer card_type = param.getCard_type();
        switch (card_type) {
        case 1:
            mcard.setCard_type(CardConstant.Type.GROUPON);
            mcard.setDefault_detail(param.getDefault_detail());
            break;
        case 2:
            mcard.setCard_type(CardConstant.Type.CASH);
            mcard.setLeast_cost(param.getLeast_cost());
            mcard.setReduce_cost(param.getReduce_cost());
            break;
        case 3:
            mcard.setCard_type(CardConstant.Type.DISCOUNT);
            mcard.setDiscount(param.getDiscount());
            break;
        case 4:
            mcard.setCard_type(CardConstant.Type.GIFT);
            mcard.setGift(param.getGift());
            break;
        case 5:
            mcard.setCard_type(CardConstant.Type.GENERAL_COUPON);
            mcard.setDefault_detail(param.getDefault_detail());
            break;
        }
        
        mcard.setLogo_url(node.get("url").asText());
        mcard.setBrand_name(param.getBrand_name());
        mcard.setTitle(param.getTitle());
        mcard.setSub_title(param.getSub_title());
        mcard.setCode_type(param.getCode_type());
        mcard.setColor(param.getColor());
        mcard.setNotice(param.getNotice());
        mcard.setDescription(param.getDescription());
        mcard.setSku(param.getSku());
        mcard.setDate_info_Range(param.getBegintime(), param.getEndtime());
        mcard.setUse_custom_code(param.getUse_custom_code());
        mcard.setBind_openid(param.getBind_openid());
        mcard.setSource(param.getSource());
        mcard.setGet_limit(param.getGet_limit());
        
        mcard.setService_phone(param.getService_phone());
        mcard.setCan_share(param.getCan_share());
        mcard.setCan_give_friend(param.getCan_give_friend());
        mcard.setBind_openid(param.getBind_openid());
        
        
        return mcard;
    }

    private void _checkParam(CardParam param) {
        Integer cardType = param.getCard_type();
        // 校验非空参数
        Assert.notNull(param.getPromoid() , "promoid");
        Assert.notNull(param.getPrizeid() , "prizeid");
        Assert.notNull(param.getLogo_url() , "logo_url");
        Assert.notNull(cardType , "card_type");
        Assert.notNull(param.getBrand_name() , "brand_name");
        Assert.notNull(param.getTitle() , "title");
        Assert.notNull(param.getColor() , "color");
        Assert.notNull(param.getNotice() , "notice");
        Assert.notNull(param.getDescription() , "description");
        Assert.notNull(param.getSku() , "sku");
        Assert.notNull(param.getBegintime() , "begintime");
        Assert.notNull(param.getEndtime() , "endtime");
        
        // 校验参数值
        Assert.isTrue(!CheckUrlUtil.isEffectiveUrl(param.getLogo_url()) , "logo_url can not access");
        Assert.isTrue(!CheckUrlUtil.isImageUrl(param.getLogo_url()) , "logo_url suffix must be .png|.jpeg|.jpg|.bmp , such as http://res.rtmap.com/");
        Assert.isTrue(param.getCard_type() <= 0 || param.getCard_type() > 5 , "card_type must gt 0 and lt 6");
        Assert.isTrue(param.getBrand_name().length() > 12 , "brand_name length must not more than 12");
        Assert.isTrue(param.getTitle().length() > 9 , "title length is not more than 9");
        Assert.isTrue(!StringUtils.checkCardColor(param.getColor()) , "color is invalid");
        Assert.isTrue(param.getNotice().length() > 48 , "notice length is not more than 48");
        Assert.isTrue(param.getDescription().length() > 3072 , "descirtion length is not more than 3072");
        Assert.isTrue(param.getSku() < 0 || param.getSku() > 100000000 , "sku must gt 0 and lt 100000000 ");
        Assert.isTrue(String.valueOf(param.getBegintime()).length() > 10 || 
                String.valueOf(param.getEndtime()).length() > 10 , "begintime or endtime field must only accurate to second");
        Assert.isTrue(param.getBegintime() >= param.getEndtime() , "begintime must less than endtime");
        Assert.isTrue(param.getEndtime() <= System.currentTimeMillis()/1000 , "endtime must more than currenttime");
        Assert.isTrue(param.getGet_limit() < 1 , "get_limit must more than 0");
        // 校验优惠券类型
        switch (cardType) {
        case 1: // 团购券
            Assert.hasText(param.getDeal_detail() , "card_type:groupon , contain field 'deal_detail'");
            break;
        case 2: // 代金券
            Assert.isTrue(param.getLeast_cost() == null 
                    || param.getReduce_cost() == null
                    || param.getLeast_cost() < 0
                    || param.getReduce_cost() <= 0 , "card_type:cash , contain field 'least_cost' and 'reduce_cost' must not be empty");
        case 3: // 折扣券
            Assert.notNull(param.getDiscount() == null , "card_type:discount , contain field 'discount'");
            Assert.isTrue(param.getDiscount() < 1 || param.getDiscount() >= 100 , "card_type:discount , contain field 'discount' must more than 0 , less than 100.");
            break;
        case 4: // 兑换券
            Assert.hasText(param.getGift() , "card_type:gift , contain field 'gift'");
            break;
        case 5: // 优惠券
            Assert.hasText(param.getDefault_detail() , "card_type:general_coupon , contain field 'default_detail'");
            break;
        default:
            break;
        }
    }
    
    
}
