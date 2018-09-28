package com.csdn.demospringboot.dao;

import com.csdn.demospringboot.DemospringbootApplication;
import com.csdn.demospringboot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemospringbootApplication.class)
@EnableAutoConfiguration
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void insertUser() throws Exception {
        User user=new User();
        user.setUserName("user1");
        user.setUserPassword("pwd1");
        assertEquals(1,userDao.insertUser(user));
    }

    @Test
    public void deleteUser() throws Exception {
        List<User> userList=userDao.queryAllUser();
        assertEquals(1,userDao.deleteUser(userList.get(0).getUserId()));
    }



    @Test
    public void queryAllUser() throws Exception {

    }

    @Test
    public void queryUserById() throws Exception {

    }

}