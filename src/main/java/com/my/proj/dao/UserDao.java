package com.my.proj.dao;

import com.my.proj.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangzb
 * @Date 2017/6/5 11:09
 */
@Repository
public interface UserDao {
    List<User> getUserInfoList();

    int saveUserInfo(User user);

}
