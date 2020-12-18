package com.elltor.greenlandsystem.modules.security.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security-config")
public class SecurityProperties {
    /**
     * 账号单用户 登录
     */
    private boolean singleLogin = false;

    /**
     * 用户登录信息缓存
     */
    private boolean cacheEnable;

    /** Request Headers ： Authorization */
    private String header;

    /** 令牌前缀，最后留个空格 Bearer */
    private String tokenStartWith;

    /** 必须使用最少88位的Base64对该令牌进行编码 */
    private String base64Secret;

    /** 令牌过期时间 此处单位/毫秒 */
    private Long tokenValidityInSeconds;

    /** 在线用户 key，根据 key 查询 redis 中在线用户的数据 */
    private String onlineKey;

    /** token 续期检查 */
    private Long detect;

    /** 续期时间 */
    private Long renew;

    /** 重写get方法，加入空格，防止空格造成影响 */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}