package com.my.proj.service;

import com.my.proj.entity.User;

import java.util.List;

/**
 * @Author zhangzb
 * @Date 2017/6/5 10:51
 */
public interface UserService {

    List<User> getUserInfoList();

    /**
     * 验证插入数据事务问题
     */
    void insertData();
}
