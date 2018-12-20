package com.my.proj.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Motan @ test interface,
 * 异步：只需要在接口类中加上@MotanAsync注解，然后client端稍作修改。server端不需要做任何修改。
 * 编译时，Motan自动生成异步service类，生成路径为target/generated-sources/annotations/，生成的类名为service名加上Async，
 * 例如service类名为FooService.java,则自动生成的类名为FooServiceAsync.java。
 * 另外，需要将motan自动生产类文件的路径配置为项目source path，可以使用maven plugin或手动配置
 * @author zhangzb
 * @since 2017/7/19 14:30
 */
//@MotanAsync
@Path("/rest-demo")
public interface FooService {
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String hello(String name);
}
