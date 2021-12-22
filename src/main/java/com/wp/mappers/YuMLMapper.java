package com.wp.mappers;

import com.wp.entities.YuML;
import com.wp.model.sqlprovider.YuMLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname YuMLMapper
 * @Description TODO
 * @Date 2019/5/2 11:22
 * @Created by wangpeng116
 */
@Repository
public interface YuMLMapper {
    YuML findById(@Param("id") Long id);

    /**
     * @return
     * @Results用于注解方式的字段属性映射
     */
    @Results(id = "BaseResultColumns", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
    })
    @Select("select id,name,sex from yu_ml")
    List<YuML> findAll();

    /**
     * @param id
     * @return
     * @ResultMap可以通过id的方式通用
     */
    @ResultMap(value = "BaseResultColumns")
    @Select("select id,name,sex from yu_ml where id=#{id}")
    YuML findByParam(@Param("id") Long id);

    @Insert("insert into yu_ml(name,sex) values (#{name},#{sex})")
    /**
     * 返回自增型主键
     * 需要注意的是如果此时给实体对象yuML设置@Param时Mybatis在执行插入语句会报错，具体原因不明
     */
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**
     * 返回非自增主键(自增的也可以用，下面的例子就是自增的，Last_Insert_ID获取的就是插入数据后(before = false)，获取最新数据的id)
     */
    @SelectKey(statement = "select Last_Insert_ID() as id", keyProperty = "id", resultType = Long.class, before = false)
    Long insertOne(YuML yuML);

    @SelectProvider(type = YuMLProvider.class, method = "getByProvider")
    YuML getByProvider(@Param("yuML") YuML yuML);
}
