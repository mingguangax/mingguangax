package cn.wolfcode.web.controller;

import cn.wolfcode.qo.ConsumptionReportQueryObject;
import cn.wolfcode.service.IBusinessService;
import cn.wolfcode.service.IConsumptionReportService;
import cn.wolfcode.vo.ConsumptionReportVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consumptionReport")
public class ConsumptionReportController {
    @Autowired
    private IConsumptionReportService consumptionReportService;
    @Autowired
    private IBusinessService businessService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") ConsumptionReportQueryObject qo){
        PageInfo<ConsumptionReportVO> pageInfo = consumptionReportService.list(qo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("businesses",businessService.selectAll());
        return "consumptionReport/list";
    }

}














