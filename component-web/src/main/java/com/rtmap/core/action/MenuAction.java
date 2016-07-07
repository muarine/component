/**   
* @Title: WeiXinPOSTController.java 
* @Package com.rtmap.weixin.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author maoyun0903 maoyun@rtmap.com   
* @date 2015-3-26 上午11:02:01 
* @version V1.0   
*/
package com.rtmap.core.action;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.config.ResponseFactory;
import com.rtmap.core.exp.Assert;
import com.rtmap.core.vo.Code;
import com.rtmap.core.vo.Result;
import com.rtmap.wx.sdk.handler.MenuHandler;

/**
 * 
 * MenuAction.  菜单接口
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
@RestController
@RequestMapping("/menu")
public class MenuAction extends AbstractAction{
	
	/**
	 * POST请求,创建菜单
	 **/
	@RequestMapping(value = "/{authAppid}/menu/create", method = RequestMethod.GET)
	public Result doPost(@PathVariable("authAppid") String authAppid , String menu_json) {
		try {
		    Assert.hasText(menu_json , "menu_json is required");
		    String appid      = commonMap.get(MpConstant.APPID);
		    String appsecret  = commonMap.get(MpConstant.APPSECRET);
			JsonNode result = MenuHandler.instance.createMenu(AuthManager.getAuthAccessToken(appid, appsecret, authAppid), menu_json);
			return ResponseFactory.build(result);
		} catch (IllegalArgumentException e) {
		    return ResponseFactory.build(Code.INVALID_PARAM , e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseFactory.build(Code.ERROR);
		}
	}
	
}
