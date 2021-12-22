package com.wp.mappers;

import com.wp.entities.User;
import com.wp.model.UserReqParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    User findById(@Param("id") Long id);
    User findByReqParam(@Param("obj") UserReqParam req);
    List<User> findAll();
    List<User> findAllExtendUsers();

    Integer insertUser(@Param("user") User user);
}
