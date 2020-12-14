package com.elltor.greenlandsystem.modules.biz.service.impl;

import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import com.elltor.greenlandsystem.modules.biz.mapper.SourcesMapper;
import com.elltor.greenlandsystem.modules.biz.service.ISourcesService;
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
public class SourcesServiceImpl extends ServiceImpl<SourcesMapper, Sources> implements ISourcesService {

    private final SourcesMapper sourcesMapper;

}

