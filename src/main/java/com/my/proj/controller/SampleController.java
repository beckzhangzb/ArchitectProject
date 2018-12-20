package com.my.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @RequestMapping("/get-home")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/get-address")
    @ResponseBody
    String getAddress() {
        return "This is chengdu!";
    }
}