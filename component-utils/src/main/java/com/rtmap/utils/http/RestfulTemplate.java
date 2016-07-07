/*
 *
 *  * RT MAP, Home of Professional MAP
 *  * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 *  * as indicated by the @author tags. All rights reserved.
 *  * See the copyright.txt in the distribution for a
 *  * full listing of individual contributors.
 *
 */
package com.rtmap.utils.http;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * RestfulTemplate. RestTemplate、AsyncRestTemplate相关工具类
 *
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月6日
 * @since 2.0
 */
public class RestfulTemplate {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestfulTemplate.class);

	/**
	 * SingleTonHolder. 静态内部类
	 *
	 * @author Muarine <maoyun@rtmap.com>
	 * @date 2016年5月13日
	 * @since 1.0.0
	 */
	private static class SingleTonHolder {

		private final static RestTemplate INSTANCE = initRestTemplate();
		private final static AsyncRestTemplate ASYNC_INSTANCE = initAsyncRestTemplate();

		/**
		 * 初始化RestTemplate，请求报文字符编码为UTF-8
		 *
		 * @return
		 */
		private static RestTemplate initRestTemplate() {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setReadTimeout(10000);
			requestFactory.setConnectTimeout(5000);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			return restTemplate;
		}

		/**
		 * 初始化AsyncRestTemplate
		 *
		 * @return
		 */
		private static AsyncRestTemplate initAsyncRestTemplate() {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setReadTimeout(20000);
			requestFactory.setConnectTimeout(10000);
			requestFactory.setTaskExecutor(new SimpleAsyncTaskExecutor());
			return new AsyncRestTemplate(requestFactory, INSTANCE);
		}
	}

	private RestfulTemplate() {
	}

	public static RestTemplate INSTANCE = SingleTonHolder.INSTANCE;
	public static AsyncRestTemplate ASYNC_INSTANCE = SingleTonHolder.ASYNC_INSTANCE;

	/**
	 * RestTemplate 同步 POST请求,已解决中文乱码
	 *
	 * @param url           request url
	 * @param multiValueMap 传递参数
	 * @return
	 */
	public static String syncPostCN(String url, MultiValueMap<String, String> multiValueMap) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("sync-post-url:{} , param:{}", url, multiValueMap == null ? "null" : JSON.toJSONString(multiValueMap));
		}
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
		headers.setContentType(type);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(multiValueMap, headers);
		return INSTANCE.postForObject(url, entity, String.class);
	}

	/**
	 * AsyncRestTemplate 异步 POST请求,已解决中文乱码
	 *
	 * @param url           request url
	 * @param multiValueMap 传递参数
	 * @return
	 */
	public static void asyncPostCN(String url, MultiValueMap<String, String> multiValueMap) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("async-post-url:{} , param:{}", url, multiValueMap == null ? "null" : JSON.toJSONString(multiValueMap));
		}

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
		headers.setContentType(type);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(multiValueMap, headers);
		ListenableFuture<ResponseEntity<String>> future = ASYNC_INSTANCE.postForEntity(url, entity, String.class);
		future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onSuccess(ResponseEntity<String> result) {
				LOGGER.info(result.getBody());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		});
	}

}
