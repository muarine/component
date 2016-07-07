/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.wx.sdk.api.MenuAPI;

/**
 * MenuHandler.  菜单处理
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class MenuHandler extends AbstractHandler{
    
    private static class SingleTonHolder{
        private final static MenuHandler INSTANCE = new MenuHandler(); 
    }
    
    private MenuHandler(){}
    
    public static MenuHandler instance = SingleTonHolder.INSTANCE;
    
    /**
     * 创建菜单 
     * @return
     */
    public JsonNode createMenu(String access_token , Object postdata){
        
        return RESTFUL.postForObject(
                            MenuAPI.getMenuCreateUrl(access_token) ,
                            postdata , 
                            JsonNode.class
                           );
    }
    
    
}
