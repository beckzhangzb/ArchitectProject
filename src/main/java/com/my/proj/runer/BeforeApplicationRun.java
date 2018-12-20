package com.my.proj.runer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author zhangzb
 * @Date 2017/6/6 10:59
 */
@Component
public class BeforeApplicationRun implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(BeforeApplicationRun.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("此Runer 在application.run()结束之前运行完成,可以用来初始化准备！");
        LOG.info("此Runer 在application.run()结束之前运行完成,可以用来初始化准备！");
    }
}
