package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.dto.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 19:48
 * @Description:
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /* * @Auther: shiyunkai
     * @Description: 当需要身份认证时跳转到这里
     * @Param: [request, response]
     * @Date:  20:07 2019/3/28
     * @return: java.lang.String
     **/
    @RequestMapping("/authentication/require")
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 拿到用户跳转到此请求前的请求信息
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("---引发跳转的请求是:"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")) {//如果引发跳转的请求是以.hmtl结尾的
                log.info("-------引发跳转的请求是以.hmtl结尾的--------");
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
                return null;
            }
        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
