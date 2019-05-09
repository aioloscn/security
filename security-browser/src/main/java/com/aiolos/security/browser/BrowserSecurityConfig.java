package com.aiolos.security.browser;

import com.aiolos.security.core.properties.SecurityProperties;
import com.aiolos.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;

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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动的时候创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(aiolosAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        /**
         * 将自定义的验证码过滤器加到UsernamePasswordAuthenticationFilter过滤器前面，优先验证验证码
         * 表单登录
         * 指定登录页面的url
         * security默认使用UsernamePasswordAuthenticationFilter处理login的post请求，loginProcessingUrl指定处理自定义请求
         * 使用自定义表单登录成功处理器
         * 使用自定义表单登录失败处理器
         * 添加自动登录的token仓库
         * token有效时间
         * 根据token拿到用户名密码后用userDetailsService去验证登录
         * @Deprecated 设置session失效后要跳转的url
         * 设置session的失效策略
         * 设置最大session数为1，后面登录会让之前的session失效
         * true：当session数量达到最大数量后，阻止后面的登录
         * 自定义session并发失效策略
         * 重新配置logout
         * logout的请求  spring security 默认为/logout
         * 配置logout重定向的url logoutSuccessUrl和logoutSuccessHandler互斥
         * 配置logoutSuccessHandler
         * 退出后删除cookie
         * 任何请求
         * 匹配到该url不需要身份认证，避免重定向死循环
         * 都需要身份认证
         * csrf跨站请求伪造的防护功能先不启用
         */
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(aiolosAuthenticationSuccessHandler)
                .failureHandler(aiolosAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(myUserDetailsService)
                .and()
                .sessionManagement()
//                .invalidSessionUrl("/session/invalid")
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
//                .logoutSuccessUrl("/demo-login.html")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(securityProperties.getBrowser().getLoginPage())
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
