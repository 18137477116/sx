package com.elltor.greenlandsystem;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * description:启动类
 *
 * <p>
 * '@Api(hidden = true)'     //在swagger中隐藏该类资源
 * '@EnableTransactionManagement' //开启事务支持，之后可以使用@Transactiona
 **/
@SpringBootApplication
@EnableAsync
@Api(hidden = true)
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan({"com.elltor.greenlandsystem.modules.biz.mapper"})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

}
