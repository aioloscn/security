package com.aiolos.security.core.config;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 让SecurityProperties配置读取器生效
 * @author Aiolos
 * @date 2019-05-04 12:48
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {
}
