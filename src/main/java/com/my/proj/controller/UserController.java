package com.my.proj.controller;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.my.proj.entity.User;
import com.my.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


      /*http://localhost:8080/getUserInfo*/

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public List<User> getUserInfo() {
        List<User> users = userService.getUserInfoList();
        if(!CollectionUtils.isEmpty(users)){
            for(User user : users){
                logger.info("user:" + JSONObject.toJSONString(user));
            }
        }
        return users;
    }
}
