package com.elltor.greenlandsystem.modules.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.response.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.elltor.greenlandsystem.modules.biz.service.IRoleService;
import com.elltor.greenlandsystem.modules.biz.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.List;


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
@Api(tags = "角色模块")
@RequestMapping("/api/role")
public class RoleController {
    private final IRoleService roleService;

    @ApiOperation("获取所有的角色")
    @GetMapping("/all")
    public Result<List<Role>> getAllRole(){
        return Result.success(roleService.list());
    }

    @ApiOperation("添加角色")
    @PostMapping("/add")
    public Result addRole(Role role){
        roleService.save(role);
        return Result.success();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public Result updateRole(@NotNull Role role){
        if(role.getRoleid()==null)throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getRoleid,role.getRoleid());
        roleService.update(role,roleWrapper);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public  Result delRole(Integer id){
        if(id == null)throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getRoleid,id);
        roleService.remove(roleWrapper);
        return Result.success();
    }

}
