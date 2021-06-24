package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public void insert(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void deleteById(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public Permission selectById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    @Override
    public List<Permission> selectAll() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }

    @Override
    public PageInfo<Permission> selectByList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> permissions = permissionMapper.selectForList(qo);
        return new PageInfo<>(permissions);
    }

    @Override
    public void batchSavr(Set<Permission> permissions) {
        permissionMapper.batchInsert(permissions);
    }

    @Override
    public List<String> queryExpressionByEmployeeId(Long id) {
        return permissionMapper.queryExpressionByEmployeeId(id);

    }


}
