package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void insert(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public Department selectById(Long id) {
        Department department = departmentMapper.selectById(id);
        return department;
    }

    @Override
    public List<Department> selectAll() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }

    @Override
    public PageInfo<Department> selectByList(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        List<Department> departments =departmentMapper.selectForList(qo);
        return new PageInfo<>(departments);
    }
}
