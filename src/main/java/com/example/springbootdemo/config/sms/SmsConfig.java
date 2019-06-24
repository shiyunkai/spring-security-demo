package com.example.springbootdemo.config.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/* * @Auther: shiyunkai
 * @Description: 发送短信的配置，阿里大鱼短信发送平台
 * @Param:
 * @Date:  21:49 2019/3/30
 * @return:
 **/
@SpringBootConfiguration
public class SmsConfig {

    @Value("${ali.msm.accessKeyId}")
    String accessKeyId;
    @Value("${ali.msm.accessKeySecret}")
    String accessKeySecret;

    @Bean
    public IAcsClient getAcsClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        return new DefaultAcsClient(profile);
    }


}
