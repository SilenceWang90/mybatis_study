package com.wp.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname UserReqParam
 * @Description TODO
 * @Date 2019/4/21 10:42
 * @Created by wangpeng116
 */
@Data
public class UserReqParam {
    private Long id;
    private String name;
    private Integer age;
    private BigDecimal weight;
    private String address;
}
