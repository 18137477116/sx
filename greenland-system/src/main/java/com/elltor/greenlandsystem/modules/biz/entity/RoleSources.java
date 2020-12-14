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
@ApiModel(value="RoleSources对象", description="")
public class RoleSources extends Model<RoleSources> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键字段")
    @TableId(value = "rsid", type = IdType.AUTO)
    private Integer rsid;

    @ApiModelProperty(value = "预留字段无实际意义")
    private String rsdis;

    @ApiModelProperty(value = "权限表的关联字段")
    private Integer resourcesFk;

    @ApiModelProperty(value = "角色表的关联字段")
    private Integer roleFk;


    @Override
    protected Serializable pkVal() {
        return this.rsid;
    }

}
