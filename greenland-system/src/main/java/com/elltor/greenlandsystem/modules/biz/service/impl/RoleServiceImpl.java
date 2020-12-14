package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.mapper.RoleMapper;
import com.elltor.greenlandsystem.modules.biz.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMapper roleMapper;

}

