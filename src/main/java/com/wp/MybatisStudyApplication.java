package com.wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MybatisStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisStudyApplication.class,args);
    }
}
