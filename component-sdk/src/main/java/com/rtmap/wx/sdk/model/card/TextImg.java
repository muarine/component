/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model.card;

/**
 * TextImg.     图文详情
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date 2016年4月8日
 * @since 1.0.0
 */
public class TextImg {

    private String image_url;
    private String text;
    
    /**
     * Create a new TextImg.
     * 
     */
    public TextImg(String image_url , String text) {
        this.image_url = image_url;
        this.text = text;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
