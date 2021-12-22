package com.wp.entities;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname WangP
 * @Description TODO
 * @Date 2019/5/2 11:19
 * @Created by wangpeng116
 */
@Data
@Accessors(chain = true)
public class WangP {
    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
}
