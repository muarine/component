/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.domain;

/**
 * CardCode.    卡券code实例
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月13日
 * @since 1.0.0
 */
public class CardCode extends AbstractEntity {

    /** The serialVersionUID */
    private static final long serialVersionUID = 2778530792285552279L;
    
    private Integer fk_card_id;
    private String code;
    private Integer state;
    
    public Integer getFk_card_id() {
        return fk_card_id;
    }
    public void setFk_card_id(Integer fk_card_id) {
        this.fk_card_id = fk_card_id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    
}
