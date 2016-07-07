/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rtmap.core.mapper.PoiMapper;

/**
 * 
 * PoiService. 门店审核失败
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
@Service
public class PoiService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PoiService.class);
	
	@Autowired
	private PoiMapper poiMapper;
	
	public Integer update(String sid , String poiId){
	    Assert.isNull(sid, "sid must not be null.");
	    Assert.isNull(poiId, "poiId must not be null.");
	    if(LOGGER.isDebugEnabled())
	        LOGGER.debug("sid:{} , postId:{}" , sid , poiId);
	    return poiMapper.updatePoiId(sid , poiId);
	}
	
}
