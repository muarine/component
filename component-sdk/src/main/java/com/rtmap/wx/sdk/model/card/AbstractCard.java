/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model.card;

import java.util.List;

/**
 * AbstarctCard.    卡券公共类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public abstract class AbstractCard extends AbstractModel{
    protected final static String CT_GROUPON              = "groupon";
    protected final static String CT_CASH                 = "cash";
    protected final static String CT_DISCOUNT             = "discount";
    protected final static String CT_GIFT                 = "gift";
    protected final static String CT_GENERAL_COUPON       = "general_coupon";
    
    private final static String DATE_INFO_RANGE     = "{\"type\":\"%s\",\"begin_timestamp\":%d,\"end_timestamp\":%d}"; 
    private final static String DATE_INFO_TERM      = "{\"type\":\"%s\",\"fixed_term\":%d,\"begin_timestamp\":%d,\"end_timestamp\":%d}"; 
    private final static String SKU         = "{\"quantity\":%d}"; 
    private final static String USE_CONDITION = "{\"accept_category\":\"%s\",\"reject_category\":\"%s\",\"can_use_with_other_discount\":%b}"; 
    // 卡券类型
    protected String card_type;
    
    // 基本信息，必填
    
    protected String logo_url;
    protected String code_type;
    protected String brand_name;
    protected String title;
    protected String sub_title; // 可不填
    protected String color;
    protected String notice;
    protected String description;
    protected String sku;       // json结构
    protected Integer quantity;
    protected String date_info;
    protected int fixed_term; 
    protected int fixed_begin_term;
    
    
    // 非必填
    protected boolean use_custom_code;
    protected String get_custom_code_mode;
    protected boolean bind_openid;
    protected String service_phone;
    protected String[] location_id_list;
    protected String source;
    protected String custom_url_name;
    protected String center_title;
    protected String center_sub_title;
    protected String center_url;
    protected String custom_url;
    protected String custom_url_sub_title;
    protected String promotion_url_name;
    protected String promotion_url;
    protected String promotion_url_sub_title;
    protected Integer get_limit;
    protected Boolean can_share;
    protected Boolean can_give_friend;
    
    
    // Advanced_info 卡券高级字段
    /**
     *          {
                   "accept_category": "鞋类",
                   "reject_category": "阿迪达斯",
                   "can_use_with_other_discount": true
                }
     */
    // 必填
    protected String use_condition;  
    // 复合对象
    protected Abstract stract = new Abstract();
    protected List<TextImg> text_img_list;
    
    // 非必填
    protected String[] business_service;
    protected List<TimeLimit> time_limit;
    
    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getCode_type() {
        return code_type;
    }

    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(Integer quantity) {
        this.sku = String.format(SKU , quantity);
        this.setQuantity(quantity);
    }
    
    public String getDate_info() {
        return date_info;
    }
    
    /**
     * 构建dateinfo字段的json    
     * @param type  DATE_TYPE_FIX_TIME_RANGE 
     * @param begin_timestamp
     * @param end_timestamp
     */
    public void setDate_info_Range(Long begin_timestamp , Long end_timestamp) {
        this.date_info = String.format(DATE_INFO_RANGE , "DATE_TYPE_FIX_TIME_RANGE" , begin_timestamp , end_timestamp);
    }
    
    /**
     * 构建dateinfo字段的json 
     * @param type  DATE_TYPE_FIX_TERM
     * @param begin_timestamp
     * @param end_timestamp
     */
    public void setDate_info_Term(Integer fixed_term , Long begin_timestamp , Long end_timestamp) {
        this.date_info = String.format(DATE_INFO_TERM , "DATE_TYPE_FIX_TERM" , fixed_term , begin_timestamp , end_timestamp);
    }

    public int getFixed_term() {
        return fixed_term;
    }

    public void setFixed_term(int fixed_term) {
        this.fixed_term = fixed_term;
    }

    public int getFixed_begin_term() {
        return fixed_begin_term;
    }

    public void setFixed_begin_term(int fixed_begin_term) {
        this.fixed_begin_term = fixed_begin_term;
    }

    public boolean isUse_custom_code() {
        return use_custom_code;
    }

    public void setUse_custom_code(boolean use_custom_code) {
        this.use_custom_code = use_custom_code;
    }

    public boolean isBind_openid() {
        return bind_openid;
    }

    public void setBind_openid(boolean bind_openid) {
        this.bind_openid = bind_openid;
    }

    public String getService_phone() {
        return service_phone;
    }

    public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
    }

    public String[] getLocation_id_list() {
        return location_id_list;
    }

    public void setLocation_id_list(String[] location_id_list) {
        this.location_id_list = location_id_list;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustom_url_name() {
        return custom_url_name;
    }

    public void setCustom_url_name(String custom_url_name) {
        this.custom_url_name = custom_url_name;
    }

    public String getCenter_title() {
        return center_title;
    }

    public void setCenter_title(String center_title) {
        this.center_title = center_title;
    }

    public String getCenter_sub_title() {
        return center_sub_title;
    }

    public void setCenter_sub_title(String center_sub_title) {
        this.center_sub_title = center_sub_title;
    }

    public String getCenter_url() {
        return center_url;
    }

    public void setCenter_url(String center_url) {
        this.center_url = center_url;
    }

    public String getCustom_url() {
        return custom_url;
    }

    public void setCustom_url(String custom_url) {
        this.custom_url = custom_url;
    }

    public String getCustom_url_sub_title() {
        return custom_url_sub_title;
    }

    public void setCustom_url_sub_title(String custom_url_sub_title) {
        this.custom_url_sub_title = custom_url_sub_title;
    }

    public String getPromotion_url_name() {
        return promotion_url_name;
    }

    public void setPromotion_url_name(String promotion_url_name) {
        this.promotion_url_name = promotion_url_name;
    }

    public String getPromotion_url() {
        return promotion_url;
    }

    public void setPromotion_url(String promotion_url) {
        this.promotion_url = promotion_url;
    }

    public String getPromotion_url_sub_title() {
        return promotion_url_sub_title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPromotion_url_sub_title(String promotion_url_sub_title) {
        this.promotion_url_sub_title = promotion_url_sub_title;
    }

    public Integer getGet_limit() {
        return get_limit;
    }

    public void setGet_limit(Integer get_limit) {
        this.get_limit = get_limit;
    }

    public Boolean getCan_share() {
        return can_share;
    }

    public void setCan_share(Boolean can_share) {
        this.can_share = can_share;
    }

    public Boolean getCan_give_friend() {
        return can_give_friend;
    }

    public void setCan_give_friend(Boolean can_give_friend) {
        this.can_give_friend = can_give_friend;
    }

    public String getUse_condition() {
        return use_condition;
    }

    public void setUse_condition(String accept_category , String reject_category , Boolean can_use_with_other_discount) {
        this.use_condition = String.format(USE_CONDITION , accept_category , reject_category , can_use_with_other_discount );
    }

    public String[] getBusiness_service() {
        return business_service;
    }

    public void setBusiness_service(String[] business_service) {
        this.business_service = business_service;
    }

    public List<TimeLimit> getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(List<TimeLimit> time_limit) {
        this.time_limit = time_limit;
    }

    public Abstract getStract() {
        return stract;
    }

    public void setStract(Abstract stract) {
        this.stract = stract;
    }

    public List<TextImg> getText_img_list() {
        return text_img_list;
    }

    public void setText_img_list(List<TextImg> text_img_list) {
        this.text_img_list = text_img_list;
    }

    public String getGet_custom_code_mode() {
        return get_custom_code_mode;
    }

    public void setGet_custom_code_mode(String get_custom_code_mode) {
        this.get_custom_code_mode = get_custom_code_mode;
    }
    
}
