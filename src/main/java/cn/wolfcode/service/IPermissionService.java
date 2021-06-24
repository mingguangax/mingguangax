package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface IPermissionService {
    void insert(Permission permission);

    void deleteById(Integer id);

    void update(Permission permission);

    Permission selectById(Integer id);

    List<Permission> selectAll();

    PageInfo<Permission> selectByList(QueryObject qo);

    void batchSavr(Set<Permission> permissions);

    List<String> queryExpressionByEmployeeId(Long id);
}
