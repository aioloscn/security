package com.aiolos.security.core.validate.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiolos
 * @date 2019-05-06 23:50
 */
@Slf4j
@RestController
public class ValidateCodeController {

    public final static String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        // 将验证码存入到session
        ImageCode code = new ImageCode();
        code.setCode(imageCode.getCode());
        code.setExpireTime(imageCode.getExpireTime());
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, code);
        // 将图片写到响应的输出流中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}
