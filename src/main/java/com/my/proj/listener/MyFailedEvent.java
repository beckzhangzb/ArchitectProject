package com.my.proj.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author zhangzb
 * @Date 2017/6/6 10:35
 */
public class MyFailedEvent extends ApplicationFailedEvent {
    private static final Logger LOG = LoggerFactory.getLogger(MyFailedEvent.class);

    /**
     * Create a new {@link ApplicationFailedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application was running with
     * @param context     the context that was being created (maybe null)
     * @param exception   the exception that caused the error
     */
    public MyFailedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context, Throwable exception) {
        super(application, args, context, exception);
        System.out.println("异常：MyFailedEvent error happen !");
        LOG.info("异常：MyFailedEvent error happen !" );
    }
}
