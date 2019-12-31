/*
package com.my.proj.motan;

import com.my.proj.service.FooServiceAsync;
import com.weibo.api.motan.rpc.Future;
import com.weibo.api.motan.rpc.FutureListener;
import com.weibo.api.motan.rpc.ResponseFuture;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

*/
/**
 * Motan : 测试主类
 * @author zhangzb
 * @since 2017/7/19 14:53
 *//*

public class TestMain {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"classpath:/motan/motan_client.xml"});

        FooServiceAsync service = (FooServiceAsync) ctx.getBean("remoteService");

        // sync call
        System.out.println(service.hello("motan"));

        // async call
        ResponseFuture future = service.helloAsync("motan async ");
        System.out.println(future.getValue());

        // multi call
        ResponseFuture future1 = service.helloAsync("motan async multi-1");
        ResponseFuture future2 = service.helloAsync("motan async multi-2");
        System.out.println(future1.getValue() + ", " + future2.getValue());

        // async with listener
        FutureListener listener = new FutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                System.out.println("async call "
                        + (future.isSuccess() ? "sucess! value:" + future.getValue() : "fail! exception:"
                        + future.getException().getMessage()));
            }
        };
        ResponseFuture future3 = service.helloAsync("motan async multi-1");
        ResponseFuture future4 = service.helloAsync("motan async multi-2");
        future3.addListener(listener);
        future4.addListener(listener);
    }
}
*/
