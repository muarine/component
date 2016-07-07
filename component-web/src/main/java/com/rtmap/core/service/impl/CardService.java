/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtmap.core.domain.Card;
import com.rtmap.core.mapper.CardMapper;
import com.rtmap.wx.sdk.model.card.MCard;

/**
 * CardService. 卡券业务逻辑
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月15日
 * @since 1.0.0
 */
@Service
public class CardService {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(CardService.class);
    @Autowired
    private CardMapper cardMapper;
    
    /**
     * 创建卡券
     *
     */
    public void createCard(Integer promoId , Integer prizeId , MCard mCard , String cardId , Integer fkAuthId){
        
        // 装载数据库实体
        Card card = new Card();
        card.setFkPromoId(promoId);
        card.setFkPrizeId(prizeId);
        card.setCardId(cardId);
        card.setCardType(mCard.getCard_type());
        card.setBaseInfo(mCard.getBaseInfo());
        card.setAdvancedInfo(mCard.getAdvancedInfo());
        card.setExtInfo(mCard.getExtInfo());
        card.setCreateTime(new Date());
        card.setFkAuthId(fkAuthId);
        card.setState(1);
        card.setStockValue(mCard.getQuantity());
        cardMapper.insert(card);
    }

    /**
     * 卡券实体
     * @param promoid
     * @param prizeid
     */
    public Card getCardByPromoAndPrizeId(Integer promoid, Integer prizeid) {
        return cardMapper.getCard(promoid , prizeid);
    }

    /**
     * 更新卡券状态
     * 
     * @param cardId
     * @param checkPass
     */
    public void cardPass(String cardId, Integer state) {
        Integer result = cardMapper.updateState(cardId, state);
        LOGGER.info("update card state :" , result);
    }

    /**
     * 按活动维度查询卡券状态列表
     * card_id , prize_id , state 
     * 
     * @param promoid
     * @return
     */
    public List<Card> stateList(Integer promoid) {
        return cardMapper.getStateList(promoid);
    }
    
}
