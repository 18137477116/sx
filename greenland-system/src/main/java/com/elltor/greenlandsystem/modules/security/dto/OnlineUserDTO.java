package com.elltor.greenlandsystem.modules.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUserDTO {

    private Integer eid;

    private String ename;

    private String esex;

    private Integer eage;

    private String telephone;

    private LocalDate hiredate;

    private String pnum;

    private String username;

    private String remark;

    private Integer dfk;

    private String pic;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private Date loginTime;

}
