package com.aiolos.security.app.config;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 当配置文件里配有opm.security.oauth2.storeType并且value为jwt的时候这个类里所有配置生效，没有配则不生效
 * @author Aiolos
 * @date 2019-05-17 16:54
 */
//@Configuration
//@ConditionalOnProperty(prefix = "opm.security.oauth2", name = "storeType", havingValue = "redis")
public class TokenStoreConfig {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    public TokenStore redisTokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    /**
     * 当配置文件里配有opm.security.oauth2.storeType并且value为jwt的时候这个类里所有配置生效
     * matchIfMissing = true 如果配置文件里没有配，这个类生效
     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "opm.security.oauth2", name = "storeType",
//            havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {

        /*@Autowired
        private SecurityProperties securityProperties;

        @Bean
        public JwtTokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {

            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return accessTokenConverter;
        }*/
    }
}
