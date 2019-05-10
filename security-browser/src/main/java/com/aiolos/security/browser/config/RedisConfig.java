package com.aiolos.security.browser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Aiolos
 * @date 2019-05-09 15:27
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {
}
