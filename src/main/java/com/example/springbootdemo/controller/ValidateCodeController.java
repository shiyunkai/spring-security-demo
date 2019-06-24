package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.validate.code.ValidateCodeGenerator;
import com.example.springbootdemo.validate.code.ValidateCodeProcessor;
import com.example.springbootdemo.validate.code.image.ImageCode;
import com.example.springbootdemo.validate.code.sms.SmsValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/29 10:20
 * @Description: 验证码ccontroller
 */

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_FOR_CODE_IMAGE";//放在session中的图形验证码的key

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // 依赖搜索，spring会找到所有ValidateCodeProcessor的具体的实现
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /* * @Auther: shiyunkai
     * @Description: 创建验证码，根据验证码类型不同，调用不同的接口实现
     * @Param: [request, response]
     * @Date:  21:31 2019/3/30
     * @return: void
     **/
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {

        validateCodeProcessors.get(type+"CodeProcessor").create(new ServletWebRequest(request,response));

    }



}
