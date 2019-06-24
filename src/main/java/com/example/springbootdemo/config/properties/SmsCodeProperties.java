package com.example.springbootdemo.config.properties;

import lombok.Data;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/29 11:17
 * @Description: 短信验证码配置类
 */
@Data
public class SmsCodeProperties {

    private int length = 4;

    private int expireIn = 60;//默认在创建后的就几秒过期

}
