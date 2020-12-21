package com.elltor.greenlandsystem.biz.service;


import cn.hutool.json.JSONUtil;
import com.elltor.greenlandsystem.modules.biz.entity.vo.TreeMenu;
import com.elltor.greenlandsystem.modules.biz.service.ISourcesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SourcesServiceImplTest {

    @Autowired
    private ISourcesService sourcesService;

    @Test
    public void getTreeMenuTest(){
        List<TreeMenu> treeMenu = sourcesService.getTreeMenu();
        String parse = JSONUtil.toJsonStr(treeMenu);
        System.out.println(parse);
    }


}
