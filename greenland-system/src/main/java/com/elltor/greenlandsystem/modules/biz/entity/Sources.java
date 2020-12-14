package com.elltor.greenlandsystem.modules.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiuQichun
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sources对象", description="")
public class Sources extends Model<Sources> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "资源路径")
    private String url;

    @ApiModelProperty(value = "资源备注")
    private String remark;

    @ApiModelProperty(value = "父菜单id")
    private Integer pid;

    @ApiModelProperty(value = "图标")
    private String icon;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
