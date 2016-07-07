/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UrlEncode.   url地址解析处理工具类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class UrlParser {
    
    /**
     * <pre>
     *  url 进行 encoder 转码
     * </pre>
     * 默认字符集：UTF-8
     */
    public static String encode(String url){
        String tmp = url;
        try {
            tmp = URLEncoder.encode(tmp , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    
    /**
     * <pre>
     *  url 进行 encoder 解码
     * </pre>
     * 默认字符集：UTF-8
     */
    public static String decode(String url){
        String tmp = url;
        try {
            tmp = URLDecoder.decode(tmp , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    
    
}
