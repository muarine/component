package com.rtmap.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.rtmap.core.domain.Poi;
/**
 * 
 * PoiMapper.  门店
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
public interface PoiMapper {
    
    Integer insert(Poi poi);

    Integer updatePoiId(@Param("sid") String sid,@Param("poiId") String poiId);
}