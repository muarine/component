package com.rtmap.promo3.mapper;

import org.apache.ibatis.annotations.Param;

import com.rtmap.promo3.domain.ShakeUser;

/**
 * 
 * ShakeUserMapper.	摇一摇
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public interface ShakeUserMapper {
    
	ShakeUser selectByOpenidAndAppid(@Param("openid") String openid, @Param("appid") String appid);
	
	int insert(ShakeUser shakeUser);

	int update(ShakeUser shakeUser);

	void updateUnsubcribeAndState(ShakeUser user);

    int delete(@Param("appid") String appid, @Param("openid") String openid);
	
}