package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.*;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    private ApplicationContext ctx;

    //permission/list"
    //处理查询所有权限的请求
    @RequestMapping("/list")
    @RequirePermission(name = "权限查询",expression = "permission:list")
    public String list(Model model , QueryObject qo) {
        PageInfo<Permission> pageInfo = permissionService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"permission/list";
    }

    //权限获取
    @RequestMapping("/load")
    @ResponseBody
    @RequirePermission(name = "权限重载",expression = "permission:load")
    public JsonResult load(){
        try {
            //Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);//获取容器内的所有Controller
            Collection<Object> controllers = beans.values();//拿到所有控制器对象

            List<Permission> permissions1 = permissionService.selectAll();//原来的权限
            Set<Permission> permissions = new HashSet<>();

            for (Object controller : controllers) {
                //System.out.println(controller);
                Method[] methods = controller.getClass().getMethods();
                for (Method method : methods) {
                    //System.out.println(method);
                    //获取方法上注解的内容
                    RequirePermission annotation = method.getAnnotation(RequirePermission.class);


                    //System.out.println(annotation);
                    if (annotation!=null) {
                        //System.out.println(annotation.toString());
                        //获取注解上的内容
                        Permission permission = new Permission(annotation.name(),annotation.expression());
                        if (!permissions1.contains(permission)){
                            permissions.add(permission);
                        }
                    }
                }
            }
            if (permissions.size()>0){
                //再次插入操作
                permissionService.batchSavr(permissions);
            }
            return new JsonResult(true,"加载成功");
        }catch (Exception e){
            return new JsonResult(false,"加载失败");
        }
    }

    @RequestMapping("/nopermission")
    public String nopermission(){
        return "common/nopermission";
    }
}
