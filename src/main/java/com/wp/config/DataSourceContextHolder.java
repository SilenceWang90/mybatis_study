package com.wp.config;

/**
 * @Classname DatabaseContextHolder
 * @Description 线程安全的DatabaseType容器
 * @Date 2021/12/22 17:54
 * @Created by wangpeng116
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * 数据源类型枚举：DataSourceType
     *
     * @param type
     */
    public static void setDataSourceType(String type) {
        contextHolder.set(type);
    }

    /**
     * 获取数据源类型
     * 数据源类型枚举：DataSourceType
     *
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清除当前线程数据源类型数据
     * 数据源类型枚举：DataSourceType
     */
    public static void clear() {
        contextHolder.remove();
    }
}
