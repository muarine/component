/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2017 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.constant;

/**
 * @author maoyun@rtmap.com
 * @project component-parent
 * @package com.rtmap.core.config
 * @date 4/28/17
 */
public enum CardTypeEnum {

    GROUPON(1, "groupon", "优惠券"),
    CASH(2, "cash", "代金券"),
    DISCOUNT(3, "discount", "折扣券"),
    GIFT(4, "gift", "礼品券"),
    GENERAL_COUPON(5, "general_coupon", "代金券");

    private int type;
    private String code;
    private String desc;

    CardTypeEnum(int type, String code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据type 获得 券种类型
     * @param type
     * @return
     */
    public static String getDesc(int type){

        for (CardTypeEnum typeEnum : CardTypeEnum.values()){
            if (typeEnum.getType() == type){
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static CardTypeEnum get(int type){
        for (CardTypeEnum typeEnum : CardTypeEnum.values()){
            if (typeEnum.getType() == type){
                return typeEnum;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
