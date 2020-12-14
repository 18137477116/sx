package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.Dept;
import com.elltor.greenlandsystem.modules.biz.mapper.DeptMapper;
import com.elltor.greenlandsystem.modules.biz.service.IDeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    private final DeptMapper deptMapper;

}

