package com.elltor.greenlandsystem.modules.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
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
@ApiModel(value="Employee对象", description="")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号（主键）")
    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;

    @ApiModelProperty(value = "姓名")
    private String ename;

    @ApiModelProperty(value = "性别")
    private String esex;

    @ApiModelProperty(value = "年龄")
    private Integer eage;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "入职日期")
    private LocalDate hiredate;

    @ApiModelProperty(value = "身份证号码")
    private String pnum;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "部门表的外键")
    private Integer dfk;

    @ApiModelProperty(value = "头像")
    private String pic;


    @Override
    protected Serializable pkVal() {
        return this.eid;
    }

}
