package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeMapper.deleteById(id);
        employeeMapper.deleteEmpRoleById(id);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public int selectByCount() {
        int count = employeeMapper.selectByCount();
        return count;
    }

    @Override
    public Employee selectById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        return employee;
    }

    @Override
    public PageResult<Employee> query(QueryObject qo) {
        int count =employeeMapper.selectByCount();
        if(count == 0){
            return  new PageResult<>(qo.getCurrentPage() ,qo.getPageSize(),count ,null);
        }
        List<Employee> employees =employeeMapper.selectByList(qo);
        return  new PageResult<>(qo.getCurrentPage() ,qo.getPageSize(),count ,employees);

    }

    @Override
    public PageInfo<Employee> selectPage(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> employees = employeeMapper.selectByList(qo);
        return new PageInfo<>(employees);
    }

    @Override
    public void insertEmplRole(Long id, Long[] roleId) {
        if (roleId!=null && roleId.length>0) {
            for (Long aLong : roleId) {
                employeeMapper.insertEmplRole(id, aLong);
            }
        }
    }

    @Override
    public void updateEmplRole(Long id, Long[] roleId) {
        //???????????????
        //?????????ID?????????????????????
        employeeMapper.deleteEmpRoleById(id);
        //?????????????????????
        this.insertEmplRole(id,roleId);
    }

    @Override
    public List<Role> selectEmplRoleById(Long id) {
        return employeeMapper.selectEmplRoleById(id);
    }

    @Override
    public Boolean checkUsername(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        return employee!=null;
    }

    @Override
    public Employee login(String username, String password) {
        return employeeMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public Workbook exportXls() {
        //??????excel??????
        Workbook wb = new HSSFWorkbook();
        //??????sheet
        Sheet sheet = wb.createSheet("????????????");
        //???????????????
        Row row = sheet.createRow(0);
        //???????????????????????????
        row.createCell(0).setCellValue("??????");
        row.createCell(1).setCellValue("??????");
        row.createCell(2).setCellValue("??????");
        // ??????????????????
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            // ?????????(????????????????????????)
            row = sheet.createRow(i + 1);
            // ???????????????????????????
            row.createCell(0)
                    .setCellValue(employee.getName());
            row.createCell(1)
                    .setCellValue(employee.getEmail());
            row.createCell(2)
                    .setCellValue(employee.getAge());
        }
        return wb;
    }

    @Override
    public void importXls(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        //????????????????????????????????????

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(1);
            String username = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            int age = (int)row.getCell(2).getNumericCellValue();
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setName(name);
            employee.setAge(age);
            employee.setPassword("1");

            employeeMapper.insert(employee);
        }

    }

    @Override
    public void updateById(String newPas,Long id) {
        employeeMapper.updateById(newPas,id);
    }


}
