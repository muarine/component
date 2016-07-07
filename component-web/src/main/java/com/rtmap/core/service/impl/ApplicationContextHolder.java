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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
/**
 * 
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * 
 * ApplicationContextHolder.
 * 
 * @author muarine
 * @since 0.1
 */
@Service
@Lazy(false)
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationContextHolder.class);
	
	private static ApplicationContext applicationContext = null;
	
	public static ApplicationContext getInstance(){
		assertContextInjected();
		return applicationContext;
	}

	@Override
	public void destroy() throws Exception {
		LOGGER.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		applicationContext = null;
	}
	
	/**
	 * 获取Bean
	 * 
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType){
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}
	
	/**
	 * 获取Bean
	 * 
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		assertContextInjected();
		return (T)applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}
	
	/**
	 * 检测Context上下文是否注入
	 */
	private static void assertContextInjected(){
		if(applicationContext == null){
			throw new IllegalStateException("applicaitonContext属性未注入, 请检测applicationontext.xml依赖注入的packge，或者将ApplicationContextHolder配置手动配置bean");
		}
	}
	
	
}
