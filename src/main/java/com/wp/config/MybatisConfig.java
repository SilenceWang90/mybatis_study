package com.wp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MybatisConfig
 * @Description Mybaits配置
 * @Date 2019/4/8 16:33
 * @Created by wangpeng116
 */
@Configuration
@MapperScan({"com.wp.mappers"})
public class MybatisConfig {

}
