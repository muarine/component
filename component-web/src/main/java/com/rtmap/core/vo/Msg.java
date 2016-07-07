/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Codec.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月19日
 * @since 1.0.0
 */
public class Msg {
    private static final Map<Integer,String> msg = new HashMap<Integer,String>();
    
    static{
        // 账户相关
        msg.put(100, "unknown user name or bad password.");
        msg.put(101, "illegal operation.");
        msg.put(102, "invalid authorizer appid.");
        msg.put(103, "appid or appsecret is invalid.");
        msg.put(104, "access token have failed or expired.");
        msg.put(105, "you not have operation permissions.");
        // 系统常用
        msg.put(200, "success.");
        msg.put(300, "signature verification failed.");
        msg.put(400, "parameters may not be empty.");
        msg.put(401, "invalid parameter.");
        msg.put(402, "already existing data.");
        msg.put(403, "no matching data.");
        msg.put(500, "system error.");
        // 卡券相关
        msg.put(30001, "create weixin card fail.");
        msg.put(30002, "the card already exsiting , please can not repeat commit.");
        
    }
    
    public static String getErrMsg(Integer errcode){
        return msg.get(errcode);
    }
    
}
