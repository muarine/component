/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.rtmap.core.service.impl.ApplicationContextHolder;
import com.rtmap.utils.http.RestfulTemplate;

/**
 * AbstractCache. 缓存逻辑基类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public abstract class AbstractCache {
    
    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCache.class);
       
    protected final static ApplicationContext ctx = ApplicationContextHolder.getInstance();
    
    protected final static RestTemplate RESTFUL = RestfulTemplate.INSTANCE;
    
    
}
