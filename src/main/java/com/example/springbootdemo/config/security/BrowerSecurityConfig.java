package com.example.springbootdemo.config.security;


import com.example.springbootdemo.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 19:26
 * @Description:
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // remember me功能
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);//设置在开启项目的时候创建记住我功能需要的数据表persistent_logins,只能执行一次，否则会报Table 'persistent_logins' already exists
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 创建验证码过滤器的实例,并设置过滤器的相应属性
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).//在UsernamePasswordAuthenticationFilter过滤器前面添加自定义的过滤器
                formLogin()//设置为基于表单的登录验证
                .loginPage("/authentication/require")//登录请求的url,spring security默认所有的请求都会被拦截，跳转到这个url
                //.loginPage("/mylogin.html")
                //.loginProcessingUrl(securityProperties.getBrowser().getLoginPage())//登录跳转的url
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)//设置登录成功处理器
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .rememberMe()//记住我配置
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(myUserDetailsService)
                .and()
                .authorizeRequests()//验证request请求
                .antMatchers("/authentication/require",
                        "/mylogin.html",
                        "/index.html",
                        "/errors.html",
                        "/myloginsms.html",
                        "/code/*"
                ).permitAll()//设置些请求不需要认证就可以访问
                //.antMatchers("/mylogin.html").permitAll()
                .anyRequest()
                .authenticated()//所有请求都要通过认证
                .and()
                .csrf().disable();//自定义登录页面时要关闭csrf不然不会进行登录操作

    }
}
