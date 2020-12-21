package com.elltor.greenlandsystem.modules.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.response.Result;
import com.elltor.greenlandsystem.modules.biz.entity.vo.TreeMenu;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.elltor.greenlandsystem.modules.biz.service.ISourcesService;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
* @author LiuQichun
* @since 2020-12-14
*/
@RequiredArgsConstructor
@RestController
@Slf4j
@Api(tags = "资源模块")
@RequestMapping("/api/sources")
public class SourcesController {
    private final ISourcesService sourcesService;

    public Result<List<Sources>> allSources(){
        return Result.success(sourcesService.list());
    }

    @ApiOperation("添加资源接口")
    @PostMapping("/add")
    public Result addSources(@NotNull Sources sources){
        sourcesService.save(sources);
        return Result.success();
    }

    @ApiOperation("更新资源接口")
    @PostMapping("/update")
    public Result updateSources(@NotNull Sources sources){
        if(sources.getId()==null) throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Sources> sourcesWrapper = new LambdaQueryWrapper<>();
        sourcesWrapper.eq(Sources::getId,sources.getId());
        sourcesService.update(sources,sourcesWrapper);
        return Result.success();
    }

    @ApiOperation("删除资源")
    @PostMapping("/delete")
    public Result delete(Integer id){
        if(id == null) throw new BadRequestException(ResultEnum.PARAMS_NOT_NULL);
        LambdaQueryWrapper<Sources> sourceWrapper = new LambdaQueryWrapper<>();
        sourceWrapper.eq(Sources::getId,id);
        sourcesService.remove(sourceWrapper);
        return Result.success();
    }

    @ApiOperation("获取所有树形菜单")
    @GetMapping("/tree-menu")
    public Result<List<TreeMenu>> getMenu(){
        return Result.success(sourcesService.getTreeMenu());
    }

    @ApiOperation("获取顶级菜单")
    @GetMapping("/parent-menu")
    public Result<List<Sources>> getParentMenu(){
        LambdaQueryWrapper<Sources> sourceWrapper = new LambdaQueryWrapper<>();
        sourceWrapper.isNull(Sources::getUrl);
        sourceWrapper.isNotNull(Sources::getPid);
        return Result.success(sourcesService.getBaseMapper().selectList(sourceWrapper));
    }

}
