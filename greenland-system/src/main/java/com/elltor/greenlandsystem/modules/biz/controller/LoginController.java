package com.elltor.greenlandsystem.modules.biz.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author LiuQichun
 * @since 2020-12-14
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@Api(tags = "模块")
public class LoginController {
    @RequestMapping("/login")
    public String  login(){
        return "login";
    }
}
