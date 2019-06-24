package com.example.springbootdemo.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Auther: shiyunkai
 * @Date: 2019/04/03 11:38
 * @Description: 短信验证码认证token
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 510L;
    private final Object principal;

    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Object principal,  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
    }
}