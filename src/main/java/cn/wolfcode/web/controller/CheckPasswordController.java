package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/checkPassword")
public class CheckPasswordController {
    @Autowired
    private IEmployeeService employeeService;



    @RequestMapping("/check")
    @ResponseBody
    public JsonResult check(String oldPas,String newPas){
        try{
        Employee employee = UserContext.getEmployee();
        Long id = employee.getId();
        if (oldPas.equals(employee.getPassword())){
            //执行修改密码操作
            employeeService.updateById(newPas,id);
            return new JsonResult(true,"修改成功");
        }else {
            //与旧密码不对
            return new JsonResult(false,"修改失败");
        }
        }catch (Exception e){
            //出现异常
            return new JsonResult(false,"修改失败");
        }

    }

    //删除session
    @RequestMapping("/deleteSession")
    public String delete(){

        UserContext.getSession().removeAttribute("employee_in_session");
        return "redirect:/static/login.html";

    }
}
