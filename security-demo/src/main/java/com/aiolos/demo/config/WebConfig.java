package com.aiolos.demo.config;

import com.aiolos.demo.filter.TimeFilter;
import com.aiolos.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要用于将第三方filter加到项目里
 * @author Aiolos
 * @date 2019-05-02 13:34
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 使自定义的拦截器起作用
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean bean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        bean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        // 所有路径TimeFilter过滤器都起作用
        urls.add("/*");
        bean.setUrlPatterns(urls);
        return bean;
    }
}
