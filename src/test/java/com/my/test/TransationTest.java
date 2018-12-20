package com.my.test;

import com.my.proj.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangzb
 * @since 2017/11/25 10:02
 */
public class TransationTest extends TestBase {

    @Autowired
    private UserService userService;

    @Test
    public void testPrint(){
        userService.insertData();
        System.out.println("OUT PRINT");
    }
}
