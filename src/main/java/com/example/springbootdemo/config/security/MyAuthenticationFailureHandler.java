package com.example.springbootdemo.config.security;

import com.example.springbootdemo.config.constant.LoginResponseType;
import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.dto.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/29 08:52
 * @Description: 自定义登录失败的处理器
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("--------登录失败!---------");

        // 登录失败后可以根据配置进行跳转或者返回json错误信息
        if(LoginResponseType.JSON.toString().equals(securityProperties.getBrowser().getLoginType())){
            log.info("-----返回json------");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else{
            log.info("------跳转到错误页面-----");
            redirectStrategy.sendRedirect(request,response,"/errors.html");
        }
    }
}
