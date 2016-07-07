package com.rtmap.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rtmap.core.domain.CardCode;

/**
 * 
 * CardMapper. 卡券
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
public interface CardCodeMapper {
    
    Integer insertBatch(List<CardCode> codes);
    
    Integer updateState(@Param("code") String code ,@Param("state") Integer state);
    
}