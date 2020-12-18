package com.elltor.greenlandsystem.modules.security.config.token;

import com.elltor.greenlandsystem.modules.security.config.bean.SecurityProperties;
import com.elltor.greenlandsystem.modules.security.service.OnlineUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * description:Token配置类
 *
 * @author RenShiWei
 * Date: 2020/11/18 9:42
 **/
@RequiredArgsConstructor
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;


    /**
     * description: 注入token过滤器
     *
     * @author RenShiWei
     * Date: 2020/11/18 9:56
     */
    @Override
    public void configure(HttpSecurity http) {
        TokenFilter customFilter = new TokenFilter(tokenProvider, properties, onlineUserService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
