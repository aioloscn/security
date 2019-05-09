package com.aiolos.security.browser.logout;

import com.aiolos.security.browser.support.SimpleResponse;
import com.aiolos.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-09 12:09
 */
@Slf4j
public class AiolosLogoutSuccessHandler implements LogoutSuccessHandler {

    private String signOutUrl;

    public AiolosLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("已退出");

        if (StringUtils.isBlank(signOutUrl)) {

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("已退出")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }
}
