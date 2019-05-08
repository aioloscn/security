package com.aiolos.code;

import com.aiolos.security.core.validate.code.ImageCode;
import com.aiolos.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Aiolos
 * @date 2019-05-08 10:20
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        return null;
    }
}
