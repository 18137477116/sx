package com.elltor.greenlandsystem.modules.biz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.elltor.greenlandsystem.modules.biz.service.IDeptService;
import com.elltor.greenlandsystem.modules.biz.entity.Dept;


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
@RequestMapping("/api/dept")
public class DeptController {
    private final IDeptService deptService;

}
