package com.aiolos.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义拦截器
 * 可以拿到原始的http请求和响应的信息，和处理的方法信息，但是拿不到方法的参数
 * 光配置@Component并不能使这个拦截器起作用，需要WebConfig中配置
 * @author Aiolos
 * @date 2019-05-02 14:12
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 请求之前调用
     * 如果返回false，不再调用后面的handle
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle");
//        log.info(((HandlerMethod)handler).getBean().getClass().getName());
//        log.info(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    /**
     * 控制器方法处理之后调用
     * 如果控制器抛出了异常该方法就不会被调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("postHandle");
        Long start = Long.parseLong(request.getAttribute("startTime").toString());
        log.info("post handle time consuming: {}ms", new Date().getTime() - start);
    }

    /**
     * 无论控制器是否报错都会调用该方法
     * 在ExceptionHandler之后调用，所以拿不到异常处理器中的异常
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("afterCompletion");
        Long start = Long.parseLong(request.getAttribute("startTime").toString());
        log.info("after completion time consuming: {}ms", new Date().getTime() - start);
        log.info("ex is {}", ex);
    }
}
