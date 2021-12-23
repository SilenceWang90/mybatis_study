package com.wp.service;

import com.wp.entities.YuML;

import java.util.List;
import java.util.Map;

/**
 * @Classname Service
 * @Description 测试多数据源service
 * @Date 2021/12/23 11:20
 * @Created by wangpeng116
 */
public interface DynamicDataSourceService {
    List<Map<String, Object>> findHusband();

    List<YuML> findAll();
}
