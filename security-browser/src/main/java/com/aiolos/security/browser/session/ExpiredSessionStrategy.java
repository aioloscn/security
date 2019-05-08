package com.aiolos.security.browser.session;

import com.aiolos.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-08 14:51
 */
public class ExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public ExpiredSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    /**
     * session失效事件
     * @param event
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
