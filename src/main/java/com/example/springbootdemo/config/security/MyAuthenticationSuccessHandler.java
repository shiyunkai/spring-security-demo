package com.example.springbootdemo.config.security;

import com.example.springbootdemo.config.constant.LoginResponseType;
import com.example.springbootdemo.config.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 21:05
 * @Description: 自定义登录成功处理器
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // 可以将对象转换为Json字符串
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    //登录成功后会调用的方法
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-------登录成功!---------");
        //将登录成功后的认证信息返回给前端
        // 登录成功后可以根据配置进行跳转或者返回json信息
        if(LoginResponseType.JSON.toString().equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
             //跳转到登录成功之前的url
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            String redirectUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,redirectUrl);

            // 调用父类的方法
            //super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
