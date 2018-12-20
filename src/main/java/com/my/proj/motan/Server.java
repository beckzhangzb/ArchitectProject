package com.my.proj.motan;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Motan @ start server
 * @author zhangzb
 * @since 2017/7/19 14:45
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/motan/motan_server.xml");

        /**
         * 如果采用zookeeper来注册服务，需要显示调用心跳开关，注册到zookeeperf
         */
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        System.out.println("===============server start!!! ========================");
    }
}