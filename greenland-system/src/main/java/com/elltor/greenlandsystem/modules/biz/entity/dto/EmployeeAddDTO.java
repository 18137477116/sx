package com.elltor.greenlandsystem.modules.biz.entity.dto;

import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import lombok.Data;

@Data
public class EmployeeAddDTO extends Employee {
    private Integer roleId;
}
