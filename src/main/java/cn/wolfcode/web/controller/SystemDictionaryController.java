package cn.wolfcode.web.controller;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.service.ISystemDictionaryService;
import cn.wolfcode.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    public void setSystemDictionaryService(ISystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    //systemDictionary/list"
    //处理查询所有数据字典的请求
    @RequestMapping("/list")
    @RequirePermission(name = "数据字典查询",expression = "systemDictionary:list")
    public String list(Model model) {
        List<SystemDictionary> systemDictionaries = systemDictionaryService.selectQueryTree();
        model.addAttribute("systemDictionaries",systemDictionaries);
        return"systemDictionary/list";
    }
    //处理删除数据字典的请求
    //systemDictionary/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "数据字典删除",expression = "systemDictionary:delete")
    public String delete(Long id){
        if(id != null){
            systemDictionaryService.deleteById(id);
        }
        return "redirect:/systemDictionary/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "数据字典编辑" ,expression = "systemDictionary:saveOrUpdate")
    public  String saveOrUpdate(SystemDictionary systemDictionary){
        if(systemDictionary.getId() == null){
            systemDictionaryService.insert(systemDictionary);
        }else {
            systemDictionaryService.update(systemDictionary);
        }
        return "redirect:/systemDictionary/list";
    }

    @RequestMapping("/queryItemById")
    @ResponseBody
    public List<SystemDictionary> queryByItemId(Long id){
       return systemDictionaryService.quertItemById(id);
    }

}
