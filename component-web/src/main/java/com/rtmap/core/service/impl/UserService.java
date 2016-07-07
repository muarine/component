/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.cache.AuthManager;
import com.rtmap.promo3.domain.ShakeUser;
import com.rtmap.promo3.mapper.ShakeUserMapper;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.wx.sdk.api.UserAPI;

/**
 * UserService. 公众号粉丝
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月14日
 * @since 2.0
 */
@Service
public class UserService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private ShakeUserMapper shakeUserMapper;
//	@Autowired
//	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	/**
	 * 取消关注
	 * 
	 * @param user
	 * @return
	 */
	public void updateUnsubcribeState(ShakeUser user) {
		 shakeUserMapper.updateUnsubcribeAndState(user);
	}
	
	/**
	 * 关注
	 * <br/>
	 * 同步用户数据，数据库里有记录则更新关注时间，关注状态，更新时间，更新基本信息
	 * 否则新增一条记录
	 * @param appId
	 * @param openid
	 */
	public void syncUserInfo(String appid , String appsecret , String authAppid , String openid) {
		try {
			ShakeUser user = this.selectByOpenidAndAppid(openid,appid);
			ShakeUser newRecord = this.getUser(appid, AuthManager.getAuthAccessToken(appid, appsecret, authAppid) , openid);
			if(user == null && newRecord !=null){ // 不存在该用户
				shakeUserMapper.insert(newRecord);
				// TODO 用户首次关注，单开推送线程
//				this.threadPushGameScore(appid, openid);
			}else if(user != null && newRecord != null){	// 已存在用户
				newRecord.setId(user.getId());
				shakeUserMapper.update(newRecord);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}

    /**
     * 消息推送线程
     * @param appid
     * @param openid
     */
    protected void threadPushGameScore(String appid, String openid) {
        // 创建线程推送给营销平台API
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String,Object>();
        multiValueMap.add("open_id", openid);
        multiValueMap.add("appid", appid);
        multiValueMap.add("type", 2);       // type = 2 首次关注得游戏积分
//        threadPoolTaskExecutor.submit(new NotifyThread("http://123.56.165.149:8080/doubleball2/player/score" , multiValueMap));
    }
	
	public ShakeUser getUser(String appid,String access_token,String openid){
		String url = UserAPI.getFansInfoUrl(access_token, openid);
		JsonNode node = RestfulTemplate.INSTANCE.getForObject(url, JsonNode.class);
		ShakeUser user = null;
		// 先检测是否含有errcode , 出错时才会带有errcode字段 ， 否则解析json会报错
		if(node.get("errcode") != null && node.get("errcode").asInt() != 0){
		    LOGGER.error("拉取粉丝信息出错:" , node.toString());
		}else{
		    if(node.get("subscribe").asInt() == 1){
		        LOGGER.debug(node.toString());
		        user = new ShakeUser();
		        user.setAppid(appid);
		        user.setCreate_time(new Date());
		        user.setOpenid(node.get("openid").asText());
		        user.setCity(node.get("city").asText());
		        user.setCountry(node.get("country").asText());
		        user.setHead(node.get("headimgurl").asText());
		        user.setNick_name(node.get("nickname").asText());
		        user.setProvince(node.get("province").asText());
		        user.setRemark(node.get("remark").asText());
		        user.setSex(node.get("sex").asInt());
		        user.setState(1);
		        user.setSubscribe_time(node.get("subscribe_time").asLong());
		        user.setUnionid(node.get("unionid").asText());
		        user.setUpdate_time(System.currentTimeMillis());
		    }else{
		        LOGGER.info("获取用户:{}的资料失败，暂未关注公众号:{}",appid);
		    }
		}
		return user;
	}
	
	public ShakeUser selectByOpenidAndAppid(String openid , String appid) {
		return shakeUserMapper.selectByOpenidAndAppid(openid,appid);
	}
	
	public int insert(ShakeUser user){
		return shakeUserMapper.insert(user);
	}
	
	public int update(ShakeUser user){
		return shakeUserMapper.update(user);
	}
	
	public int delete(String appid , String openid){
	    return shakeUserMapper.delete(appid , openid);
	}
}
