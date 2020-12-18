package com.elltor.greenlandsystem.modules.security.service;

import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.utils.RedisUtils;
import com.elltor.greenlandsystem.modules.biz.bo.EmployeeBO;
import com.elltor.greenlandsystem.modules.biz.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.entity.Role;
import com.elltor.greenlandsystem.modules.biz.entity.Sources;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.elltor.greenlandsystem.modules.biz.service.IRoleService;
import com.elltor.greenlandsystem.modules.security.config.bean.SecurityProperties;
import com.elltor.greenlandsystem.modules.security.dto.SecurityUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service("userDetailsService")
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityProperties securityProperties;
    private final IEmployeeService employeeService;
    private final IRoleService roleService;
    private final RedisUtils redisUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean isSearchDb = true;
        SecurityUserDTO securityUserDTO = null;
        if (securityProperties.isCacheEnable() && redisUtils.hasKey(username)) {
            try {
                securityUserDTO = (SecurityUserDTO) SerializationUtils.deserialize((byte[]) redisUtils.get(username));
                log.info("\n"+securityUserDTO+"\n");
                isSearchDb = false;
            } catch (SerializationException e) {
                log.error("用户反序列化错误 :" + e.getMessage());
                isSearchDb = true;
            }
        }
        if (isSearchDb) {
            EmployeeBO employee = employeeService.findEmployeeByUsername(username);
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            //设置权限
            HashSet<String> grantSet = employee.getRoles().stream().map(Role::getRolename).collect(Collectors.toCollection(HashSet::new));

            List<GrantedAuthority> authorities = grantSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            securityUserDTO = new SecurityUserDTO(employeeDTO,authorities);
            redisUtils.set(username, securityUserDTO);
        }
        return securityUserDTO;
    }
}
