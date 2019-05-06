package com.aiolos.security.browser;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Aiolos
 * @date 2019-04-14 23:50
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler aiolosAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler aiolosAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 表单登录
         * 指定登录页面的url
         * security默认使用UsernamePasswordAuthenticationFilter处理login的post请求，loginProcessingUrl指定处理自定义请求
         * 使用自定义表单登录成功处理器
         * 使用自定义表单登录失败处理器
         * 任何请求
         * 匹配到该url不需要身份认证，避免重定向死循环
         * 都需要身份认证
         * csrf跨站请求伪造的防护功能先不启用
         */
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(aiolosAuthenticationSuccessHandler)
                .failureHandler(aiolosAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
