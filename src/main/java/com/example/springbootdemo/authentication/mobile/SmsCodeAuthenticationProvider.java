package com.example.springbootdemo.authentication.mobile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Auther: shiyunkai
 * @Date: 2019/04/03 11:50
 * @Description: 短信验证码认证提供者
 */
@Data
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public SmsCodeAuthenticationProvider() {
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken authenticationToken  = (SmsCodeAuthenticationToken)authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        if(user == null){
            log.info("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
       authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
