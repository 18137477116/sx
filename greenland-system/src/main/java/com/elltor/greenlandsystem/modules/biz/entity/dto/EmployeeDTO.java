package com.elltor.greenlandsystem.modules.biz.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeeDTO implements Serializable {

    private Integer eid;

    private String ename;

    private String esex;

    private Integer eage;

    private String telephone;

    private LocalDate hiredate;

    private String pnum;

    private String username;

    @JsonIgnore
    private String password;

    private String remark;

    private Integer dfk;

    private String pic;
}
