package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class EmployeeServiceImplTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void query() {
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setCurrentPage(2);
        PageResult<Employee> query = employeeService.query(qo);
        System.out.println(query);
    }
}