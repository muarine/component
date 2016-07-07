/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * 
 * IndexAction. 授权相关
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月28日
 * @since 1.0.0
 */
@RestController
@RequestMapping("/")
public class IndexAction extends AbstractAction{
	
	/**
	 * 首页 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		// 检测登录状态,未登录则跳转到 公众号登录授权页的跳转页
		Object auther = WebUtils.getSessionAttribute(request, "authorizer");
		if(null == auther){
			// 未登录,跳转到授权页面
			return new ModelAndView("login"); 
		}
		
		return new ModelAndView("redirect:/admin/index");
	}
	
	/**
	 * 介绍
	 * 
	 * @return
	 */
	@RequestMapping("/intro")
	public ModelAndView intro(){
		ModelAndView mv = new ModelAndView("intro");
		return mv;
	}
	
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().removeAttribute("authorizer");
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
}
