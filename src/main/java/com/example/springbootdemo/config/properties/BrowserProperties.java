package com.example.springbootdemo.config.properties;

import lombok.Data;
import sun.security.util.SecurityConstants;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 20:26
 * @Description: 基于浏览器的请求参数配置
 */
@Data
public class BrowserProperties {
    private String signUpUrl = "/imooc-signUp.html";

    private String loginPage = "/login";

    private String loginType = "JSON";

    private int rememberMeSeconds = 3600;//记住我的时间
}
