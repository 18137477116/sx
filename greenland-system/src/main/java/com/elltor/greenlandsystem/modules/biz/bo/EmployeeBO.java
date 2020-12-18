package com.elltor.greenlandsystem.modules.biz.bo;

import com.elltor.greenlandsystem.modules.biz.entity.Dept;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class EmployeeBO extends Employee {

    private Dept dept;

    private Set<Role> roles;

    private Set<Sources> sources;
}
