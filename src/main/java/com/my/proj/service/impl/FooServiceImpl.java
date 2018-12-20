package com.my.proj.service.impl;

import com.my.proj.service.FooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Motan @ create and start RPC Server
 * @author zhangzb
 * @since 2017/7/19 14:31
 */
public class FooServiceImpl implements FooService {

    private static final Logger LOG = LoggerFactory.getLogger(FooServiceImpl.class);

    @Override
    public String hello(String name) {
        System.out.println(name + " invoked rpc service");
        LOG.info(name + " invoked rpc service");
        return "hello " + name;
    }
}
