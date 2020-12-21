package com.elltor.greenlandsystem.modules.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elltor.greenlandcommon.base.PageVO;
import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.utils.PageUtil;
import com.elltor.greenlandcommon.utils.StringUtils;
import com.elltor.greenlandsystem.modules.biz.entity.bo.EmployeeBO;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeAddDTO;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.entity.*;
import com.elltor.greenlandsystem.modules.biz.mapper.*;
import com.elltor.greenlandsystem.modules.biz.service.mapstruct.EmployeeMapStruct;
import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
*
* @author LiuQichun
* @since 2020-12-14
*/
@Service("employeeService")
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmpRoleMapper empRoleMapper;
    private final RoleMapper roleMapper;
    private final SourcesMapper sourcesMapper;
    private final RoleSourcesMapper roleSourcesMapper;
    private final EmployeeMapStruct employeeMapStruct;
    private final PasswordEncoder passwordEncoder;

    public EmployeeBO findEmployeeByUsername(String username){
        //查询用户
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>(Employee.class);
        wrapper.eq(Employee::getUsername,username);
        Employee employee = employeeMapper.selectOne(wrapper);
        if(employee==null) throw new BadRequestException(ResultEnum.NO_USER);
        EmployeeBO employeeBO = new EmployeeBO();
        BeanUtils.copyProperties(employee,employeeBO);

        //查询用户拥有角色
        //用户角色关系表查用户
        LambdaQueryWrapper<EmpRole> empRoleWrapper = new LambdaQueryWrapper<>(EmpRole.class);
        empRoleWrapper.eq(EmpRole::getEmpFk,employeeBO.getEid());
        List<EmpRole> empRoleList = empRoleMapper.selectList(empRoleWrapper);

        //查角色
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>(Role.class);
        Set<Integer> roleFkSet = empRoleList.stream().map(EmpRole::getRoleFk).collect(Collectors.toSet());
        roleWrapper.in(Role::getRoleid,roleFkSet);
        List<Role> roleList = roleMapper.selectList(roleWrapper);
        employeeBO.setRoles(new HashSet<>(roleList));

        //查询用户(其拥有角色)拥有的资源
        //查角色资源关系表
        LambdaQueryWrapper<RoleSources> roleSourcesWrapper = new LambdaQueryWrapper<>(RoleSources.class);
        Set<Integer> roleIdSet = roleList.stream().map(Role::getRoleid).collect(Collectors.toSet());
        if(roleIdSet.size()>0){
            roleSourcesWrapper.in(RoleSources::getRoleFk,roleIdSet);
            List<RoleSources> roleSourcesList = roleSourcesMapper.selectList(roleSourcesWrapper);
            //查资源表
            Set<Integer> sourcesIdSet = roleSourcesList.stream().map(RoleSources::getResourcesFk).collect(Collectors.toSet());
            LambdaQueryWrapper<Sources> sourcesWrapper = new LambdaQueryWrapper<>(Sources.class);
            if(sourcesIdSet.size()>0){
                sourcesWrapper.in(Sources::getId,sourcesIdSet);
                List<Sources> sourcesList = sourcesMapper.selectList(sourcesWrapper);
                employeeBO.setSources(new HashSet<>(sourcesList));
            }
        }
        return employeeBO;
    }

    @Override
    public IPage<EmployeeDTO> employeeList(String key, PageVO pageVO) {
        LambdaQueryWrapper<Employee> employeeWrapper = new LambdaQueryWrapper<>();
        IPage<EmployeeDTO> returnPage = pageVO.buildPage();
        if(!StringUtils.isNullOrUndefined(key)){
            employeeWrapper.like(Employee::getEname,key);
        }
        IPage<Employee> page = employeeMapper.selectPage(pageVO.buildPage(),employeeWrapper);
        // 为什么我们有个page还要创建一个page对象, 因为查询出的是IPage<Employee>,而需要的是IPage<EmployeeDTO>
        BeanUtils.copyProperties(page,returnPage);
        // List<Employee> -> List<EmployeeDTO>
        List<EmployeeDTO> employeeDTOList = employeeMapStruct.toDTO(page.getRecords());
        returnPage.setRecords(employeeDTOList);
        return returnPage;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setPassword(null);
        employeeMapper.updateById(employee);
    }

    @Override
    public void delete(Integer id) {
        LambdaQueryWrapper<Employee> employeeWrapper = new LambdaQueryWrapper<>();
        employeeWrapper.eq(Employee::getEid,id);
        employeeMapper.delete(employeeWrapper);
    }

}

