package com.elltor.greenlandsystem.modules.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elltor.greenlandsystem.modules.biz.entity.vo.TreeMenu;
import lombok.RequiredArgsConstructor;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import com.elltor.greenlandsystem.modules.biz.mapper.SourcesMapper;
import com.elltor.greenlandsystem.modules.biz.service.ISourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<TreeMenu> getTreeMenu() {
        LambdaQueryWrapper<Sources> sourcesWrapper = new LambdaQueryWrapper<>();
        sourcesWrapper.isNull(Sources::getUrl);
        sourcesWrapper.isNull(Sources::getPid);
        List<Sources> sourcesList = sourcesMapper.selectList(sourcesWrapper);
        List<TreeMenu> treeMenuList = new ArrayList<>();

        for(Sources s : sourcesList){
            TreeMenu tm = new TreeMenu();
            tm.setId(s.getId());
            tm.setIsParent(true);
            tm.setOpen(false);
            tm.setName(s.getName());
            tm.setChildren(getChildMenu(s.getId()));
            treeMenuList.add(tm);
        }
        return treeMenuList;
    }

    public List<TreeMenu> getChildMenu(Integer pid){
        LambdaQueryWrapper<Sources> sourcesWrapper = new LambdaQueryWrapper<>();
        sourcesWrapper.eq(Sources::getPid,pid);
        List<Sources> sourcesList = sourcesMapper.selectList(sourcesWrapper);
        List<TreeMenu> treeMenuList = new ArrayList<>();
        for(Sources s : sourcesList){
            TreeMenu tm = new TreeMenu();
            tm.setId(s.getId());
            tm.setIsParent(true);
            tm.setOpen(false);
            tm.setName(s.getName());
            sourcesWrapper.clear();
            sourcesWrapper.eq(Sources::getPid,s.getId());
            int count = sourcesMapper.selectCount(sourcesWrapper);
            if(count>0){
                tm.setChildren(getChildMenu(s.getId()));
            }else{
                tm.setIsParent(false);
            }
            treeMenuList.add(tm);
        }
        return treeMenuList;
    }
}

