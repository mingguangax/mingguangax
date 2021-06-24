package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Business;
import cn.wolfcode.qo.BusinessQueryObject;
import cn.wolfcode.service.IBusinessService;
import cn.wolfcode.util.FileUploadUtil;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/business")
public class BusinessController {
    private IBusinessService businessService;


    @Autowired
    private ServletContext servletContext;

    @Autowired
    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }

    //business/list"
    //处理查询所有部门的请求
    @RequestMapping("/list")
    @RequirePermission(name = "部门查询",expression = "business:list")
    public String list(Model model, BusinessQueryObject qo) {
        model.addAttribute("qo",qo);
        PageInfo<Business> pageInfo = businessService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"business/list";
    }


    //处理删除部门的请求
    //business/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "部门删除",expression = "business:delete")
    public String delete(Long id){
        if(id != null){
            businessService.deleteById(id);
        }
        return "redirect:/business/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "部门编辑" ,expression = "business:saveOrUpdate")
    public  String saveOrUpdate(Model model, Business business, MultipartFile file) throws Exception {
        //用户传递了营业执照 删除原来的 再存新的
        if (file!=null && file.getSize()>0){

            if (StringUtils.hasLength(business.getLicenseImg())){
                //把图片的项目路径转成磁盘绝对路径,之后调用工具方法删除
                FileUploadUtil.deleteFile(servletContext.getRealPath(business.getLicenseImg()));
            }
        //用户没有传递照片 拿到原来的路径再设置回去
            //servletContext.getRealPath拿到真实路径
            String realPath = servletContext.getRealPath("/");
            String imgUrl = FileUploadUtil.uploadFile(file,realPath);
            business.setLicenseImg(imgUrl);
        }
        //新增
        if(business.getId() == null){
            businessService.insert(business);
        }else {//修改
            businessService.update(business);
        }
        return "redirect:/business/list";
    }

    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id!=null){
            Business business = businessService.selectById(id);
            model.addAttribute("business",business);
        }
        return "business/input";
    }
}














