package com.wp.controller;

import com.google.common.collect.Lists;
import com.wp.entities.WangP;
import com.wp.mappers.WangPMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname MyBatisStudyOgnlController
 * @Description TODO
 * @Date 2019/5/3 9:39
 * @Created by wangpeng116
 */
@RequestMapping(value = "/myBatisStudyOgnl")
@RestController
@Slf4j
public class MyBatisStudyOgnlController {
    @Autowired
    private WangPMapper wangPMapper;

    /**
     * <if></if>标签+Ognl扩展用法
     * <sql></sql>标签以及<include></include>标签
     */
    @RequestMapping("/testIf")
    public String testIf() {
        WangP wangP = new WangP();
        wangP.setId(1L);
        WangP result = wangPMapper.findByIf(wangP);
        log.info("result:{}", result);
        log.info("list:{}", wangPMapper.findByIncludeSql());
        return "OK";
    }

    /**
     * <choose></choose>标签使用
     * @return
     */
    @RequestMapping("/testChoose")
    public String testChoose(){
        WangP wangP = new WangP();
//        wangP.setId(1L);
        WangP result = wangPMapper.findByChoose(wangP);
        log.info("result:{}", result);
        return "OK";
    }

    /**
     * where、set(并设置update常用sql模板)、trim标签使用
     * trim标签可以实现set和where标签，底层就是通过TrimSqlNode实现的：
     * <where> = <trim prefix="where" prefixOverrides="AND |OR ">...</trim>，这里的AND和OR后面的空格不能省略！！！！为了避免碰到orders等单词
     * <set> = <trim prefix="SET" suffixOverrides=","></trim>
     * <trim>标签有以下属性：
     * prefix：当trim元素内包含内容时，会给该内容增加prefix指定的前缀
     * prefixOverrides：当trim元素内包含内容时，会把内容中匹配的前缀去掉(比如去掉where中多余的and或者or)
     * suffix：当trim元素内包含内容时，会给该内容增加suffix指定的后缀
     * suffixOverrides：当trim元素内包含内容时，会把内容中匹配的后缀去掉(比如update set中去掉多余的逗号)
     */
    @RequestMapping("/testCtestOptimizehoose")
    public String testOptimize(){
        WangP wangP = new WangP();
        wangP.setId(3L);
        wangP.setName("wangp");
        WangP resultWhere = wangPMapper.findByOptimize(wangP);
        wangP.setName("wangpeng");
        Integer resultUpdate = wangPMapper.updateBySetSql(new WangP().setId(3L));
        log.info("resultWhere:{}", resultWhere);
        log.info("resultUpdate:{}", resultUpdate);
        return "OK";
    }

    /**
     * foreach用法
     * @return
     */
    @RequestMapping("/testForEach")
    public List<WangP> testForEach(){
//        List<WangP> list = wangPMapper.findByIds(Lists.newArrayList(1L,2L,3L));
        List<WangP> list = wangPMapper.findByIds(Lists.newArrayList());
        /**实现批量插入**/
        WangP obj1 = new WangP();
        obj1.setName("wangpp");
        obj1.setAge(12);
        obj1.setSex("boy");
        obj1.setAddress("bj");
        WangP obj2 = new WangP();
        obj2.setName("wanggppeng");
        obj2.setAge(13);
        obj2.setSex("boy");
        obj2.setAddress("bbj");
        WangP obj3 = new WangP();
        obj3.setName("wanggpp");
        obj3.setAge(14);
        obj3.setSex("boy");
        obj3.setAddress("bjj");
        List<WangP> insertList = Lists.newArrayList(obj1,obj2,obj3);
        Integer result = wangPMapper.insertList(insertList);
        log.info("插入个数：{}",result);
        return list;
    }

    /**
     * bind用法：可以使用OGNL表达式创建一个变量并将其绑定到上下文中
     * 另外可以对多数据库进行支持《Mybatis从入门到精通》P86
     * @return
     */
    @RequestMapping("/testBind")
    public List<WangP> testBind(){
        List<WangP> list = wangPMapper.findByBind("gg");
        return list;
    }

    /**
     * 自定义测试：
     *    大于：&gt;
     *    大于等于：&gt;=
     *    小于：&lt;
     *    小于等于：&lt;=
     * @return
     */
    @RequestMapping("/testCustom")
    public List<WangP> testCustom(){
        List<WangP> list = wangPMapper.testCustom(new WangP());
        return list;
    }

}
