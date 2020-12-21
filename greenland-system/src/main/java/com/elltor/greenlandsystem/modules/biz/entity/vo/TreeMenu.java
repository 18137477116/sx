package com.elltor.greenlandsystem.modules.biz.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeMenu implements Serializable {
    /** 菜单id */
    private Integer id;
    /** 菜单名称 */
    private String name;
    /** 是否展开 */
    private Boolean open;
    /** 是否是父菜单 */
    private Boolean isParent;
    /** 子菜单 */
    private List<TreeMenu> children;
}
