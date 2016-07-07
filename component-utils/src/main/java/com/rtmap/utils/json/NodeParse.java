/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * NodeParse.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月30日
 * @since 1.0.0
 */
public class NodeParse {
    
    /**
     * 解析指定的字段 
     * @param node
     * @param fieldName 字段名
     * @return ""
     */
    public static String asText(JsonNode node , String fieldName){
        
        if(node == null || node.get(fieldName) == null) return "";
        
        return node.get(fieldName).asText();
    }
    
    /**
     * 转换成Integer
     * @param node
     * @param fieldName
     * @return
     */
    public static Integer asInt(JsonNode node, String fieldName , int defaultValue){
        
        if(node == null || node.get(fieldName) == null) return defaultValue;
        
        return node.get(fieldName).asInt();
        
    }
}
