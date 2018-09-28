package com.csdn.demospringboot.web;

import com.csdn.demospringboot.dao.UserDao;
import com.csdn.demospringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserDao userDao;

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User get(@PathVariable String id) {
        return userDao.queryUserById(id);
    }

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {
        return userDao.deleteUser(id)==1?"success":"false" ;
    }



    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public String update(@RequestBody User user){
        return userDao.updateUser(user)==1 ?"success":"false";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String save(@RequestBody User user){

        return   userDao.insertUser(user)==1 ?"success":"false";
    }

}
