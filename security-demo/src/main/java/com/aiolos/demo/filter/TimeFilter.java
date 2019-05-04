package com.aiolos.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义过滤器
 * 可以拿到原始的http请求和响应的信息，但是拿不到请求的controller和方法
 * @author Aiolos
 * @date 2019-05-02 13:16
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("time filter start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        log.info("time filter, time consuming: {}ms", new Date().getTime() - start);
        log.info("time filter finish");
    }

    @Override
    public void destroy() {
        log.info("time filter destroy");
    }
}
