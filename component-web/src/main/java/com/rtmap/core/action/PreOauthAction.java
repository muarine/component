/**   
* @Title: WeiXinRedirectController.java 
* @Package com.rtmap.weixin.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author maoyun0903 maoyun@rtmap.com   
* @date 2015-3-24 下午05:47:02 
* @version V1.0   
*/
package com.rtmap.core.action;

import com.alibaba.fastjson.JSONObject;
import com.rtmap.core.cache.TicketManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.api.WebServiceAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Oauth前置跳转
 * PreOauthAction.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月24日
 * @since 1.0.0
 */
@Controller
@RequestMapping("/preoauth")
public class PreOauthAction extends AbstractAction{


	/**
	 * Oauth认证获取用户OpenId
	 * 
	 * @param authAppid	授权方appId
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/{authAppid}/base")
	public ModelAndView snsapiBaseNew(@PathVariable("authAppid") String authAppid,HttpServletRequest request,String r) throws Exception{
		if(r == null || r.trim().length() == 0) return new ModelAndView("/404");
		// Map<String,String[]> 转 Map<String,String> 转 String
		Map<String,String[]> map = request.getParameterMap();
		Map<String,String> tempMap = new HashMap<String,String>();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		StringBuffer buffer = new StringBuffer();
		while(it.hasNext()){
			Entry<String, String[]> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue() == null ? "":entry.getValue()[0];
			if(!key.equals("r")){
				buffer.append("&"+key).append("="+value);
			}
			tempMap.put(key, value);
		}
		String param = buffer.toString();
		LOGGER.info("静默授权,前置跳转:{}" , param);
		String state = JSONObject.toJSONString(tempMap);
		String key = StringUtils.generateUUID();
		TicketManager.setOauthState(authAppid, key , state);
		LOGGER.info("静默授权,key:{},value:{}",key,state);
		return new ModelAndView("redirect:" + 
				WebServiceAPI.getOAuthCodeUrl(authAppid , 
							String.format(commonMap.get(MpConstant.DOMAIN) + "/oauth/%s/snsapi_base", authAppid), 
							"snsapi_base", 
							key,
							commonMap.get(MpConstant.APPID))
			    );
	}
	
	/**
	 * Oauth认证获取用户基本信息
	 * ? json去"
	 * 
	 * @param authAppid
	 * @param request
	 * @return	http://xxx/redirect/transfer.do?redirectURL=xxxx&phone=123456
	 */
	@RequestMapping("/{authAppid}/userinfo")
	public ModelAndView transferUserInfo(@PathVariable("authAppid") String authAppid , HttpServletRequest request , String r){
		Map<String,String[]> map = request.getParameterMap();
		if(r == null || r.trim().length() == 0) 
		    return new ModelAndView("/404");
		Map<String,String> tempMap = new HashMap<String,String>();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String[]> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue() == null ? "":entry.getValue()[0];
			tempMap.put(key, value);
		}
		String state  = JSONObject.toJSONString(tempMap);
		String key    = StringUtils.generateUUID();
		TicketManager.setOauthState(authAppid, key, state);
		LOGGER.info("非静默授权,key:{},value:{}",key,state);
		return new ModelAndView("redirect:" + 
				WebServiceAPI.getOAuthCodeUrl(
						authAppid , 
						String.format(commonMap.get(MpConstant.DOMAIN) + "/oauth/%s/snsapi_userinfo", authAppid), 
						"snsapi_userinfo", 
						key,
						commonMap.get(MpConstant.APPID))
				);
	}
	
	
	
}
