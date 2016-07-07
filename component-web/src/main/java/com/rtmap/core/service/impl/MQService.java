/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rtmap.core.config.MQConstant;
import com.rtmap.core.thread.ProductThread;
import com.rtmap.utils.json.JsonMapper;

/**
 * MQService.   MessageQueue 消息队列
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月19日
 * @since 1.0.0
 */
@Service
public class MQService {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(MQService.class);
    
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    /**
     * ONS 消息队列推送
     * 
     * @param cardid
     * @param openid
     * @param code
     * @param tag
     */
    public void createPrizeMQ(String cardid , String openid , String code , String tag){
        LOGGER.info("开始奖券消息推送");
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("cardid", cardid);
        map.put("openid", openid);
        map.put("code", code);
        
        MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<String,Object>();
        multiValueMap.add("topic", "PRIZE");
        multiValueMap.add("tags", "consumeCardCode");
        multiValueMap.add("data", JsonMapper.toJsonString(map));
        threadPoolTaskExecutor.submit(new ProductThread(MQConstant.MQ_URL , multiValueMap));
    }
    
    /**
     * 推送核销消息的线程
     */
    public void consumePrizePush(String code){
        LOGGER.info("开始券码核销消息推送");
        threadPoolTaskExecutor.submit(new ProductThread(String.format(MQConstant.CODE_CONSUME_URL , code) , null));
    }
    
    
}
