package com.wp.mappers;

import com.wp.entities.WangP;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname WangPMapper
 * @Description TODO
 * @Date 2019/5/2 11:21
 * @Created by wangpeng116
 */
@Repository
public interface WangPMapper {
    WangP findById(@Param("id") Long id);

    WangP findByIf(@Param("wp") WangP wp);

    List<WangP> findByIncludeSql();

    WangP findByChoose(@Param("wp") WangP wp);

    WangP findByOptimize(@Param("wp") WangP wp);

    Integer updateBySetSql(@Param("wp") WangP wp);

    List<WangP> findByIds(@Param("ids") List<Long> ids);

    Integer insertList(@Param("list") List<WangP> list);

    List<WangP> findByBind(@Param("str") String likeName);

    List<WangP> testCustom(@Param("param") WangP wangP);
}
