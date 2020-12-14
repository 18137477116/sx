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
@ApiModel(value="Role对象", description="")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    @ApiModelProperty(value = "角色名称")
    private String rolename;

    @ApiModelProperty(value = "角色描述")
    private String roledis;

    @ApiModelProperty(value = "是否启用(0 禁用 1启用)")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.roleid;
    }

}
