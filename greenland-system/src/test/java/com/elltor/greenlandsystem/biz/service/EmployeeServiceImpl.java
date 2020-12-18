package com.elltor.greenlandsystem.biz.service;


import com.elltor.greenlandcommon.config.bean.RsaProperties;
import com.elltor.greenlandcommon.utils.RsaUtils;
import com.elltor.greenlandsystem.modules.biz.bo.EmployeeBO;
import com.elltor.greenlandsystem.modules.biz.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.elltor.greenlandsystem.modules.security.dto.SecurityUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImpl {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RsaProperties rsaProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    /*私钥解密*/
    @Test
    public void findByUsernameTest(){
        EmployeeBO aUser = employeeService.findEmployeeByUsername("ssm");

        System.out.println("username:" + aUser.getUsername());
        System.out.println("pass: "+aUser.getPassword());

        System.out.println("--------------------");

        SecurityUserDTO securityUserDTO = new SecurityUserDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(aUser,employeeDTO);
//        securityUserDTO.setUser(employeeDTO);
//        securityUserDTO.setRoles(aUser.getRoles().stream().map(Role::getRolename).collect(Collectors.toSet()));

//        System.out.println("secur user :"+securityUserDTO.getUser());
//        System.out.println("secur username :"+securityUserDTO.getUsername());
//        System.out.println("secur pass :"+securityUserDTO.getPassword());
//        System.out.println("secur roles:"+securityUserDTO.getRoles());
//        boolean isMatch = passwordEncoder.matches("123456",securityUserDTO.getPassword());
//        System.out.println("密码是否相同:"+isMatch);

        System.out.println("资源标识符: "+aUser.getSources());
        System.out.println("其权限" + securityUserDTO.getAuthorities());

    }
    @Test
    public void UserDetailsServiceTest(){
        userDetailsService.loadUserByUsername("ssm");


    }




    /*公钥加密*/
    public void publicKeyCrypt() throws Exception {
        String plainSecret = "123456";
        RsaUtils.encryptByPublicKey(RsaProperties.publicKey,plainSecret);
    }

    @Test
    public void bCryptTest(){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String encode = bc.encode("123456");
        System.out.println(encode);
        boolean isSame = passwordEncoder.matches("123456","$2a$10$2ssT/FMh7ajUO/pd48Xhi.LDo3pPBxkpZoOYfa1IuqhSOhvoWQz4m");
        System.out.println("是否相同:"+isSame);
    }
}