package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("record") Department record);

    Department selectById(@Param("id") Long id);

    List<Department> selectAll();

    int update(Department record);



    List<Department> selectForList(QueryObject qo);
}