package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //department/list"
    //处理查询所有部门的请求
    @RequestMapping("/list")
    @RequirePermission(name = "部门查询",expression = "department:list")
    public String list(Model model , QueryObject qo) {
        PageInfo<Department> pageInfo = departmentService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"department/list";
    }
    //处理删除部门的请求
    //department/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "部门删除",expression = "department:delete")
    public String delete(Long id){
        if(id != null){
            departmentService.deleteById(id);
        }
        return "redirect:/department/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "部门编辑" ,expression = "department:saveOrUpdate")
    public  String saveOrUpdate(Department department){
        if(department.getId() == null){
            departmentService.insert(department);
        }else {
            departmentService.update(department);
        }
        return "redirect:/department/list";
    }

}
