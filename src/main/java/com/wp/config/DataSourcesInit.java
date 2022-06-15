package com.wp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Classname DataSourcesInit
 * @Description 多数据源创建
 * @Date 2021/12/22 16:21
 * @Created by wangpeng116
 */
@Configuration
@Slf4j
public class DataSourcesInit {
    @Resource
    private DataSourcesProperties dataSourcesProperties;

    /**
     * 首要使用数据源，必须要@Primary。
     *
     * @return
     */
    @Bean("custom-wp-primaryDataSource")
    public DataSource getPrimaryDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourcesProperties.getDatasource().get("primary").get("driver-class-name"));
        druidDataSource.setUrl(dataSourcesProperties.getDatasource().get("primary").get("url"));
        druidDataSource.setUsername(dataSourcesProperties.getDatasource().get("primary").get("username"));
        druidDataSource.setPassword(dataSourcesProperties.getDatasource().get("primary").get("password"));
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            log.error("创建数据源异常：", e);
        }
        return druidDataSource;
    }


    /**
     * 其他使用数据源。
     *
     * @return
     */
    @Bean("custom-wp-secondDataSource")
    public DataSource getSecondDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourcesProperties.getDatasource().get("second").get("driver-class-name"));
        druidDataSource.setUrl(dataSourcesProperties.getDatasource().get("second").get("url"));
        druidDataSource.setUsername(dataSourcesProperties.getDatasource().get("second").get("username"));
        druidDataSource.setPassword(dataSourcesProperties.getDatasource().get("second").get("password"));
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            log.error("创建数据源异常：", e);
        }
        return druidDataSource;
    }

    /**
     * 配置动态数据源
     *
     * @return
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        // DynamicDataSourceConfig实现了Spring的AbstractRoutingDataSource用于数据源切换
        DynamicDataSourceConfig dynamicDataSourceConfig = new DynamicDataSourceConfig();
        // 默认数据源
        dynamicDataSourceConfig.setDefaultTargetDataSource(getPrimaryDataSource());
        Map<Object, Object> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("primary", getPrimaryDataSource());
        dataSourceMap.put("second", getSecondDataSource());
        // 将数据源放入到动态数据源，用于切换使用
        dynamicDataSourceConfig.setTargetDataSources(dataSourceMap);
        return dynamicDataSourceConfig;
    }
}
