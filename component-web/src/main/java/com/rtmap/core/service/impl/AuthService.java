/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtmap.core.cache.AuthManager;
import com.rtmap.core.config.MpConstant;
import com.rtmap.core.domain.Authorizer;
import com.rtmap.core.mapper.AuthorizerMapper;

/**
 * AuthService. 授权相关Service
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月20日
 * @since 1.0.0
 */
@Service
public class AuthService {
    
    @Autowired
    private AuthorizerMapper authorizerMapper;

    public Authorizer getBaseInfo(String authAppid) {
        return authorizerMapper.getBaseInfo(authAppid);
    }

    public void update(Authorizer authorizer) {
        authorizerMapper.update(authorizer);
    }

    public void insert(Authorizer authorizer) {
        authorizerMapper.insert(authorizer);
    }

    /**
     * 取消授权
     * @param authAppid
     */
    public void cancelAuth(String authAppid) {
        authorizerMapper.updateState(authAppid , MpConstant.Authorizer.AUTH_CANCEL);
        // 更新缓存
        AuthManager.setAuthAppidLegal(authAppid, MpConstant.Authorizer.AUTH_CANCEL);
    }
    
    
}
