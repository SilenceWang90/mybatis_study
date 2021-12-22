package com.wp.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private BigDecimal weight;
    private String address;
    private Date bornDate;
    private Date bornDateTime;
}
