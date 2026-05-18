package com.my.test;

import com.my.job.event.CaculateAgentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangzb
 * @date 2021/3/25 21:15
 * @desc
 */
public class ApplicationEventTest extends BaseTests{
    @Autowired
    private CaculateAgentService caculateAgentService;

    @Test
    public void contextLoads() {
        caculateAgentService.caculateData();
    }

}
