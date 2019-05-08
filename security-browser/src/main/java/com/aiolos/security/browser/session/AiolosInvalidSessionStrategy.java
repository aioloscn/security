package com.aiolos.security.browser.session;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-08 15:48
 */
public class AiolosInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public AiolosInvalidSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }


    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}
