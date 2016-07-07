/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.cache;

/**
 * KeyConfig.  缓存相关配置信息  单位：秒    |   缓存KEY
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class KeyConfig {
    
    /**
     * 一天
     */
    public static final Integer DAY_1 = 60*60*24;
    /**
     * 一个月
     */
    public static final Integer MONTH_1 = 60*60*24*30;
    /**
     * 十五分钟
     */
    public static final Integer MINUTE_10 = 60*15;
    /**
     * 一个小时
     */
    public static final Integer HOUR_1 = 60*60;
    /**
     * 两个小时
     */
    public static final Integer HOUR_2 = 60*60*2;
    
    /**
     * 平台相关key
     * Component_Key.
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class Component_Key{
        /**
         * 公众号平台access_token
         */
        public static final String Component_A_T    = "Component_A_T";
        /**
         * 公众号平台verify_ticket
         */
        public static final String Component_V_T    = "Component_V_T";
        /**
         * 授权公众号的auth_access_token
         */
        public static final String Component_A_A_T  = "Component_Authorier_%s_A_T";
        /**
         * 授权公众号的auth_refresh_token
         */
        public static final String Component_A_R_T  = "Component_Authorier_%s_R_T";
    }
    
    /**
     * 授权账户相关key
     * Authorizer_key.
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class Authorizer_key{
        /**
         * 授权信息
         * %s 表示authAppid
         */
        public static final String INFO         = "Component_Authorier_%s_Info";
        /**
         * 主键ID
         * %s 表示authAppid
         */
        public static final String PRIMARY_ID   = "Component_Authorier_Appid_%s";
        /**
         * 是否有效
         * %s 表示authAppid
         */
        public static final String ISLEGAL      = "Component_Authorier_Appid_%s_Islegal";
    }
    
    /**
     * 
     * JSSDK_key.   JSSDK相关缓存key
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class JSSDK_key{
        
        /**
         * jsapi_ticket 缓存key
         */
        public static final String JS_TICKET_KEY    = "Component_Authorier_%s_Jsapi_Ticket";
        /**
         * wx_card ticket 缓存key
         */
        public static final String CARD_TICKET_KEY  = "Component_Authorier_%s_Card_Ticket";
        /**
         * 网页授权的access_token，这与基础支持中的access_token不同
         */
        public static final String JS_ACCESS_TOKEN  = "Component_Authorier_%s_Web_A_T";
        /**
         * 网页授权前置跳转时存入自定义参数：State
         */
        public static final String Oauth_State      = "Component_Authorizer_%s_State_%s";
        
    }

    public static class Lbs_BeaconInfo {
        /**
         * 公众号下粉丝摇一摇定位结果
         */
        public static final String Authorizer_Openid_Shake_Result    = "Component_Authorier_%s_%s_Shake_Result";
    }
}
