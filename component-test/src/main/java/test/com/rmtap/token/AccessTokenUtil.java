/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.token;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.RestfulTemplate;

/**
 * AccessTokenUtil.
 * 
 * @author muarine
 * @since 2.1.6
 */
public class AccessTokenUtil {
	
	private final static Logger log = LoggerFactory.getLogger(AccessTokenUtil.class);
	
	
	/**
	 * <ul>
	 * 	<li>1. 先从缓存里取</li>
	 * 	<li>2. 若缓存里没有则发起post请求access_token</li>
	 * 	<li>3. 若网络请求失败，则连续发送5次，直到取到access_token为止</li>
	 * </ul>
	 * @param appid
	 * @return
	 */
	public static String getAccessToken(String appid){
		// 1. 先从缓存里取
//		ApplicationContext ctx = ApplicationContextHolder.getInstance();
//		MemCacheService service = ctx.getBean(MemCacheService.class);
		String access_token = null;
//		try {
//			access_token = service.getAccessToken(appid);
//		} catch (Exception e) {
//			// Timeout waiting for value: waited 2,500 ms. Node status: Connection Status { c3aa07608e7044ea.m.cnbjalicm12pub001.ocs.aliyuncs.com/10.157.164.1:11211 active: true, authed: true, last read: 62,646,611 ms ago }
//			// Caused by: net.spy.memcached.internal.CheckedOperationTimeoutException: Timed out waiting for operation - failing node: c3aa07608e7044ea.m.cnbjalicm12pub001.ocs.aliyuncs.com/10.157.164.1:11211
//			log.error(e.getMessage(),e);
//		}
		// 2. 若缓存里没有值则请求weix.rtmap.com
		if(StringUtils.isBlank(access_token)){
			try {
				access_token = postAccessToken(appid);
			} catch (Exception e) {
				// org.springframework.web.client.ResourceAccessException: 
				//	I/O error on POST request for "http://weix.rtmap.com/mp/wxb5e69065eb3d67ce/token":Connection reset; 
				//	nested exception is java.net.SocketException: Connection reset
				if(e instanceof ResourceAccessException){
					log.info(e.getMessage(),e);
					// 连续发送5次请求。直到获取到access_token
					for (int i = 0; i < 5; i++) {
						try {
							access_token = postAccessToken(appid);
							if(StringUtils.isNotBlank(access_token)){
								break;
							}
						} catch (ResourceAccessException e2) {
							log.info(e2.getMessage(),e);
						} catch (Exception e2) {
							log.error(e2.getMessage(),e);
						}
					}
				}else{
					log.error(e.getMessage(),e);
				}
			}
		}
		return access_token;
	}
	
	/**
	 * post请求获取access_token
	 * @return
	 */
	public static String postAccessToken(String appid){
		String access_token_url = "http://dev.rtmap.com/component-web/mp/%s/token";
		JsonNode node = RestfulTemplate.INSTANCE.postForObject(String.format(access_token_url, appid), null, JsonNode.class);
		return node.get("data").get("auth_access_token").asText();
	}
	
}
