package com.wp.mappers;

import com.wp.entities.Father;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname FatherMapper
 * @Description TODO
 * @Date 2019/4/13 16:42
 * @Created by wangpeng116
 */
@Repository
public interface FatherMapper {
    List<Father> findAll();

    List<Father> findAllByUserId();
}
