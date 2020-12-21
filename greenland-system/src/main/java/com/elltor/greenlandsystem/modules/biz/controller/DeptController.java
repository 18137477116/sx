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

import com.elltor.greenlandsystem.modules.biz.service.IDeptService;
import com.elltor.greenlandsystem.modules.biz.entity.Dept;

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
@Api(tags = "部门模块")
@RequestMapping("/api/dept")
public class DeptController {
    private final IDeptService deptService;

    @ApiOperation("查询部门列表数据")
    @GetMapping("/all")
    public Result<List<Dept>> deptList(){
        return Result.success(deptService.list());
    }

    @ApiOperation("更新部门")
    @PostMapping("/update")
    public Result update(Dept dept){
        if(dept==null || dept.getDeptno()==null){
            throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        }
        LambdaQueryWrapper<Dept> deptWrapper = new LambdaQueryWrapper<>();
        deptWrapper.eq(Dept::getDeptno,dept.getDeptno());
        deptService.update(dept,deptWrapper);
        return Result.success();
    }

    @ApiOperation("添加部门")
    @PostMapping("/add")
    public Result add(Dept dept){
        if(dept==null){
            throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        }
        deptService.save(dept);
        return Result.success();
    }

    @ApiOperation("删除部门")
    @PostMapping("/delete")
    public Result delete(Integer id){
        if(id==null) throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Dept> deptWrapper = new LambdaQueryWrapper<>();
        deptWrapper.eq(Dept::getDeptno,id);
        deptService.remove(deptWrapper);
        return Result.success();
    }
}
