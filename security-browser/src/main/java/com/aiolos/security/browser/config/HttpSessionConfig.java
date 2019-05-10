package com.aiolos.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

/**
 * @author Aiolos
 * @date 2019-05-09 16:47
 */
@Configuration
public class HttpSessionConfig {

    /**
     * 让spring session不再执行config命令
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
