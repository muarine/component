/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.IntegerTranscoder;
import net.spy.memcached.transcoders.LongTranscoder;

/**
 * Memcache 缓存Bean
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
@Service
public class Memcache {
    
    @Autowired
	private MemcachedClient memcachedClient;

	/**
     * set String kV-store
     * @param key
     * @param value
     * @param i
     */
    public void setKV(String key, String value, int exp) {
        memcachedClient.set(key, exp, value);
    }
    /**
     * set Integer KV-store
     * @param key
     * @param value
     * @param i
     */
    public void setKV(String key, Integer value, int exp) {
        memcachedClient.set(key, exp, value, new IntegerTranscoder());
    }
    
    /**
     * set Longs KV-store
     * @param key
     * @param value
     * @param i
     */
    public void setKV(String key, Long value, int exp) {
        memcachedClient.set(key, exp, value, new LongTranscoder());
    }
    
    /**
     * 获取缓存value
     * @param key
     * @return
     */
    public Object getObjValue(String key){
        return memcachedClient.get(key);
    }
    
    /**
     * 获取缓存value
     * @param key
     * @return
     */
    public String getString(String key){
        Object obj = this.getObjValue(key);
        return obj == null ? "" : obj.toString();
    }
    
    /**
     * 获取缓存value
     * @param key
     * @return
     */
    public Integer getInteger(String key){
        Object obj = this.getObjValue(key);
        try {
            return obj == null ? null : Integer.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * 获取缓存value
     * @param key
     * @return
     */
    public Long getLong(String key){
        Object obj = this.getObjValue(key);
        try {
            return obj == null ? null : Long.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 删除key
     */
    public void delKey(String key) {
        memcachedClient.delete(key);
    }
	
}
