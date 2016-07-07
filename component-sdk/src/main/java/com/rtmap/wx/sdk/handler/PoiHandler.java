/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import java.io.IOException;
import java.net.URL;

import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.file.FileUtils;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.wx.sdk.api.PoiAPI;
import com.rtmap.wx.sdk.model.PostJsonBuilder;
import com.rtmap.wx.sdk.model.card.MBusiness;
import com.rtmap.wx.sdk.utils.UploadMedia;

/**
 * PoiHandler.      门店接口处理类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class PoiHandler  extends AbstractHandler{
    
    private static class SingleTonHolder{
        private final static PoiHandler INSTANCE = new PoiHandler(); 
    }
    
    private PoiHandler(){}
    
    public static PoiHandler instance = SingleTonHolder.INSTANCE;
    
    /**
     * 图片大小限制1M 
     * @param access_token
     * @param file
     * @return
     * @throws IOException 
     */
    public JsonNode uplaodLogo(String access_token , String url) throws IOException{
//        this.valiUploadImage(file);
        URL uri = new URL(url);
        String filename = FileUtils.getFileName(url);
        logDebug("request:{}", "buffer=@" + filename);
        String buffer = UploadMedia.upload(PoiAPI.getImgUpload(access_token), uri.openStream() , filename , "buffer=@" + filename);
        return JsonMapper.fromJsonString(buffer, JsonNode.class);
    }
    
    /**
     * 创建门店
     * @param access_token  调用方access_token
     * @param business      门店实体对象
     * @return
     */
    public JsonNode createPoi(String access_token , MBusiness business){
        Assert.notNull(business, "poi business must not be null. please check it");
        // 构建freemaker模板
        String businessJson = PostJsonBuilder.build(business);
        System.out.println(businessJson);
        return RESTFUL.postForObject(PoiAPI.getPoiCreate(access_token), businessJson, JsonNode.class);
    }
}
