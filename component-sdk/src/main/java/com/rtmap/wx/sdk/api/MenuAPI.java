/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

/**
 * MenuAPI. 自定义菜单
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class MenuAPI {
    
    /**
     * 创建菜单
     */
    private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    
    /**
     * 删除菜单
     */
    private static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
    
    /**
     * 获取菜单创建接口
     * @param token
     * @return
     */
    public static String getMenuCreateUrl(String token){
        return String.format(MENU_CREATE, token);
    }
    /**
     * 删除菜单 
     * @param access_token
     * @return
     */
    public static String getMenuDelete(String access_token) {
        return String.format(MENU_DELETE , access_token);
    }
    
    
}
