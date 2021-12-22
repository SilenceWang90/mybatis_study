package com.wp.controller;

import com.wp.entities.YuML;
import com.wp.mappers.YuMLMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname MybatisStudyAnotationController
 * @Description 通过注解形式使用mybatis
 * @Date 2019/4/21 18:20
 * @Created by wangpeng116
 */
@RestController
@RequestMapping(value = "/mybatisStudyAno")
@Slf4j
public class MybatisStudyAnotationController {
    @Autowired
    private YuMLMapper yuMLMapper;

    @RequestMapping(value = "/testResultsAnno")
    public String testResultsAnno() {
        List<YuML> list = yuMLMapper.findAll();
        YuML yuML = yuMLMapper.findByParam(3L);
        log.info("输出结果：{}", yuML);
        return "OK";
    }

    /**
     * 插入数据后获取id
     * @return
     */
    @RequestMapping(value = "/testInsertAnno")
    public String testInsertAnno() {
        YuML obj = new YuML();
        obj.setName("Yml");
        obj.setSex("girl");
        Long result = yuMLMapper.insertOne(obj);
        log.info("插入数据的id为：{}", obj.getId());
        log.info("result为：{}", result);
        return "OK";
    }

    /**
     * SqlProvider使用
     * @return
     */
    @RequestMapping(value = "/testProvider")
    public String testProvider(){
        YuML obj = new YuML();
        obj.setId(10L);
        YuML yuML = yuMLMapper.getByProvider(obj);
        log.info("查询结果：{}",yuML);
        return "OK";
    }
}
