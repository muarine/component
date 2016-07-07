/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.vo;

import java.lang.reflect.Field;

/**
 * Param.   参数基类
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月8日
 * @since 2.0
 */
public class Param {
    private Long id;
    private String openid;
    private Integer page;
    private Integer limit;
    private String key;
    
    
	
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    @Override
	public String toString() {
		Class<?> clazz = this.getClass();
		StringBuffer buffer = new StringBuffer();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String varName = field.getName();  
				try {
					boolean isAccess = field.isAccessible();
					if(!isAccess) field.setAccessible(true);
					Object o = field.get(this);
					if(o == null) continue;
					buffer.append("\n").append("'"+varName+"'：").append(o).append("\n");
					if(!isAccess) field.setAccessible(false);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		return buffer.toString();
	}
}
