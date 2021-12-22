package com.wp.entities;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname Father
 * @Description TODO
 * @Date 2019/4/13 16:40
 * @Created by wangpeng116
 */
@Data
public class Father extends User {
    private Long id;
    private Long userId;
    private String name;
    private BigDecimal weight;
    private BigDecimal asset;
}
