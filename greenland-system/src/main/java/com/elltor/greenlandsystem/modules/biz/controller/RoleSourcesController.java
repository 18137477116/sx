package com.elltor.greenlandsystem.modules.biz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.elltor.greenlandsystem.modules.biz.service.IRoleSourcesService;
import com.elltor.greenlandsystem.modules.biz.entity.RoleSources;


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
@RequestMapping("/api/roleSources")
public class RoleSourcesController {
    private final IRoleSourcesService roleSourcesService;

}
