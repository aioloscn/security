package com.aiolos.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Aiolos
 * @date 2019-05-08 09:52
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
