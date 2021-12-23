package com.wp.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname DataSourceType
 * @Description 数据源类型切换类
 * @Date 2021/12/22 16:02
 * @Created by wangpeng116
 */
@Slf4j
@Getter
public enum DataSourceType {
    primary("primary", "1"),
    second("second", "2"),
    ;

    private String name;
    private String value;

    DataSourceType(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
