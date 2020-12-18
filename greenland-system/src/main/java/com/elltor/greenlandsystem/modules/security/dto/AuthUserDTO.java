package com.elltor.greenlandsystem.modules.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDTO {
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
}
