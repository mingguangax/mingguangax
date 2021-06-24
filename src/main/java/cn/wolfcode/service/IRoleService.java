package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
    void insert(Role role, Long[] permissionIds);

    void deleteById(Long id);

    void update(Role role, Long[] permissionIds);

    Role selectById(Long id);

    List<Role> selectAll();

    PageInfo<Role> selectByList(QueryObject qo);

    List<Permission> selectRolePermissionById(Long roleId);

    void deleteRolePermission(Long id);
}
