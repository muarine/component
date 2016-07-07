/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model.card;

/**
 * MCard.    
 * 券种(
 * <pre>
 * 代金券-cash
 * 团购-groupon
 * 优惠-general_coupon
 * 兑换-gift
 * 折扣-discount
 * )
 * </pre>
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class MCard extends AbstractCard {
    
    public final static String TEMPLATE = load(CARD_JSON_PATH);
    // 团购券
    private String deal_detail;         // 团购详情
    // 代金券
    private Integer least_cost;         // 表示起用金额（单位为分）,如果无起用门槛则填0。
    private Integer reduce_cost;        // 表示减免金额,(单位为分）
    // 折扣券
    private Integer discount;           // 折扣券专用，表示打折额度（百分比）。
    // 兑换券
    private String gift;                // 填写兑换内容的名称。
    // 优惠券
    private String default_detail;      // 填写优惠详情
    
    // 扩展字段
    private String baseInfo;
    private String advancedInfo;
    
    /**
     * 团购券
     * 
     */
    public MCard initGroupon(String deal_detail) {
        this.setCard_type(CT_GROUPON);
        this.setDeal_detail(deal_detail);
        return this;
    }
    /**
     * 代金券
     */
    public MCard initCash(Integer least_cost , Integer reduce_cost) {
        this.setCard_type(CT_CASH);
        this.setLeast_cost(least_cost);
        this.setReduce_cost(reduce_cost);
        return this;
    }
    /**
     * 折扣券
     */
    public MCard initDiscount(Integer discount) {
        this.setCard_type(CT_DISCOUNT);
        this.setDiscount(discount);
        return this;
    }
    /**
     * 兑换券
     */
    public MCard initGift(String gift) {
        this.setCard_type(CT_GIFT);
        this.setGift(gift);
        return this;
    }
    /**
     * 优惠券
     */
    public MCard initGeneralCoupon(String default_detail) {
        this.setCard_type(CT_GENERAL_COUPON);
        this.setDefault_detail(default_detail);
        return this;
    }

    public String getDeal_detail() {
        return deal_detail;
    }

    public void setDeal_detail(String deal_detail) {
        this.deal_detail = deal_detail;
    }

    public Integer getLeast_cost() {
        return least_cost;
    }

    public void setLeast_cost(Integer least_cost) {
        this.least_cost = least_cost;
    }

    public Integer getReduce_cost() {
        return reduce_cost;
    }

    public void setReduce_cost(Integer reduce_cost) {
        this.reduce_cost = reduce_cost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getDefault_detail() {
        return default_detail;
    }

    public void setDefault_detail(String default_detail) {
        this.default_detail = default_detail;
    }
    
    /**
     * 受券类型影响，可以是：default_detail，gift，discount，reduce_cost
     * 
     * @return
     */
    public String getExtInfo() {
        String extInfo = null;
        if(card_type.equals(CT_CASH)){
            extInfo = "{\"least_cost\":\""+least_cost+"\",\"reduce_cost\":\""+reduce_cost+"\"}";
        }else if(card_type.equals(CT_DISCOUNT)){
            extInfo = "{\"discount\":"+discount+"}";
        }else if(card_type.equals(CT_GENERAL_COUPON)){
            extInfo = "{\"default_detail\":\""+default_detail+"\"}";
        }else if(card_type.equals(CT_GIFT)){
            extInfo = "{\"gift\":\""+gift+"\"}";
        }else if(card_type.equals(CT_GROUPON)){
            extInfo = "{\"deal_detail\":\""+deal_detail+"\"}";
            
        }else{
            throw new RuntimeException("system can not unrecognized about the card_type:" + card_type);
        }
        return extInfo;
    }
    
    /**
     * 
     * 设置基本信息
     * 
     * @param baseInfo  json数据格式
     * @return
     */
    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }
    
    /**
     * 基本信息
     * 
     * @return
     */
    public String getBaseInfo() {
        return baseInfo;
    }
    
    /**
     * 设置高级字段数据
     * @param advancedInfo 高级属性，json格式
     * @return
     */
    public void setAdvancedInfo(String advancedInfo) {
        this.advancedInfo = advancedInfo;
    }
    
    /**
     * 获取高级属性信息,json格式
     * @return
     */
    public String getAdvancedInfo() {
        return advancedInfo;
    }

    
}
