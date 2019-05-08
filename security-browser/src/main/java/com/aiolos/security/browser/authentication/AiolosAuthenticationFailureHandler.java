package com.aiolos.security.browser.authentication;

import com.aiolos.security.browser.support.SimpleResponse;
import com.aiolos.security.core.enums.LoginType;
import com.aiolos.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-04 16:39
 */
@Slf4j
@Component("aiolosAuthenticationFailureHandler")
public class AiolosAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        log.info("login failed");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {

            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        } else {
            // 跳转
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }
    }
}
