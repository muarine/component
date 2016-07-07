/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.util.Assert;

/**
 * FileUtils.   文件工具类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class FileUtils {
    
    /**
     * 根据路径删除图片
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(File file)throws IOException{
        return file != null && file.delete();
    }
    
    /***
     * 获取文件扩展名
     * @param filename
     * @return 返回文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    
    /**
     *  读取指定文件的输出
     * @param path
     * @return
     */
    public static String getFileOutputString(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 检测文件大小,单位：MB
     * 
     * @param file      要比较的文件
     * @param i         MB
     * @return
     */
    public static boolean isGtMB(File file , Integer i){
        Assert.notNull(file, "file must not be null");
        Long length = file.length();
        System.out.println(length);
        Long mb = length/(1024*1024);
        System.out.println(mb);
        return mb >= i ? true : false;
    }
    
    public static void main(String[] args) {
        System.out.println(getFileName("http://ss.baidu.com/121231231.jpg"));
        
        System.out.println(isGtMB(new File("/Users/Muarine/Downloads/微信摇一摇周边接口文档_V2.3_关联其他公众账号门店接口灰度0119.pdf"), 1));
        
        
    }

    /**
     * 从网络地址中截取出文件名,形如:xxx.jpg
     * 
     * @param url
     * @return
     */
    public static String getFileName(String url) {
        if ((url != null) && (url.length() > 0)) {
            int dot = url.lastIndexOf('/');
            if ((dot >-1) && (dot < (url.length() - 1))) {
                return url.substring(dot + 1);
            }
        }
        return url;
    }
    
}
