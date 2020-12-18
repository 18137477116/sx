package com.elltor.greenlandsystem;

import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectDataBaseTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void selectTest(){
        List<Employee> employees = employeeMapper.selectList(null);
        for(Employee e: employees){
            System.out.println(e);
        }
    }
}
