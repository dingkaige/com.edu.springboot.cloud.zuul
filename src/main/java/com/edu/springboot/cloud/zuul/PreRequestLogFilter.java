package com.edu.springboot.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: zuul过滤器
 * @program: cloud-zuul
 * @author: dingkaige
 * @date: 2019-12-25 10:07
 **/
@Log4j2
public class PreRequestLogFilter extends ZuulFilter {
    // 过滤器类型
    @Override
    public String filterType() {
        return "pre";//在请求路由到目标之前执行。一般用于请求认证、负载均衡和日志记录
//        return "routing";//将请求路由到微服务，用于构建发送给微服务的请求，并使用Apache Http Client或者Netflix Ribbon请求微服务
//        return "post";//在目标请求返回后执行。一般会在此步骤添加响应头、收集统计和性能数据等。
//        return "ERROR";//出错时执行
    }

    // 优先级，越大越靠后执行
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL())+"\n"+System.currentTimeMillis());
        return null;
    }
}
