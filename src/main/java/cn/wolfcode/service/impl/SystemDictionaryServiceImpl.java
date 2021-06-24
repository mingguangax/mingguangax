package cn.wolfcode.service.impl;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.mapper.SystemDictionaryMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void insert(SystemDictionary systemDictionary) {
        systemDictionaryMapper.insert(systemDictionary);
    }

    @Override
    public void deleteById(Long id) {
        systemDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SystemDictionary systemDictionary) {
        systemDictionaryMapper.update(systemDictionary);
    }

    @Override
    public List<SystemDictionary> selectById(Long id) {
        List<SystemDictionary> systemDictionary = systemDictionaryMapper.selectById(id);
        return systemDictionary;
    }

    @Override
    public List<SystemDictionary> selectAll() {
        List<SystemDictionary> systemDictionarys = systemDictionaryMapper.selectAll();
        return systemDictionarys;
    }

    @Override
    public PageInfo<SystemDictionary> selectByList(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        List<SystemDictionary> systemDictionarys =systemDictionaryMapper.selectForList(qo);
        return new PageInfo<>(systemDictionarys);
    }



    @Override
    public List<SystemDictionary> selectQueryTree() {
        //图个数据字典下面可能有子字典,字字典可能还有字字典
        //
        //最顶级一层
        List<SystemDictionary> systemDictionaries = systemDictionaryMapper.selectByParentId(null);
        //查询出自己的子数据字典 存到模型层的itmes中
        tree(systemDictionaries);

        return systemDictionaries;

    }

    @Override
    public List<SystemDictionary> queryBySb(String sn) {

        return systemDictionaryMapper.queryBySn(sn);
    }

    @Override
    public List<SystemDictionary> quertItemById(Long id) {
        return  systemDictionaryMapper.selectItemById(id);
    }


    private void tree(List<SystemDictionary> systemDictionaries){
        for (SystemDictionary systemDictionary : systemDictionaries) {
            Long systemDictionaryId = systemDictionary.getId();

            List<SystemDictionary> childItems = systemDictionaryMapper.selectByParentId(systemDictionaryId);
            //把子数据字典存到对应的属性上
            systemDictionary.setItems(childItems);
            if (childItems.size()>0) {
                tree(childItems);
            }

        }
    }














}
