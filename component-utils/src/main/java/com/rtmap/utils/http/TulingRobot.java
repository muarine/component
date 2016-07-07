/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * TulingRobot. 图灵机器人
 * 
 * @author muarine
 * @since 0.1
 */
public class TulingRobot {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TulingRobot.class);
	
	private static final String TULING_URL = "http://www.tuling123.com/openapi/api?key=744bf2019fc0859b83498c2a33f7f756&info={info}";
	
	private static TulingRobot robot = null;
	
	private TulingRobot(){
		super();
	}
	
	public static TulingRobot getInstance(){
		if(robot == null){
			robot = new TulingRobot();
		}
		return robot;
	}
	
	/**
	 * 
	 * 关键字回复
	 * 
	 * // 302000 新闻
		// 308000 菜谱
		// 200000 图片
	 * 
	 * @return
	 */
	public String getOutText(String info){
		try {
			String result = RestfulTemplate.INSTANCE.getForObject(TULING_URL, String.class, info);
			JsonNode node = JsonMapper.fromJsonString(result, JsonNode.class);
			if(node.get("code").asInt() != 100000) {
				LOGGER.info("请求内容暂未设定解析，内容:{}",node.toString());
				return null;
			}
			return node.get("text").asText();
		} catch (Exception e) {
			// 防止网络请求异常
			LOGGER.error(e.getMessage(),e);
			return null;
		}
		
	}
	
}
