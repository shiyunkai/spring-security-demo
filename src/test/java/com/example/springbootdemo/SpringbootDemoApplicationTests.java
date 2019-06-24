package com.example.springbootdemo;

import com.example.springbootdemo.config.constant.LoginResponseType;
import com.example.springbootdemo.config.constant.ValidateCodeType;
import com.example.springbootdemo.config.properties.SecurityProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)//如何来运行来运行测试用例
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private SecurityProperties securityProperties;

    @Test
    public void LoginResponseTypeTest(){
        String json = LoginResponseType.JSON.toString();
        String dat = securityProperties.getBrowser().getLoginType();
        System.out.println(LoginResponseType.JSON);
        System.out.println(securityProperties.getBrowser().getLoginType());
        if(LoginResponseType.JSON.toString().equals(securityProperties.getBrowser().getLoginType())){
            System.out.println("用户要获得json数据");
        }else{
            System.out.println("用户要进行跳转");
        }
    }

    @Test
    public void ValidateCodeTypeTest(){
        System.out.println("--------------->"+ValidateCodeType.IMAGE.getParamNameOnValidate());
        System.out.println("--------------->"+ValidateCodeType.SMS.getParamNameOnValidate());
    }

}
