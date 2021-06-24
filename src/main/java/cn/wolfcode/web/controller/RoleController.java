package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.service.IRoleService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    private IRoleService roleService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    private IPermissionService permissionService;

    //role/list"
    //处理查询所有角色的请求
    @RequestMapping("/list")
    @RequirePermission(name = "角色查询",expression = "role:list")
    public String list(Model model , QueryObject qo) {
        PageInfo<Role> pageInfo = roleService.selectByList(qo);
        model.addAttribute("pageInfo",pageInfo);
        return"role/list";
    }

    //页面跳转
    @RequestMapping("/input")
    @RequirePermission(name = "角色跳转",expression = "role:input")
    public String input(Model model,Long id){
        //所有的权限
        List<Permission> allpermissions = permissionService.selectAll();
        if (id!=null){
            //查询角色拥有的permission
            List<Permission> permissions = roleService.selectRolePermissionById(id);

            //创建一个空的permiss 来存角色没有的权限
            List<Permission> permissions1 = new ArrayList<>();

            for (Permission allpermission : allpermissions) {
                if (!permissions.contains(allpermission)) {
                    permissions1.add(allpermission);
                }
            }
            model.addAttribute("role",roleService.selectById(id));
            //角色没有的权限(左边)
            model.addAttribute("allpermission",permissions1);
            //角色有的权限(右边)
            model.addAttribute("permissions",permissions);
        }else {
            model.addAttribute("allpermission",allpermissions);
        }
        return "role/input";
    }
    //处理删除角色的请求
    //role/delete?id=1
    @RequestMapping("/delete")
    @RequirePermission(name = "角色删除",expression = "role:delete")
    public String delete(Long id){
        if(id != null){
            roleService.deleteById(id);
            roleService.deleteRolePermission(id);
        }
        return "redirect:/role/list";
    }


    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "角色编辑" ,expression = "role:saveOrUpdate")
    public  String saveOrUpdate(Role role,Long[] permissionIds){
        if(role.getId() == null){
            roleService.insert(role, permissionIds);
        }else {
            roleService.update(role, permissionIds);
        }
        return "redirect:/role/list";
    }

}
