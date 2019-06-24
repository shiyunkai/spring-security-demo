package com.example.springbootdemo.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 18:44
 * @Description: 自定义spring security登录的逻辑, 用户名和密码可以从数据库中获取
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 在点击spring security登录的按钮时会调用此方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("----------loadUserByUsername方法被调用 用户进行登录------------");
        UserDetails user = new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
