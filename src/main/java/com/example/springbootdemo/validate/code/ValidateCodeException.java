package com.example.springbootdemo.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 13:22
 * @Description: 验证码异常类
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
