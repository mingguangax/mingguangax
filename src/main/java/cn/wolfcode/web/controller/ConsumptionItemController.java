package cn.wolfcode.web.controller;

import cn.wolfcode.domain.ConsumptionItem;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IConsumptionItemService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consumptionItem")
public class ConsumptionItemController {
    private IConsumptionItemService consumptionItemService;

    @Autowired
    public void setConsumptionItemService(IConsumptionItemService consumptionItemService) {
        this.consumptionItemService = consumptionItemService;
    }

    //consumptionItem/list"
    //处理查询所有预约单明细的请求
    @RequestMapping("/list")
    @RequirePermission(name = "预约单明细查询",expression = "consumptionItem:list")
    public String list(Model model , QueryObject qo) {
        PageInfo<ConsumptionItem> pageInfo = consumptionItemService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"consumptionItem/list";
    }
    //处理删除预约单明细的请求
    //consumptionItem/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "预约单明细删除",expression = "consumptionItem:delete")
    public String delete(Long id){
        if(id != null){
            consumptionItemService.deleteById(id);
        }
        return "redirect:/consumptionItem/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "预约单明细编辑" ,expression = "consumptionItem:saveOrUpdate")
    public  String saveOrUpdate(ConsumptionItem consumptionItem){
        if(consumptionItem.getId() == null){
            consumptionItemService.insert(consumptionItem);
        }else {
            consumptionItemService.update(consumptionItem);
        }
        return "redirect:/consumption/input?id="+consumptionItem.getId();
    }

}



















