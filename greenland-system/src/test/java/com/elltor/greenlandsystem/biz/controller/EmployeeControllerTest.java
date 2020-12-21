package com.elltor.greenlandsystem.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elltor.greenlandcommon.base.PageVO;
import com.elltor.greenlandcommon.utils.StringUtils;
import com.elltor.greenlandsystem.modules.biz.controller.EmployeeController;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.elltor.greenlandsystem.modules.biz.service.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    IEmployeeService employeeService;

    @Test
    public void updateTest(){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setUsername("liuqicun");
        employeeDTO.setEname("刘启春");
        employeeDTO.setEid(6);
        employeeService.update(employeeDTO);

    }

    @Test
    public void employeeList(){
        PageVO page = new PageVO();
        page.setSize(10);
        page.setCurrent(0);
        IPage<EmployeeDTO> employeeDTOIPage = employeeService.employeeList(null, page);
        System.out.println("currentPage : "+employeeDTOIPage.getCurrent());
        System.out.println("page size : "+employeeDTOIPage.getSize());
        System.out.println("--------------------");
        List<EmployeeDTO> empList = employeeDTOIPage.getRecords();
        for(EmployeeDTO e: empList){
            System.out.println(e);
        }
    }
    @Test
    public void deleteTest(){
        employeeService.delete(6);

        PageVO page = new PageVO();
        page.setSize(10);
        page.setCurrent(0);
        IPage<EmployeeDTO> employeeDTOIPage = employeeService.employeeList(null, page);
        System.out.println("currentPage : "+employeeDTOIPage.getCurrent());
        System.out.println("page size : "+employeeDTOIPage.getSize());
        System.out.println("--------------------");
        List<EmployeeDTO> empList = employeeDTOIPage.getRecords();
        for(EmployeeDTO e: empList){
            System.out.println(e);
        }
    }
}
