package com.aiolos.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Aiolos
 * @date 2019-05-04 12:37
 */
@Data
@ConfigurationProperties(prefix = "aiolos.security")
public class SecurityProperties {

    /**
     * 这里的变量名必须和yml配置文件中的一样，否get方法获取不到
     */
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();
}
