/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.domain;

/**
 * Card.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月8日
 * @since 1.0.0
 */
public class Card extends AbstractEntity {

    /** The serialVersionUID */
    private static final long serialVersionUID = 6317980140167494063L;
    
    private Integer fkPromoId;
    private Integer fkPrizeId;
    private Integer fkAuthId;
    private String cardId;
    private String cardType;
    private String baseInfo;
    private String advancedInfo;
    private String extInfo;
    private Integer stockValue;
    private Integer state;
    
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getBaseInfo() {
        return baseInfo;
    }
    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }
    public String getAdvancedInfo() {
        return advancedInfo;
    }
    public void setAdvancedInfo(String advancedInfo) {
        this.advancedInfo = advancedInfo;
    }
    public String getExtInfo() {
        return extInfo;
    }
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getFkAuthId() {
        return fkAuthId;
    }
    public void setFkAuthId(Integer fkAuthId) {
        this.fkAuthId = fkAuthId;
    }
    public Integer getStockValue() {
        return stockValue;
    }
    public void setStockValue(Integer stockValue) {
        this.stockValue = stockValue;
    }
    public Integer getFkPromoId() {
        return fkPromoId;
    }
    public void setFkPromoId(Integer fkPromoId) {
        this.fkPromoId = fkPromoId;
    }
    public Integer getFkPrizeId() {
        return fkPrizeId;
    }
    public void setFkPrizeId(Integer fkPrizeId) {
        this.fkPrizeId = fkPrizeId;
    }
    
}
