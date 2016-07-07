/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.cache;

import org.springframework.util.Assert;

import com.rtmap.core.domain.Authorizer;
import com.rtmap.core.mapper.AuthorizerMapper;
import com.rtmap.core.service.impl.Memcache;

/**
 * 
 * AccountManager. 平台账户
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月17日
 * @since 1.0.0
 */
public class AccountManager extends AbstractCache{
    
    private final static Memcache memcache = ctx.getBean(Memcache.class);

    /**
     * 获取授权方的主键ID
     * 
     * @param authAppid
     * @return
     */
    public static Integer getAuthId(String authAppid) {
        Integer value = memcache.getInteger(KeyConfig.Authorizer_key.PRIMARY_ID);
        if(null == value){
            Authorizer auth = getAuthorizerInfo(authAppid);
            Assert.notNull(auth , "authAppid is invalid , not match relation data.");
            value = auth.getId();
            if(LOGGER.isDebugEnabled()) LOGGER.debug("授权方信息已更新");
        }
        if(LOGGER.isDebugEnabled()) LOGGER.debug("authAppid:{} , authId:{}" , authAppid , value);
        return value;
    }

    /**
     * 授权方基本信息
     * 
     * @param authAppid
     */
    private static Authorizer getAuthorizerInfo(String authAppid) {
        AuthorizerMapper mapper = ctx.getBean(AuthorizerMapper.class);
        Authorizer auth = mapper.getBaseInfo(authAppid);
        return auth;
    }
    
}
