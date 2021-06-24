package cn.wolfcode.mapper;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    List<SystemDictionary> selectById(Long id);

    List<SystemDictionary> selectAll();

    int update(SystemDictionary record);

    List<SystemDictionary> selectForList(@Param("qo") QueryObject qo);

    List<SystemDictionary> selectByParentId(@Param("parentId") Long parentId);

    List<SystemDictionary> queryBySn(String sn);

    List<SystemDictionary> selectItemById(Long id);

}