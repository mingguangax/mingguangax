package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    public void insert() {
        Department department = new Department();
        department.setName("这是测试数据");
        department.setSn("123");
        departmentMapper.insert(department);
    }
}