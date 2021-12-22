package com.wp.controller;

import com.github.pagehelper.PageHelper;
import com.wp.entities.Father;
import com.wp.entities.User;
import com.wp.entities.WangP;
import com.wp.entities.YuML;
import com.wp.mappers.FatherMapper;
import com.wp.mappers.UserMapper;
import com.wp.mappers.WangPMapper;
import com.wp.mappers.YuMLMapper;
import com.wp.model.UserReqParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.List;

/**
 * @Classname MybatisStudyController
 * @Description 通过xml方式使用mybatis
 * @Date 2019/4/8 16:27
 * @Created by wangpeng116
 */
@RestController
@RequestMapping(value = "/mybatisStudyXml")
@Slf4j
public class MybatisStudyXMLController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FatherMapper fatherMapper;
    @Autowired
    private WangPMapper wangPMapper;
    @Autowired
    private YuMLMapper yuMLMapper;

    @RequestMapping("/testPage")
    public void testPage(@RequestParam("pageNum") int pageNum,
                         @RequestParam("pageSize") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.findAll();
        log.info("list",list);
    }
    /**
     * select测试
     *
     * @return
     */
    @RequestMapping(value = "/testSelect")
    public String testSelect() {
        User user = userMapper.findById(1L);
        System.out.println("findById：" + user);
        List<User> list = userMapper.findAll();
        System.out.println("findAll：");
        list.forEach(obj -> {
            System.out.println(obj);
        });
        UserReqParam userReqParam = new UserReqParam();
        userReqParam.setId(5L);
        User user1 = userMapper.findByReqParam(userReqParam);
        System.out.println("user1：" + user1);
        List<Father> fathers = fatherMapper.findAllByUserId();
        fathers.forEach(obj -> {
            System.out.println(obj.getName() + ";" + obj.getAsset() + ";" + obj.getAddress() + ";");
        });
        return "OK";
    }


    /**
     * select测试
     * 反射给私有属性赋值
     * 其实继承可以继承到父类的私有属性，只是无法直接使用，但是反射可以直接获得父类的属性
     *
     * @return
     */
    @RequestMapping(value = "/testSelectAndInherit")
    public String testSelectAndInherit() {
        Class fatherClass = Father.class;
        try {
            Father father = (Father) fatherClass.newInstance();
            Field addressField = fatherClass.getSuperclass().getDeclaredField("address");
            addressField.setAccessible(true);
            addressField.set(father, "地址");
            System.out.println("father地址：" + father.getAddress());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    /**
     * 测试ResultMap的继承
     * @return
     */
    @RequestMapping(value = "/testResultMapExtends")
    public String testResultMapExtends() {
        WangP wangP = wangPMapper.findById(1L);
//        YuML yuML = yuMLMapper.findById(2L);
        log.info("输出结果：{}",wangP);
//        log.info("输出结果：{}",yuML);
        return "OK";
    }

    /**
     * insert测试
     * 通过<selectKey>直接获取插入数据的主键id</selectKey>
     * 很好用的优化功能！！！！！！！：业务上可能存在插入后需要获得该条数据主键id去更新其他数据库表的数据，这种情况下一般我们需要再查询数据库才能获得。
     * 但是如果用了这种方式后，mybatis会自动将该条数据生成的主键值返回给当前插入对象的主键属性上，减少一次查询。
     * 虽然大部分业务主键都不是自增的而是系统特殊生成的，但如果是基于数据库的，这种方式就很棒。
     * @return
     */
    @RequestMapping(value = "/testInsert")
    public String testInsert() {
        User user = new User();
        user.setName("ymlu");
        user.setAge(15);
        user.setWeight(new BigDecimal("50.00"));
        user.setAddress("house");
        /**java.time.LocalDate --> java.util.Date**/
        LocalDate date = LocalDate.now();
        ZoneId zoneArea = ZoneId.systemDefault();
        Instant instantDate = date.atStartOfDay().atZone(zoneArea).toInstant();
        user.setBornDate(Date.from(instantDate));
        /**java.time.LocalDateTime --> java.util.Date**/
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        user.setBornDateTime(Date.from(instant));
        Integer result = userMapper.insertUser(user);
        //！！！！！！！！Mybatis会把该条数据的主键值赋给主键对应的id属性
        System.out.println("user：" + user);
        System.out.println("result：" + result);
        return "OK";
    }

    /**
     * update测试
     *
     * @return
     */
    @RequestMapping(value = "/testUpdate")
    public String testUpdate() {
        User user = new User();
        user.setAge(15);
        user.setWeight(new BigDecimal("50.00"));
        user.setAddress("house");
        /**java.time.LocalDate --> java.util.Date**/
        LocalDate date = LocalDate.now();
        ZoneId zoneArea = ZoneId.systemDefault();
        Instant instantDate = date.atStartOfDay().atZone(zoneArea).toInstant();
        user.setBornDate(Date.from(instantDate));
        /**java.time.LocalDateTime --> java.util.Date**/
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        user.setBornDateTime(Date.from(instant));
        Integer result = userMapper.insertUser(user);
        return "OK";
    }

    /**
     * delete测试
     *
     * @return
     */
    @RequestMapping(value = "/testDelete")
    public String testDelete() {
        return "OK";
    }
}
