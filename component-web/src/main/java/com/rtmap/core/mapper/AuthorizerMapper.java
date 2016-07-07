package com.rtmap.core.mapper;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.rtmap.core.domain.Authorizer;
public interface AuthorizerMapper {
    
	Authorizer selectById(Long id);
	Authorizer getBaseInfo(String authAppid);
	String getRefreshToken(String authAppid);
	Integer getState(String authAppid);
	Authorizer selectByUserName(String username);
	Authorizer selectByUserNameAndSecret(Map<String, Object> map);
	
	Long insert(Authorizer authorizer);
	
	int update(Authorizer authorizer);
	int updateRefreshToken(@Param("authAppid") String authAppid , @Param("refreshToken") String refreshToken);
    void updateState(@Param("authAppid") String authAppid, @Param("state") Integer state);
	
}