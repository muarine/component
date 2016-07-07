/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model.card;

/**
 * Abstract.    封面摘要简介。
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date 2016年4月8日
 * @since 1.0.0
 */
public class Abstract {
    private String abstract_name;
    private String[] icon_url_list;

    public String getAbstract_name() {
        return abstract_name;
    }

    public void setAbstract_name(String abstract_name) {
        this.abstract_name = abstract_name;
    }

    public String[] getIcon_url_list() {
        return icon_url_list;
    }

    public void setIcon_url_list(String[] icon_url_list) {
        this.icon_url_list = icon_url_list;
    }

}
