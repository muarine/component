/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtmap.core.vo.Code;
import com.rtmap.core.vo.Msg;
import com.rtmap.core.vo.Result;
import com.rtmap.utils.json.JsonMapper;

/**
 * ResponseFactory. 响应数据工厂类
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class ResponseFactory {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseFactory.class);
    
    /**
     * 公共私有函数
     * @param code
     * @return
     */
    private static Result _commonBuild(Integer code , String errmsg) {
        Result result = new Result();
        result.setStatus(code);
        if(StringUtils.isBlank(errmsg)){
            result.setMessage(Msg.getErrMsg(code));
        }else{
            result.setMessage(errmsg);
        }
        if(LOGGER.isDebugEnabled()) LOGGER.debug("response:{}" , JsonMapper.toJsonString(result));
        return result;
    }
    
    /**
     * 构建返回json
     * @param code
     * @return
     */
    public static Result build(Integer code){
        Result json = _commonBuild(code , Msg.getErrMsg(code));
        return json;
    }
    
    /**
     * 请求成功，构建返回数据
     * 
     * @return
     */
    public static Result build(){
        Result json = _commonBuild(Code.SUCCESS , null);
        return json;
    }
    /**
     * 请求成功，构建返回数据
     * 
     * @param data  返回数据
     * @return
     */
    public static Result build(Object data){
        Result json = _commonBuild(Code.SUCCESS , null);
        json.setData(data);
        return json;
    }
    /**
     * 自定义返回码code，构建返回数据
     * @param code
     * @return
     */
    public static Result build(Integer code , Object data){
        Result result = _commonBuild(code , null);
        result.setData(data);
        return result;
    }
    
    /**
     * 自定义返回code
     * @param code      返回码
     * @param errmsg    自定义返回结果
     * @return
     */
    public static Result buildMessage(Integer code , String errmsg){
        return _commonBuild(code , errmsg);
    }
    
}
