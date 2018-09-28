package com.csdn.demospringboot.dao;

import com.csdn.demospringboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserDao {
    public int insertUser(@Param("user") User user);
    public int deleteUser(@Param("id") String userId);
    public int updateUser(@Param("user") User user);
    public List<User> queryAllUser();
    public User queryUserById(@Param("id") String userId);
}
