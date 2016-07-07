/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

/**
 * UploadMedia.     上传素材
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class UploadMedia {
    private final static String DEFAULT_CHARSET = "UTF-8";
    private final static String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
    
    /**
     * 上传临时素材，
     * @param url 图片上传地址
     * @param file 需要上传的文件
     * @return ApiResult
     * @throws IOException
     */
    public static String upload(String url, InputStream is , String filename , String params) throws IOException {
        URL urlGet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", DEFAULT_USER_AGENT);  
        conn.setRequestProperty("Charsert", "UTF-8");
        // 定义数据分隔线
        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; 
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        
        OutputStream out = new DataOutputStream(conn.getOutputStream());
        // 定义最后数据分隔线
        StringBuilder mediaData = new StringBuilder();
        mediaData.append("--").append(BOUNDARY).append("\r\n");
        mediaData.append("Content-Disposition: form-data;name=\"media\";filename=\"" + filename + "\"\r\n");
        mediaData.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] mediaDatas = mediaData.toString().getBytes();
        out.write(mediaDatas);
        DataInputStream fs = new DataInputStream(is);
        int bytes = 0;  
        byte[] bufferOut = new byte[1024];
        while ((bytes = fs.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        fs.close();
        // 多个文件时，二个文件之间加入这个
        out.write("\r\n".getBytes());
        if (StringUtils.isNotBlank(params)) {
            StringBuilder paramData = new StringBuilder();
            paramData.append("--").append(BOUNDARY).append("\r\n");
            paramData.append("Content-Disposition: form-data;name=\"description\";");
            byte[] paramDatas = paramData.toString().getBytes();
            out.write(paramDatas);
            out.write(params.getBytes(DEFAULT_CHARSET));
        }
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        out.write(end_data);
        out.flush();
        out.close();
        // 定义BufferedReader输入流来读取URL的响应  
        InputStream in = conn.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        StringBuffer bufferRes = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null){
            bufferRes.append(valueString);
        }
        in.close();
        // 关闭连接
        if (conn != null) {
            conn.disconnect();
        }
        return bufferRes.toString();
    }
}
