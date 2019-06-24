package com.example.springbootdemo.validate.code.sms;

import com.example.springbootdemo.validate.code.ValidateCode;

import java.time.LocalDateTime;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 17:00
 * @Description: 短信验证码
 */
public class SmsValidateCode extends ValidateCode {
    public SmsValidateCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsValidateCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
