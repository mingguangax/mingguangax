package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password){
        Employee employee = employeeService.login(username,password);
        if (employee != null) {
            HttpSession session = UserContext.getSession();
            //往session中存入登录员工对象
            UserContext.setEmployee(employee);
            if (!employee.getAdmin()) {
                //将权限去除存到expression_in_session 中
                List<String> expressions = permissionService.queryExpressionByEmployeeId(employee.getId());
                session.setAttribute("expression_in_session",expressions);
            }
            return new JsonResult(true,"登录成功");
        }else {
            return new JsonResult(false,"登录失败");
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        HttpSession session = UserContext.getSession();
        session.invalidate();
        return "redirect:/static/login.html";
    }
}
