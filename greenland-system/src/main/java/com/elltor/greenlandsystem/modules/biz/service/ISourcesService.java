package com.elltor.greenlandsystem.modules.biz.service;

import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elltor.greenlandsystem.modules.biz.entity.vo.TreeMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuQichun
 * @since 2020-12-14
 */
public interface ISourcesService extends IService<Sources> {
    List<TreeMenu> getTreeMenu();
}
