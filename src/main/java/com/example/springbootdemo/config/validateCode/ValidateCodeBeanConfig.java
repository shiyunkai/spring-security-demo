package com.example.springbootdemo.config.validateCode;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.validate.code.ValidateCodeGenerator;
import com.example.springbootdemo.validate.code.image.ImageValidateCodeGenerator;
import com.example.springbootdemo.validate.code.sms.SmsValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 15:20
 * @Description: 验证码配置
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean(name = "imageValidateCodeGenerator")
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageValidateCodeGenerator imageCodeGenerator = new ImageValidateCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean(name = "smsValidateCodeGenerator")
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(){
        SmsValidateCodeGenerator smsCodeGenerator = new SmsValidateCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    /* * @Auther: shiyunkai
     * @Description: 在其它应用中可以进行验证码生成器的自定义,如果其它地方进行了自定义，根据@ConditionalOnMissingBean(name = "imageCodeGenerator"),默认配置的就不会生成
     * @Param:
     * @Date:  15:30 2019/3/30
     * @return:
     * 使用如下:
     *
     *定义一个类:
     * @Component("imageCodeGenerator")
       public class MyImageCodeCodeGenerator implements ValidateCodeGenerator {
            @Override
            public ImageCode generate(HttpServletRequest request) {

                //此处返回自定义的验证码生成器
                return null;
            }
        }

     *
     **/
}
