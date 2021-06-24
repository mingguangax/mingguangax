package cn.wolfcode.service;

import cn.wolfcode.domain.Business;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBusinessService {
    void insert(Business business);

    void deleteById(Long id);

    void update(Business business);

    Business selectById(Long id);

    List<Business> selectAll();

    PageInfo<Business> selectByList(QueryObject qo);

    Business queryMainBusiness();
}
