package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteById(Long id);

    int insert(Employee record);

    Employee selectById(Long id);

    List<Employee> selectAll();

    int update(Employee record);

    int selectByCount();

    List<Employee> selectByList(QueryObject qo);

    void deleteEmpRoleById(Long id);

    void insertEmplRole(@Param("id") Long id, @Param("aLong") Long aLong);

    List<Role> selectEmplRoleById(@Param("id") Long id);

    Employee selectByUsername(String username);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    void updateById(@Param("newPas") String newPas, @Param("id") Long id);
}