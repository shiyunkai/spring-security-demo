package com.example.springbootdemo.validate.code;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.controller.ValidateCodeController;
import com.example.springbootdemo.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 13:13
 * @Description: 验证码过滤器 OncePerRequestFilter:只执行一次的过滤器  InitializingBean:在其它参数都组装完毕后再初始化urls
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    //InitializingBean中的方法,在所有的bean初始化之后才会调用
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 获取配置文件中的url配置
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
        for(String configUrl : configUrls){
            urls.add(configUrl);
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        // 采用循环的方式比较用户请求的url是否和配置文件中可以图片验证码的url相同，相同才会过滤
        for(String url:urls){
            if(antPathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }

        if(action){
            try {
                // 校验验证码
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                // 验证码校验失败,用认证失败处理器进行处理
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        // 验证码校验通过，继续执行后面的过滤器
        filterChain.doFilter(request,response);
    }

    /* * @Auther: shiyunkai
     * @Description: 验证码验证方法
     * @Param:
     * @Date:  13:30 2019/3/30
     * @return: void
     **/
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        // 1.拿到session中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);

        // 2.拿到用户提交的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        // 3.校验请求中拿到的验证码
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已经过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        // 4.上面没有抛出异常，说明验证成功，同样从session中移除验证码
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);

    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
