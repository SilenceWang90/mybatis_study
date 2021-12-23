package com.wp.config;

import java.lang.annotation.*;

/**
 * @Classname AssignDataSource
 * @Description 动态切换数据源注解
 * @Date 2021/12/23 11:02
 * @Created by wangpeng116
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AssignDataSource {
    /**
     * 使用的数据源，参见枚举：DataSourceType，设置DataSourceType.name即可
     *
     * @return
     */
    String value() default "primary";
}
