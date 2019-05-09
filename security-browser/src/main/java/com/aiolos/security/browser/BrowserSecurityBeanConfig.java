package com.aiolos.security.browser;

import com.aiolos.security.browser.logout.AiolosLogoutSuccessHandler;
import com.aiolos.security.browser.session.AiolosInvalidSessionStrategy;
import com.aiolos.security.browser.session.ExpiredSessionStrategy;
import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @author Aiolos
 * @date 2019-05-08 15:46
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new AiolosInvalidSessionStrategy(securityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new ExpiredSessionStrategy(securityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new AiolosLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }
}
