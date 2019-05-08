package com.aiolos.security.core.properties;

import com.aiolos.security.core.enums.LoginType;
import lombok.Data;

/**
 * @author Aiolos
 * @date 2019-05-04 12:38
 */
@Data
public class BrowserProperties {

    /**
     * 这里的变量名必须和yml配置文件中的一样，否get方法获取不到
     */
    private String loginPage = "/default-login.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 60 * 10;

    private SessionProperties session = new SessionProperties();

    private String signOutUrl;
}
