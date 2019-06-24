package com.example.springbootdemo.config.properties;

import lombok.Data;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/29 11:17
 * @Description:
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
