package com.example.springbootdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 20:31
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    // 浏览器访问配置
    private BrowserProperties browser = new BrowserProperties();

    // 验证码配置
    private ValidateCodeProperties code = new ValidateCodeProperties();

}
