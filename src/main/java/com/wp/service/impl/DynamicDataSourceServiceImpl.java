package com.wp.service.impl;

import com.wp.config.AssignDataSource;
import com.wp.entities.YuML;
import com.wp.mappers.HusbandMapper;
import com.wp.mappers.YuMLMapper;
import com.wp.service.DynamicDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Classname DynamicDataSourceServiceImpl
 * @Description 测试多数据源service
 * @Date 2021/12/23 11:22
 * @Created by wangpeng116
 */
@Slf4j
@Service
public class DynamicDataSourceServiceImpl implements DynamicDataSourceService {
    @Resource
    private HusbandMapper husbandMapper;
    @Resource
    private YuMLMapper yuMLMapper;

    @AssignDataSource("second")
    @Override
    public List<Map<String, Object>> findHusband() {
        return husbandMapper.findHusband();
    }

    @AssignDataSource("primary")
    @Override
    public List<YuML> findAll() {
        return yuMLMapper.findAll();
    }
}
