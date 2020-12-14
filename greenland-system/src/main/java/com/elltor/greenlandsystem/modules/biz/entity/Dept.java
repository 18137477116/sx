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
@ApiModel(value="Dept对象", description="")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门编号")
    @TableId(value = "deptno", type = IdType.AUTO)
    private Integer deptno;

    @ApiModelProperty(value = "部门名称")
    private String dname;

    @ApiModelProperty(value = "部门位置")
    private String local;


    @Override
    protected Serializable pkVal() {
        return this.deptno;
    }

}
