package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.mapper.RoleMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void insert(Role role, Long[] permissionIds) {
        //角色中间表和角色表存入数据
        roleMapper.insert(role);
        if (permissionIds!=null && permissionIds.length>0) {
            for (Long permissionId : permissionIds) {
                roleMapper.insertRelation(role.getId(),permissionId);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Role role, Long[] permissionIds) {
        //更新role表和role_permission表
        roleMapper.updateByPrimaryKey(role);
        //删除关系表中该角色ID的所有权限
        roleMapper.deleteRolePermissionByRoleId(role.getId());

        if (permissionIds != null) {
            for (Long permissionId : permissionIds) {
                //增加
                roleMapper.insertRelation(role.getId(), permissionId);
            }
        }
    }

    @Override
    public Role selectById(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    @Override
    public PageInfo<Role> selectByList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Role> roles =roleMapper.selectForList(qo);
        return new PageInfo<>(roles);
    }

    @Override
    public List<Permission> selectRolePermissionById(Long roleId) {
        List<Permission> permissions = roleMapper.selectRolePermissionById(roleId);
        return permissions;
    }

    @Override
    public void deleteRolePermission(Long id) {
        roleMapper.deleteRolePermissionByRoleId(id);
    }
}
