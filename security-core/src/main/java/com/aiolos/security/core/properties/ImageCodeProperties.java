package com.aiolos.security.core.properties;

import lombok.Data;

/**
 * @author Aiolos
 * @date 2019-05-08 00:33
 */
@Data
public class ImageCodeProperties {

    private int width = 67;

    private int height = 23;

    private int length = 4;

    private int expiredIn = 60;

    private String url;
}
