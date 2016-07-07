/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

/**
 * PoiAPI.  门店API
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class PoiAPI {
    
    private static final String IMG_UPLOAD      = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";
    /**
     * 创建门店
     */
    private static final String  POI_CREATE     = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=%s";
    /**
     * 查询门店信息
     */
    private static final String POI_SELECT_SINGLE   = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=%s";
    /**
     * 查询门店列表
     */
    private static final String POI_SELECT_LIST = " https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=%s";
    /**
     * 修改门店服务信息
     */
    private static final String POI_UPDATE      = " https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=%s";
    /**
     * 删除门店
     */
    private static final String POI_DELETE      = " https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=%s";
    
    
    public static String getImgUpload(String access_token) {
        return String.format(IMG_UPLOAD , access_token);
    }
    public static String getPoiCreate(String access_token) {
        return String.format(POI_CREATE , access_token);
    }

    public static String getPoiSelectSingle(String access_token) {
        return String.format(POI_SELECT_SINGLE, access_token);
    }

    public static String getPoiSelectList(String access_token) {
        return String.format(POI_SELECT_LIST, access_token);
    }

    public static String getPoiUpdate(String access_token) {
        return String.format(POI_UPDATE , access_token);
    }

    public static String getPoiDelete(String access_token) {
        return String.format(POI_DELETE , access_token);
    }
}
