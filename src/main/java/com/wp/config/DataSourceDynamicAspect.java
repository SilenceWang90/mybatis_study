package com.wp.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Classname DataSourceDynamicAspect
 * @Description 数据源动态切换切面类
 * @Date 2021/12/23 10:56
 * @Created by wangpeng116
 */
@Configuration
@Aspect
@Slf4j
@Order(-1)
public class DataSourceDynamicAspect {
    /**
     * 加载当前线程使用的数据源
     *
     * @param proceedingJoinPoint
     */
    @Before("@annotation(com.wp.config.AssignDataSource)")
    public void changeDataSource(JoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        AssignDataSource assignDataSource = methodSignature.getMethod().getAnnotation(AssignDataSource.class);
        // 获取当前的指定的数据源
        String dataSourceName = assignDataSource.value();
        DataSourceContextHolder.setDataSourceType(dataSourceName);
        log.debug("切换数据源(Assign) : {}", "dataSourceName");
    }

    /**
     * 清除当前线程使用的数据源
     *
     * @param proceedingJoinPoint
     */
    @After("@annotation(com.wp.config.AssignDataSource)")
    public void restoreDataSource(JoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        AssignDataSource assignDataSource = methodSignature.getMethod().getAnnotation(AssignDataSource.class);
        DataSourceContextHolder.clear();
        log.debug("回收数据源(Assign) : {} > {}", assignDataSource.value(), proceedingJoinPoint.getSignature());
    }
}
