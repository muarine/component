/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.string;

import java.util.Random;

/**
 * IdGenerator. ID生成器
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月9日
 * @since 1.0.0
 */
public class IdGenerator {
    
    private static Random random = new Random();
    
    /**
     * ID生成公共方法
     * @param prefix
     * @return
     */
    public static synchronized String generate(String prefix) {
        prefix = prefix == null ? "" : prefix;
        StringBuilder sb = new StringBuilder(prefix);
        long time = System.currentTimeMillis();
        sb.append(time);
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        return sb.toString();
    }
    
    /**
     * 生成ID，不带有前缀，格式如：时间戳 + 后三位随机数
     * @return
     */
    public static String generateSid(){
        return generate(null);
    }
}
