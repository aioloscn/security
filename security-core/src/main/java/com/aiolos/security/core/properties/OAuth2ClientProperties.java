package com.aiolos.security.core.properties;

import lombok.Data;

/**
 * @author Aiolos
 * @date 2019-05-18 14:30
 */
@Data
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;

    /**
     * 第三方应用appSecret
     */
    private String clientSecret;

    /**
     * 针对此应用发出的token的有效期
     */
    private int accessTokenValidateSeconds = 7200;
}
