package com.elltor.greenlandsystem.modules.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author LiuQichun
 * @since 2020-12-14
 */
@RequiredArgsConstructor
@Controller
@Api(tags = "系统模块, 服务spring security")
@Slf4j
public class SystemController {

    @ApiOperation("登录页")
    @GetMapping("/login")
    public String  login(){
        return "login";
    }

    @ApiOperation("首页")
    @GetMapping("/index")
    public String  index(){
        return "index";
    }
}
