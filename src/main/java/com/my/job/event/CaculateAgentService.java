package com.my.job.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangzb
 * @date 2021/3/25 20:52
 * @desc
 */
@Service
public class CaculateAgentService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void caculateData() {
        Agent agent = new Agent();
        agent.setId(1001);
        agent.setName("王五");
        agent.setPhone("13889766712");
        agent.setTime(new Date());

        applicationEventPublisher.publishEvent(new CaculateAgentEvent(this, agent));
    }


}
