/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.vo;

import java.lang.reflect.Field;

/**
 * CardParam.   卡券参数
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
public class CardParam extends Param {
    // 网页ticket
    private String card_id;
    private String code;
    
    // 营销平台
    private Integer promoid;
    private Integer prizeid;
    
    private Integer card_type;
    // 1. 团购券
    private String deal_detail;
    // 2. 代金券
    private Integer least_cost;
    private Integer reduce_cost;
    // 3. 折扣券
    private Integer discount;
    // 4. 兑换券
    private String gift;
    // 5. 优惠券
    private String default_detail;
    
    // 卡券基础信息
    private String logo_url;
    private String code_type = "CODE_TYPE_ONLY_QRCODE";
    private String brand_name;
    private String title;
    private String sub_title;
    private String color = "Color010";
    private String notice;
    private String description;
    private Integer sku = 0;
    private Long begintime;
    private Long endtime;
    private String service_phone;
    private String source = "营销平台";
    private Boolean use_custom_code = true;
    private Boolean bind_openid = true;
    private Integer get_limit = 50;
    private Boolean can_share = false;
    private Boolean can_give_friend = false;
    
    public String getLogo_url() {
        return logo_url;
    }
    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
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
    public Integer getSku() {
        return sku;
    }
    public void setSku(Integer sku) {
        this.sku = sku;
    }
    public Long getBegintime() {
        return begintime;
    }
    public void setBegintime(Long begintime) {
        this.begintime = begintime;
    }
    public Long getEndtime() {
        return endtime;
    }
    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }
    public String getService_phone() {
        return service_phone;
    }
    public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Integer getGet_limit() {
        return get_limit;
    }
    public void setGet_limit(Integer get_limit) {
        this.get_limit = get_limit;
    }
    public String getDeal_detail() {
        return deal_detail;
    }
    public void setDeal_detail(String deal_detail) {
        this.deal_detail = deal_detail;
    }
    public String getCard_id() {
        return card_id;
    }
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getCard_type() {
        return card_type;
    }
    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
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
    public String getCode_type() {
        return code_type;
    }
    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }
    public String getSub_title() {
        return sub_title;
    }
    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
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
    public Boolean getUse_custom_code() {
        return use_custom_code;
    }
    public void setUse_custom_code(Boolean use_custom_code) {
        this.use_custom_code = use_custom_code;
    }
    public Boolean getBind_openid() {
        return bind_openid;
    }
    public void setBind_openid(Boolean bind_openid) {
        this.bind_openid = bind_openid;
    }
    public Integer getPromoid() {
        return promoid;
    }
    public void setPromoid(Integer promoid) {
        this.promoid = promoid;
    }
    public Integer getPrizeid() {
        return prizeid;
    }
    public void setPrizeid(Integer prizeid) {
        this.prizeid = prizeid;
    }
    @Override
    public String toString() {
        Class<?> clazz = this.getClass();
        Class<?> superClazz = this.getClass().getSuperclass();
        StringBuffer buffer = new StringBuffer();
        
        Field[] superFields = superClazz.getDeclaredFields();
        for (Field field : superFields) {
            String varName = field.getName();  
            try {
                boolean isAccess = field.isAccessible();
                if(!isAccess) field.setAccessible(true);
                Object o = field.get(this);
                if(o == null) continue;
                buffer.append("\n").append("'"+varName+"'：").append(o).append("\n");
                if(!isAccess) field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();  
            try {
                boolean isAccess = field.isAccessible();
                if(!isAccess) field.setAccessible(true);
                Object o = field.get(this);
                if(o == null) continue;
                buffer.append("'"+varName+"'：").append(o).append("\n");
                if(!isAccess) field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
