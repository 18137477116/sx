package com.elltor.greenlandsystem.modules.biz.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elltor.greenlandcommon.base.PageVO;
import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.response.Result;
import com.elltor.greenlandsystem.modules.biz.entity.EmpRole;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeAddDTO;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.service.IEmpRoleService;
import com.elltor.greenlandsystem.modules.biz.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;

import javax.validation.constraints.NotNull;


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
@Api(tags = "员工模块")
@RequestMapping("/api/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final IEmpRoleService empRoleService;
    private final PasswordEncoder encoder;

    @ApiOperation("查询用户列表数据")
    @GetMapping("/list")
    public Result<IPage<EmployeeDTO>> user(String key, PageVO pageVO){
        return Result.success(employeeService.employeeList(key,pageVO));
    }

    @ApiOperation("更新用户数据")
    @PostMapping("/update")
    public Result update(EmployeeDTO employeeDTO){
        if(ObjectUtil.isEmpty(employeeDTO)){
            return Result.error("传递对象为空");
        }
        employeeService.update(employeeDTO);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public Result delete(Integer id){
        if(id==null) throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Employee> employeeWrapper = new LambdaQueryWrapper<>();
        employeeWrapper.eq(Employee::getEid,id);
        employeeService.remove(employeeWrapper);
        return Result.success();
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result add(EmployeeAddDTO employeeAddDTO){
        if(employeeAddDTO == null) throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        employeeAddDTO.setPassword(encoder.encode(employeeAddDTO.getPassword()));
        employeeService.save(employeeAddDTO);
        EmpRole er = new EmpRole();
        LambdaQueryWrapper<Employee> employeeWrapper = new LambdaQueryWrapper<>();
        employeeWrapper.eq(Employee::getUsername,employeeAddDTO.getUsername())
                .eq(Employee::getPnum,employeeAddDTO.getPnum());
        Employee goal = employeeService.getOne(employeeWrapper);
        er.setEmpFk(goal.getEid());
        er.setRoleFk(employeeAddDTO.getRoleId());
        empRoleService.save(er);
        return Result.success();
    }

}
