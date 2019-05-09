package com.aiolos.security.browser.session;

import com.aiolos.security.browser.support.SimpleResponse;
import com.aiolos.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-08 15:57
 */
@Slf4j
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String destinationUrl;

    /**
     * 系统配置信息
     */
    private SecurityProperties securityProperties;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(SecurityProperties securityProperties) {

        String invalidSessionUrl = securityProperties.getBrowser().getSession().getSessionInvalidUrl();
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
        this.destinationUrl = invalidSessionUrl;
        this.securityProperties = securityProperties;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("session已失效");

        if (createNewSession)
            request.getSession();

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {

            if (StringUtils.equals(sourceUrl, securityProperties.getBrowser().getLoginPage())
                    || StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignOutUrl())) {
                targetUrl = destinationUrl;
            } else {
                targetUrl = sourceUrl;
            }
            log.info("跳转到：{}", targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    protected Object buildResponseContent(HttpServletRequest request) {

        String message = "strategy session已失效";
        if (isConcurrency()) {
            message = message + "，账号已在其他客户端登录";
        }
        return new SimpleResponse(message);
    }

    /**
     * session失效是否是并发导致的
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
