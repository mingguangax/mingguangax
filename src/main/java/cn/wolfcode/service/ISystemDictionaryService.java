package cn.wolfcode.service;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryService {
    void insert(SystemDictionary systemDictionary);

    void deleteById(Long id);

    void update(SystemDictionary systemDictionary);

    List<SystemDictionary> selectById(Long id);

    List<SystemDictionary> selectAll();

    PageInfo<SystemDictionary> selectByList(QueryObject qo);

    List<SystemDictionary> selectQueryTree();

    List<SystemDictionary> queryBySb(String sn);

    List<SystemDictionary> quertItemById(Long id);


}
