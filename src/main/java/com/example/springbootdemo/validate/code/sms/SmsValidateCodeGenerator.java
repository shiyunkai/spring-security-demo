package com.example.springbootdemo.validate.code.sms;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.validate.code.ValidateCode;
import com.example.springbootdemo.validate.code.ValidateCodeGenerator;
import com.example.springbootdemo.validate.code.image.ImageCode;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 15:15
 * @Description:
 */
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;


    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String smsCode = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsValidateCode(smsCode,securityProperties.getCode().getSms().getLength());
    }
}
