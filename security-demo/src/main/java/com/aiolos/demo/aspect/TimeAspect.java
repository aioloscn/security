package com.aiolos.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 可以拿到方法传进来的值，但是拿不到原始的http请求和响应对象
 * @author Aiolos
 * @date 2019-05-02 15:41
 */
@Slf4j
@Aspect
@Component
public class TimeAspect {

    /**
     * 应用于该类任何方法任何参数任何返回值的方法
     */
    @Around("execution(* com.aiolos.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        log.info("time aspect start");

        // 所拦截的方法的参数
        for (Object arg : pjp.getArgs()) {
            log.info("arg: {}", arg);
        }
        long start = new Date().getTime();
        log.info("time aspect time consuming: {}ms", new Date().getTime() - start);

        // 所拦截的方法的返回值
        Object object = pjp.proceed();

        log.info("time aspect end");
        return object;
    }
}
