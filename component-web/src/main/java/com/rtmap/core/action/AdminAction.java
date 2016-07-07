/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminController. 后台
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月31日
 * @since 2.0
 */
@RestController
@RequestMapping("/admin")
public class AdminAction {
	
	/**
	 * 
	 * FIXME Comment this
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		
		return new ModelAndView("admin/index");
		
	}
	
	/**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping("/loginview")
	public ModelAndView loginview(){
		
		return new ModelAndView("admin/login");
		
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(){
		
		return new ModelAndView("admin/login");
		
	}
	
}
