package com.my.job.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhangzb
 * @date 2021/3/25 20:42
 * @desc
 */
public class CaculateAgentEvent extends ApplicationEvent {

    private Agent agent;

    public CaculateAgentEvent(Object source, Agent agent) {
        super(source);
        this.agent = agent;
    }

    public Object getTargetObj() {
        return this.agent;
    }

    public Agent getAgent() {
        return this.agent;
    }
}
