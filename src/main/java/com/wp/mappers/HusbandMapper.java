package com.wp.mappers;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Classname HusbandMapper
 * @Description TODO
 * @Date 2021/12/22 18:23
 * @Created by wangpeng116
 */
public interface HusbandMapper {
    @Select("select * from husband")
    List<Map<String, Object>> findHusband();
}
