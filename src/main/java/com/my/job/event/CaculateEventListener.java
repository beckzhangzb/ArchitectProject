package com.my.job.event;

import org.springframework.context.ApplicationListener;

/**
 * @author zhangzb
 * @date 2021/3/25 21:03
 * @desc
 */
public class CaculateEventListener implements ApplicationListener<CaculateAgentEvent> {

    @Override
    public void onApplicationEvent(CaculateAgentEvent event) {
        System.out.printf(this.getClass().getName() + " -- id=[%s],name=[%s], phone=[%s]\n",
            event.getAgent().getId(), event.getAgent().getName(), event.getAgent().getPhone());
    }
}
