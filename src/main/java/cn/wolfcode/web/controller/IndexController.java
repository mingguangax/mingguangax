package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Appointment;
import cn.wolfcode.domain.Business;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.IAppointmentService;
import cn.wolfcode.service.IBusinessService;
import cn.wolfcode.service.ISystemDictionaryService;
import cn.wolfcode.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private IBusinessService businessService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    private IAppointmentService appointmentService;


    @RequestMapping("/index")
    public String index(Model model){
        Business business = businessService.queryMainBusiness();
        model.addAttribute("business",business);
        //查询业务这个数据字典下的数据字典

        model.addAttribute("businesses",businessService.selectAll());
        List<SystemDictionary> systemDictionaries = systemDictionaryService.queryBySb("business");
        model.addAttribute("systemDictionaries",systemDictionaries);
        return "index";
    }

    @RequestMapping("/index/save")
    @ResponseBody
    public JsonResult save(Appointment appointment){
        appointmentService.save(appointment);
        return new JsonResult(true,"预约成功");
    }

    @RequestMapping("/index/checkSession")
    @ResponseBody
    public JsonResult check(){
        Employee employee = UserContext.getEmployee();
        if (employee==null) {
            //告诉他没有权限
            return new JsonResult(false,"无权限");
        }else {
            return new JsonResult(true,"有权限");
        }
    }

}
