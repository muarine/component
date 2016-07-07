/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.wx.sdk.api.ShakeAroundAPI;

/**
 * ShakeAroundHandler.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class ShakeAroundHandler extends AbstractHandler {
    
    private static class SingleTonHolder{
        private final static ShakeAroundHandler INSTANCE = new ShakeAroundHandler(); 
    }
    
    private ShakeAroundHandler(){}
    
    public static ShakeAroundHandler instance = SingleTonHolder.INSTANCE;
    
    /**
     * 获取摇一摇用户的信息
     * @return
     */
    public JsonNode getShakeUserInfo(String access_token , Object postdata){
        
        JsonNode node  = RESTFUL.postForObject(
                                ShakeAroundAPI.getUserShakeInfo(access_token), 
                                postdata, 
                                JsonNode.class
                             ) ;
        
        return node;
    }
    
    
}
