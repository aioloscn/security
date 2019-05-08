package com.aiolos.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Aiolos
 * @date 2019-05-06 23:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode implements Serializable {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    /**
     * x秒后过期
     * @param image
     * @param code
     * @param expireIn
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {

        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
}
