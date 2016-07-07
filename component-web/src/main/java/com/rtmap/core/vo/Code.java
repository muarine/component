/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.vo;

/**
 * Code. 业务返回码
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年6月29日
 * @since 0.1
 */
public class Code {
    
    public static final int SUCCESS         = 200;  // 请求成功
    public static final int ERROR           = 500;  // 服务器异常
    public static final int INVALID_SIGN    = 300;  // 非法的签名
    public static final int NULL_PARAM      = 400;  // 参数不能为空
    public static final int INVALID_PARAM   = 401;  // 非法的参数
    public static final int EXITING         = 402;  // 重复数据
    public static final int NO_MATCHING     = 403;  // 暂无匹配数据
    
    /**
     * Account. 账户相关
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @date   2016年4月19日
     * @since 1.0.0
     */
    public static class Account{
        public static final int INVALID_USER_PWD    = 100;
        public static final int INVALID_OPERATION   = 101; 
        public static final int INVAILD_APPID       = 102;
    }
    
    public static class Card {

        public static final int ERROR               = 30001;
        public static final int EXISTING            = 30002;

    }


}
