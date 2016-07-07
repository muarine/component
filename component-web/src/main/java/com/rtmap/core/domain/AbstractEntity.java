/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * AbstractEntity.  抽象基类实体
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月8日
 * @since 1.0.0
 */
public abstract class AbstractEntity implements Serializable{
    
    /** The serialVersionUID */
    private static final long serialVersionUID = 1664092820661628438L;

    private Integer id;
    
    private Date createTime;
    private Date modifyTime;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    
}
