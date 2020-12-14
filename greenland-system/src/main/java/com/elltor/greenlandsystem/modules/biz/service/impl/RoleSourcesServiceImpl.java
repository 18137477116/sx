package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.RoleSources;
import com.elltor.greenlandsystem.modules.biz.mapper.RoleSourcesMapper;
import com.elltor.greenlandsystem.modules.biz.service.IRoleSourcesService;
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
public class RoleSourcesServiceImpl extends ServiceImpl<RoleSourcesMapper, RoleSources> implements IRoleSourcesService {

    private final RoleSourcesMapper roleSourcesMapper;

}

