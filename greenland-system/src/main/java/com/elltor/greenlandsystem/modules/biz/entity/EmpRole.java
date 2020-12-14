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
@ApiModel(value="EmpRole对象", description="")
public class EmpRole extends Model<EmpRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "erid", type = IdType.AUTO)
    private Integer erid;

    @ApiModelProperty(value = "角色表的关联外键")
    private Integer roleFk;

    @ApiModelProperty(value = "员工表的关联外键")
    private Integer empFk;

    @ApiModelProperty(value = "预留字段没有实际意义")
    private String erdis;


    @Override
    protected Serializable pkVal() {
        return this.erid;
    }

}
