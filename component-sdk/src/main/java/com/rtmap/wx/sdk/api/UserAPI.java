/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

/**
 * UserAPI. 用户管理
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class UserAPI {
    
  //获取账号粉丝信息
    private static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    
    //获取账号粉丝列表
    private static final String GET_FANS_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
    
    /**
     * 获取粉丝信息接口
     * @param token
     * @param openid
     * @return
     */
    public static String getFansInfoUrl(String token,String openid){
        return String.format(GET_FANS_INFO, token, openid);
    }
    /**
     * 获取粉丝列表接口
     * @param token
     * @param nextOpenId
     * @return
     */
    public static String getFansListUrl(String token,String nextOpenId){
        return String.format(GET_FANS_LIST, token, nextOpenId);
    }
}
