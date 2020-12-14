package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.mapper.EmployeeMapper;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* <p>
*  服务实现类
* </p>
*
* @author LiuQichun
* @since 2020-12-14
*/
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    private final EmployeeMapper employeeMapper;

}

