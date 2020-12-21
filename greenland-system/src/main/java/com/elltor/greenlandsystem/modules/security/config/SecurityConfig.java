package com.elltor.greenlandsystem.modules.security.config;

import com.elltor.greenlandsystem.modules.security.config.bean.SecurityProperties;
import com.elltor.greenlandsystem.modules.security.config.handler.JwtAccessDeniedHandler;
import com.elltor.greenlandsystem.modules.security.config.handler.JwtAuthenticationEntryPoint;
import com.elltor.greenlandsystem.modules.security.config.handler.MyAuthencationFailureHandler;
import com.elltor.greenlandsystem.modules.security.config.handler.MyAuthenticationSuccessHandler;
import com.elltor.greenlandsystem.modules.security.config.token.TokenConfigurer;
import com.elltor.greenlandsystem.modules.security.config.token.TokenProvider;
import com.elltor.greenlandsystem.modules.security.service.OnlineUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.handler.DefaultWebFilterChain;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** 默认过滤 */
    private final CorsFilter corsFilter;
    /** security和jwt全局属性 */
    private final SecurityProperties properties;
    /** token提供对象 */
    private final TokenProvider tokenProvider;
    /** 认证失败处理类 */
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    /** 权限不足处理类 */
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    /** 在线用户服务 */
    private final OnlineUserService onlineUserService;

    private final MyAuthenticationSuccessHandler successHandler;

    private final MyAuthencationFailureHandler failureHandler;

    private final SecurityGeneralFilter securityGeneralFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/auth/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            // 防止 iframe 造成跨域
            .and()
            .headers()
            .frameOptions().disable()//如果不设置jsp或iframe禁止请求
            // 以下配置认证策略
            .and()
            .authorizeRequests()
            .antMatchers("/login","/404.html","/500.html").permitAll()
            //对静态资源放行
            .antMatchers(
                HttpMethod.GET,
                "/css/**",
                "/front/**",
                "/images/**",
                "/js/**",
                "/img/**",
                "/**/*.js",
                "/**/*.css",
                "/**/*.png",
                "/**/*.jpg",
                "/**/*.jpeg",
                "/**/*.front"
            ).permitAll()
            // swagger 文档
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/*/api-docs").permitAll()
            // 文件
            .antMatchers("/avatar/**").permitAll()
            .antMatchers("/file/**").permitAll()
            // 阿里巴巴 druid
            .antMatchers("/druid/**").permitAll()
            .anyRequest()
            .authenticated();
            httpSecurity.logout().logoutUrl("/logout");
            httpSecurity.addFilterBefore(securityGeneralFilter, UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * description:去除 ROLE_ 前缀
     */
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    /**
     * description: 指定密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * description: token配置
     *
     * @author RenShiWei
     * Date: 2020/11/18 9:45
     */
    private TokenConfigurer tokenSecurityConfigurerAdapter() {
        return new TokenConfigurer(tokenProvider, properties, onlineUserService);
    }

}
