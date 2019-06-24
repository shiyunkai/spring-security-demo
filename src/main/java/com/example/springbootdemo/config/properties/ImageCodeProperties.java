package com.example.springbootdemo.config.properties;

import lombok.Data;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/29 11:17
 * @Description: 验证码配置类
 */
@Data
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;
    private String url;//请求图形验证码的url，可以有多个并且可以配置
}
