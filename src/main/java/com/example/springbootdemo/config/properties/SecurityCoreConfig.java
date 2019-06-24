package com.example.springbootdemo.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 20:34
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
