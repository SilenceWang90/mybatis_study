package com.wp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Classname DynamicDataSourceConfig
 * @Description 动态切换数据源，重写determineCurrentLookupKey用于数据源切换
 * 当前线程使用的数据源要从ThreadLocal中获取，防止各线程之间发生数据源篡改。
 * @Date 2021/12/22 15:38
 * @Created by wangpeng116
 */
@Slf4j
public class DynamicDataSourceConfig extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 从ThreadLocal中获取数据源类型，使用真正的数据源
        log.info("当前线程使用的数据源信息为:{}", DataSourceContextHolder.getDataSourceType());
        return DataSourceContextHolder.getDataSourceType();
    }
}
