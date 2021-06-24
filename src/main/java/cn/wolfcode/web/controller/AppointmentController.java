package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Appointment;
import cn.wolfcode.domain.Business;
import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.enums.AppointmentStatusEnum;
import cn.wolfcode.qo.AppointmentQueryObject;
import cn.wolfcode.service.IAppointmentService;
import cn.wolfcode.service.IBusinessService;
import cn.wolfcode.service.ISystemDictionaryService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    private IBusinessService businessService;


    //appointment/list"
    //处理查询所有预约的请求
    @RequestMapping("/list")
    @RequirePermission(name = "预约查询",expression = "appointment:list")
    public String list(Model model , @ModelAttribute("qo") AppointmentQueryObject qo) {
        //查询预约业务的子数据字典
        List<SystemDictionary> systemDictionaries = systemDictionaryService.queryBySb("business");
        model.addAttribute("systemDictionaries",systemDictionaries);
        //查门店
        List<Business> businesses = businessService.selectAll();
        model.addAttribute("business",businesses);

        //把预约的状态值存到model中
        model.addAttribute("appointmentStatusEnums", AppointmentStatusEnum.values());

        PageInfo<Appointment> pageInfo = appointmentService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"appointment/list";
    }
    //处理删除预约的请求
    //appointment/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "预约删除",expression = "appointment:delete")
    public String delete(Long id){
        if(id != null){
            appointmentService.deleteById(id);
        }
        return "redirect:/appointment/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "预约编辑" ,expression = "appointment:saveOrUpdate")
    public  String saveOrUpdate(Appointment appointment){
        if(appointment.getId() == null){
            //前端不藏值就这样写
            // appointment.setStatus(AppointmentStatusEnum.PERFORM.getStatus());

            appointmentService.save(appointment);
        }else {

            appointmentService.update(appointment);
        }
        return "redirect:/appointment/list";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Appointment appointment){
        appointmentService.updateStatus(appointment);
        System.out.println(1);
        return "redirect:/appointment/list";
    }

}
