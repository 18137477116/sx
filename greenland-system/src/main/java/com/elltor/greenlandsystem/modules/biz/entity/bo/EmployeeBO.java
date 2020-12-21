package com.elltor.greenlandsystem.modules.biz.entity.bo;

import com.elltor.greenlandsystem.modules.biz.entity.Dept;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class EmployeeBO extends Employee {

    private Dept dept;

    private Set<Role> roles;

    private Set<Sources> sources;
}
