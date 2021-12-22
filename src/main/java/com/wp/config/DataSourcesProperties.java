package com.wp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Classname DataSourcesProperties
 * @Description 数据源属性配置类
 * @Date 2021/12/22 16:24
 * @Created by wangpeng116
 */
@Component
@ConfigurationProperties(prefix = "spring")
@Data
public class DataSourcesProperties {
    // 数据源属性
    private Map<String, Map<String, String>> datasource;
}
