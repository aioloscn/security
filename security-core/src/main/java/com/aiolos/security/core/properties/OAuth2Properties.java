package com.aiolos.security.core.properties;

import lombok.Data;

/**
 * @author Aiolos
 * @date 2019-05-18 14:31
 */
@Data
public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    /**
     * jwt密签
     */
    private String jwtSigningKey = "opm";
}
