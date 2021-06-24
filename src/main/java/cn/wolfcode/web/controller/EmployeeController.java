package cn.wolfcode.web.controller;

import cn.wolfcode.domain.*;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.*;
import cn.wolfcode.util.RequirePermission;
import cn.wolfcode.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private IEmployeeService employeeService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMessageReplyService messageReplyService;

    @Autowired
    private IMessageBoardService messageBoardService;

    //employee/list"
    //处理员工查询分页的请求
    @RequestMapping("/list")

    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        model.addAttribute("qo",qo);

        List<Department> departments = departmentService.selectAll();
        model.addAttribute("departments",departments);
        PageInfo<Employee> pageInfo = employeeService.selectPage(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "employee/list";
    }

    //处理删除员工的请求
    //employee/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "员工删除",expression = "employee:delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.deleteById(id);
        }
        return "redirect:/employee/list";
    }

    //跳转页面(处理保存请求)
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        model.addAttribute("department", departmentService.selectAll());
        //所有权限
        List<Role> roles = roleService.selectAll();
        if (id != null) {
            //编辑操作
            Employee employee = employeeService.selectById(id);
            //角色拥有的权限
            List<Role> rightRoles = employeeService.selectEmplRoleById(id);
            List<Role> leftRoles = new ArrayList<>();

            //对所有权限遍历
            for (Role role : roles) {
                if (!rightRoles.contains(role)){
                    leftRoles.add(role);
                }
            }
            model.addAttribute("role",leftRoles);
            model.addAttribute("employee", employee);
            model.addAttribute("rightRole", rightRoles);
        }else {

            model.addAttribute("role", roles);
        }
        return "/employee/input";

    }

    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @RequirePermission(name = "员工编辑" ,expression = "employee:saveOrUpdate")
    public JsonResult saveOrUpdate(Employee employee, Long[] roleId) {
        try {
            if (employee.getId() == null) {
                //添加操作
                //保存到员工表
                employeeService.insert(employee);
                //保存数据到关系表
                employeeService.insertEmplRole(employee.getId(),roleId);
            } else {
                //更新操作
                //更新员工表
                employeeService.update(employee);
                //更新管理表
                employeeService.updateEmplRole(employee.getId(),roleId);
            }
            return new JsonResult(true,"编辑成功");
        }catch (Exception e){
            return new JsonResult(false,"编辑失败");
        }

    }

    //检查用户名
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Map<String,Boolean> checkUsername(String username){
        //存在返回true 不存在返回false
        Boolean exite = employeeService.checkUsername(username);
        Map<String ,Boolean> data = new HashMap<>();
        data.put("valid",!exite);
        return data;
    }

    @RequestMapping("/exportXls")
    public void exprotXls(HttpServletResponse response) throws IOException {
        //文件下载的响应头(让浏览器访问资源的时候以下载的方式打开)
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        //创建excel文件
        Workbook wb = employeeService.exportXls();
        wb.write(response.getOutputStream());
    }

    @RequestMapping("/importXls")
    public String importXls(MultipartFile file) throws IOException {
        //根据用户上传的文件创建workbook对象
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        employeeService.importXls(wb);

        return "redirect:/employee/list";
    }

    @RequestMapping("/insertMsg")
    public String insertMsg(MessageReply messageReply,Long messageId){
        if (UserContext.getEmployee()!=null) {
        //messageId在模态框hidden
        Date now = new Date();
        messageReply.setCreateTime(now);
        messageReply.setReplyUserId(UserContext.getEmployee().getId());
        messageReply.setMessageId(messageId);
        messageReplyService.insert(messageReply);

        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setReplyStatus(true);
        messageBoard.setId(messageId);
        messageBoardService.update(messageBoard);

        return "redirect:/messageBoard/list";
        }else {
            return "redirect:/static/login.html";
        }
    }
}
