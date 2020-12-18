package com.elltor.greenlandsystem.modules.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author LiuQichun
 * @since 2020-12-14
 */
//@RequiredArgsConstructor
@Controller
//@Slf4j
//@Api(tags = "模块")
public class SystemController {

    @RequestMapping("/login")
    public String  login(){
        return "login";
    }

    @RequestMapping("/index")
    public String  index(){
        return "index";
    }

}
