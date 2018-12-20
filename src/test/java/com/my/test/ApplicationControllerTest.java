//package com.my.test;
//
//import com.my.proj.controller.SampleController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
///**
// * @Author zhangzb
// * @Date 2017/6/7 10:18
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MockServletContext.class)
//@WebAppConfiguration
//public class ApplicationControllerTest {
//    private MockMvc mvc;
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new SampleController()).build();
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        System.out.println("test begin:");
//        mvc.perform(MockMvcRequestBuilders.get("/get-address").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("This is chengdu!")));
//        System.out.println("test end!");
//    }
//}
