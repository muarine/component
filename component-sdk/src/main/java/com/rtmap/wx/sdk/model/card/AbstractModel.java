/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model.card;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtmap.utils.string.StreamUtil;

/**
 * AbstractJsonFile.    解析json到实体的抽象类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public abstract class AbstractModel {
    
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    protected final static Logger slog = LoggerFactory.getLogger(AbstractModel.class);
    /**
     * 创建门店json文件
     */
    protected final static String POI_JSON_PATH = "poi.json";
    /**
     * 创建券json文件
     */
    protected final static String CARD_JSON_PATH = "card.json";
    
    /**
     * 加载package下的json文件 
     * @param filename
     * @return
     */
    protected static String load(String filename){
        
        InputStream in = AbstractModel.class.getResourceAsStream(filename);
        try {
            return StreamUtil.inputStreamToString(in);
        } catch (IOException e) {
            slog.error(e.getMessage(),e);
        }
        return null;
    }
    
}
