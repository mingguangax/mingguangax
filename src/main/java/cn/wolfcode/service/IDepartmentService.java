package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void insert(Department department);

    void deleteById(Long id);

    void update(Department department);

    Department selectById(Long id);

    List<Department> selectAll();

    PageInfo<Department> selectByList(QueryObject qo);
}
