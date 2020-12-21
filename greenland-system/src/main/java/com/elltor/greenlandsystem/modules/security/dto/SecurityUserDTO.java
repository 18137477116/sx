package com.elltor.greenlandsystem.modules.security.dto;

import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecurityUserDTO implements UserDetails, Serializable {

    private EmployeeDTO employeeDTO;

    private List<GrantedAuthority> authorities;

    /**
     * description:获取角色权限信息
     */
    public Set<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    public void setRoles(Set<String> roles) {
        authorities =
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return employeeDTO.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return employeeDTO.getUsername();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
