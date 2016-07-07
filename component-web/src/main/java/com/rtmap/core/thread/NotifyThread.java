/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.rtmap.utils.http.RestfulTemplate;

/**
 * 
 * NotifyThread. 消息回调线程
 * 
 * @author muarine
 * @since 2.2.4
 */
public class NotifyThread implements Runnable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyThread.class);
	
	private String notify_url ;
	private MultiValueMap<String,Object> multiValueMap ;
	
	/**
	 * 有参构造函数
	 * @param notify_url		通知地址
	 * @param multiValueMap		参数集合
	 */
	public NotifyThread(String notify_url , MultiValueMap<String,Object> multiValueMap) {
		super();
		this.notify_url = notify_url;
		this.multiValueMap = multiValueMap;
	}

	@Override
	public void run() {
		LOGGER.info("消息推送线程开始执行");
		LOGGER.info("notify_url:{} , request_params:{}" , notify_url , JSON.toJSONString(multiValueMap));
		for (int i = 0; i < 3; i++) {
			try {
				String result = RestfulTemplate.INSTANCE
												.postForObject(
																notify_url ,
																multiValueMap , 
																String.class
																);
				LOGGER.info("异步通知返回结果:{}" , result);
				if(JSON.parseObject(result).getInteger("code") == 200){
					break;
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(),e);
				LOGGER.info("正在建立重试机制,重新尝试请求通知接口...");
			}
			if(i >= 2){
				LOGGER.error("异步通知失败，重试次数:{}" , i);
			}
		}
		
		
		LOGGER.info("消息推送线程执行完毕！");
		
	}
	
}
