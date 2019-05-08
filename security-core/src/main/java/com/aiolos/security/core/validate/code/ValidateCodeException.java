package com.aiolos.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Aiolos
 * @date 2019-05-08 00:22
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
