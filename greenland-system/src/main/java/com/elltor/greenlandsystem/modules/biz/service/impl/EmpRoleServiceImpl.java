package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.EmpRole;
import com.elltor.greenlandsystem.modules.biz.mapper.EmpRoleMapper;
import com.elltor.greenlandsystem.modules.biz.service.IEmpRoleService;
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
public class EmpRoleServiceImpl extends ServiceImpl<EmpRoleMapper, EmpRole> implements IEmpRoleService {

    private final EmpRoleMapper empRoleMapper;

}

