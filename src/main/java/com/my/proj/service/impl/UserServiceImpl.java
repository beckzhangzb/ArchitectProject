package com.my.proj.service.impl;

import com.my.proj.dao.UserDao;
import com.my.proj.entity.User;
import com.my.proj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangzb
 * @Date 2017/6/5 10:52
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserInfoList() {
        return userDao.getUserInfoList();
    }

    @Override
    public void insertData() {
        User user = new User();
        user.setAge(13);
        user.setName("123adf");
        user.setPwd("6548791");
        user.setTelephone("1874595245");

        userDao.saveUserInfo(user);
        saveTestData(user);
    }

    /**
     * 保存测试方法
     */
    public void saveTestData(User user){
        user.setName("111111111111111111111");
        userDao.saveUserInfo(user);

        user.setName("222222222222222222222");
        userDao.saveUserInfo(user);
    }
}
