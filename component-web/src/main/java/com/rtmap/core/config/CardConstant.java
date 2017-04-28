/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.config;

/**
 * CardConstant.    卡券常量
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月18日
 * @since 1.0.0
 */
public class CardConstant {
    
    /**
     * 
     * Type. 卡券类型
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @date   2016年4月19日
     * @since 1.0.0
     */
    public static class Type{
        /**
         * 代金券
         */
        public final static String CASH = "cash";
        /**
         * 团购券
         */
        public final static String GROUPON = "groupon";
        /**
         * 优惠券
         */
        public final static String GENERAL_COUPON = "general_coupon";
        /**
         * 兑换券
         */
        public final static String GIFT = "gift";
        /**
         * 折扣券
         */
        public final static String DISCOUNT = "discount"; 
    }
    /**
     * State.    卡券状态GENERAL_COUPON
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @date   2016年4月19日
     * @since 1.0.0
     */
    public static class State{
        /**
         * 待审核
         */
        public final static Integer WAITING_CHECK = 1;
        /**
         * 审核失败
         */
        public final static Integer CHECK_FAIL = 2;
        /**
         * 审核通过
         */
        public final static Integer CHECK_PASS = 3;
        /**
         * 已删除
         */
        public final static Integer DELETED = 4;
    }
    
    
}
