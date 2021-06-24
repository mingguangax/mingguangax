package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Consumption;
import cn.wolfcode.domain.ConsumptionItem;
import cn.wolfcode.enums.ConsumprionStatusEnum;
import cn.wolfcode.qo.ConsumptionQueryObject;
import cn.wolfcode.service.IBusinessService;
import cn.wolfcode.service.IConsumptionItemService;
import cn.wolfcode.service.IConsumptionService;
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
@RequestMapping("/consumption")
public class ConsumptionController {
    private IConsumptionService consumptionService;
    @Autowired
    private IConsumptionItemService consumptionItemService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    public void setConsumptionService(IConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }
    @Autowired
    private IBusinessService businessService;


    @RequestMapping("/save")
    public String save(Long appointmentId){
        Consumption consumption = consumptionService.save(appointmentId);
        return "redirect:/consumption/input?id="+consumption.getId();
    }


    //consumption/list"
    //处理查询所有预约单的请求
    @RequestMapping("/list")
    @RequirePermission(name = "预约单查询",expression = "consumption:list")
    public String list(Model model , @ModelAttribute("qo") ConsumptionQueryObject qo) {

        //把所有门店查出来
        model.addAttribute("businesses",businessService.selectAll());
        //把所有结算单状态
        model.addAttribute("ConsumprionStatus", ConsumprionStatusEnum.values());


        PageInfo<Consumption> pageInfo = consumptionService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"consumption/list";
    }
    //处理删除预约单的请求
    //consumption/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "预约单删除",expression = "consumption:delete")
    public String delete(Long id){
        if(id != null){
            consumptionService.deleteById(id);
        }
        return "redirect:/consumption/list";
    }

    @RequestMapping("/input")
    public String input(Long id ,Model model){
        model.addAttribute("businesses",businessService.selectAll());

        model.addAttribute("categories",systemDictionaryService.queryBySb("business"));
        model.addAttribute("payType",systemDictionaryService.queryBySb("pay_type"));



        if (id!=null) {
            Consumption consumption = consumptionService.selectById(id);
            model.addAttribute("consumption",consumption);
            //根据结算单流水号查
            List<ConsumptionItem> consumptionItems = consumptionItemService.queryByCno(consumption.getCno());
            model.addAttribute("consumptionItems",consumptionItems);
            System.out.println(1);

        }

        return  "consumption/input";
    }

    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "预约单编辑" ,expression = "consumption:saveOrUpdate")
    public  String saveOrUpdate(Consumption consumption){
        if(consumption.getId() == null){
            consumptionService.insert(consumption);
        }else {
            consumptionService.update(consumption);
        }
        return "redirect:/consumption/list";
    }

}
