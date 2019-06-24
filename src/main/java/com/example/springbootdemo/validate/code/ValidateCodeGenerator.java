package com.example.springbootdemo.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 15:12
 * @Description: 验证码生成器接口
 */
public interface ValidateCodeGenerator {

    // 生成验证码
    ValidateCode generate(ServletWebRequest request);

}
