package com.aiolos.security.app.config;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 资源服务器
 * @author Aiolos
 * @date 2019-05-22 14:33
 */
@Configuration
@EnableResourceServer
public class AiolosResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler aiolosAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler aiolosAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(aiolosAuthenticationSuccessHandler)
                .failureHandler(aiolosAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/code/image",
                        "/session/invalid").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
