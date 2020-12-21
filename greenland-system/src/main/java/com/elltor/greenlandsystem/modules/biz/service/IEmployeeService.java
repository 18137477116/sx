package com.elltor.greenlandsystem.modules.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elltor.greenlandcommon.base.PageVO;
import com.elltor.greenlandsystem.modules.biz.entity.bo.EmployeeBO;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeAddDTO;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuQichun
 * @since 2020-12-14
 */
public interface IEmployeeService extends IService<Employee> {

    EmployeeBO findEmployeeByUsername(String username);

    IPage<EmployeeDTO> employeeList(String key, PageVO pageVO);

    void update(EmployeeDTO employeeDTO);

    void delete(Integer id);

}
