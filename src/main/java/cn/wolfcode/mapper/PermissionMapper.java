package cn.wolfcode.mapper;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectForList(QueryObject qo);

    void batchInsert(@Param("permissions") Set<Permission> permissions);


    List<String> queryExpressionByEmployeeId(@Param("id") Long id);
}