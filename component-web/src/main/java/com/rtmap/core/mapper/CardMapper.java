package com.rtmap.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rtmap.core.domain.Card;

/**
 * 
 * CardMapper. 卡券
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
public interface CardMapper {
    
    Integer insert(Card card);
    
    Integer updateState(@Param("cardid") String cardid , @Param("state") Integer state);
    
    Integer increaseStock(@Param("id") Integer id , @Param("increase") Integer increase);

    Card getCard(@Param("fkPromoId") Integer fkPromoId,@Param("fkPrizeId") Integer fkPrizeId);

    List<Card> getStateList(Integer fkPromoId);
    
}