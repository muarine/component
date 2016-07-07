/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import com.rtmap.utils.file.FileUtils;
import com.rtmap.utils.http.RestfulTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * AbstractHandler.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public abstract class AbstractHandler {
    
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    protected final RestTemplate RESTFUL = RestfulTemplate.INSTANCE;
    
    /**
     * 打印debug 
     * @param template
     * @param args
     */
    protected void logDebug(String template , Object ... args) {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(template , args);
        }
    }
    
    /**
     * 打印info 
     * @param template
     * @param args
     */
    protected void logInfo(String template , Object ... args) {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info(template , args);
        }
    }
    /**
     * 打印Error
     * @param template
     * @param args
     */
    protected void logError(String template , Object ... args) {
        LOGGER.error(template , args);
    }
    
    /**
     * <pre>
     * 校验文件
     * 1. 是否为null
     * 2. 后缀名是否为.jpg或者.png
     * 3. 图片大小不能大于1MB
     * </pre>
     * @param file
     */
    protected void valiUploadImage(File file){
        Assert.notNull(file , "card image must not be null.");
        String extensionName = FileUtils.getExtensionName(file.getName());
        Assert.isTrue(extensionName.equals(".png") || extensionName.equals("jpg") , "card imagename extension must be .jpg or .png");
        FileUtils.isGtMB(file, 1);
    }
    
}
