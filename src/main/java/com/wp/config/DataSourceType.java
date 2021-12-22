package com.wp.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Classname DataSourceType
 * @Description 数据源类型切换类
 * @Date 2021/12/22 16:02
 * @Created by wangpeng116
 */
@Slf4j
public class DataSourceType {

    /**
     * 数据源类型枚举
     */
    public enum DataBaseType {
        primary, second
    }

    // 增加线程安全
    private static final ThreadLocal<DataBaseType> type = new ThreadLocal<>();

    /**
     * 当前线程设置数据源类型
     *
     * @param dataBaseType 数据源类型
     */
    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            type.set(DataBaseType.primary);
        } else {
            type.set(dataBaseType);
        }
        log.info("当前数据源为：{}", dataBaseType);
    }

    /**
     * 获取数据源类型
     *
     * @return
     */
    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = type.get() == null ? DataBaseType.primary : type.get();
        return dataBaseType;
    }

    /**
     * 清空数据源类型
     */
    public static void clearDataBaseType() {
        type.remove();
    }
}
