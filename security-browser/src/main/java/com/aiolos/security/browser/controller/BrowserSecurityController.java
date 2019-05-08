package com.aiolos.security.browser.controller;

import com.aiolos.security.browser.support.SimpleResponse;
import com.aiolos.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-04 12:08
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    /**
     * SpringSecurity会把当前的请求缓存到session中
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 用于请求跳转的工具类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，跳转到这
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 从session中把请求取出
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {

            // 跳转请求的url
            String target = savedRequest.getRedirectUrl();
            log.info("引发跳转的url是{}", target);

            // 判断是否是html请求
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {

                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        return new SimpleResponse("访问的服务需要身份认证");
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid() {
        return new SimpleResponse("session已失效");
    }
}
