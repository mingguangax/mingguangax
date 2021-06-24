package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface IEmployeeService {
    void insert(Employee employee);

    void deleteById(Long id);

    void update(Employee employee);

    int selectByCount();

    Employee selectById(Long id);

    PageResult<Employee> query(QueryObject qo);

    PageInfo<Employee> selectPage(QueryObject qo);


    void insertEmplRole(Long id, Long[] roleId);

    void updateEmplRole(Long id, Long[] roleId);

    List<Role> selectEmplRoleById(Long id);

    Boolean checkUsername(String username);

    Employee login(String username, String password);

    Workbook exportXls();

    void importXls(Workbook wb);

    void updateById(String newPas,Long id);
}
