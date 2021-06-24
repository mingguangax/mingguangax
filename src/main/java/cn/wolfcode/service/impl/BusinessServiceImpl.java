package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Business;
import cn.wolfcode.mapper.BusinessMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IBusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public void insert(Business business) {
        businessMapper.insert(business);
    }

    @Override
    public void deleteById(Long id) {
        businessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Business business) {

        businessMapper.updateByPrimaryKey(business);

    }

    @Override
    public Business selectById(Long id) {
        Business business = businessMapper.selectByPrimaryKey(id);
        return business;
    }

    @Override
    public List<Business> selectAll() {
        List<Business> businesss = businessMapper.selectAll();
        return businesss;
    }

    @Override
    public PageInfo<Business> selectByList(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        List<Business> businesss =businessMapper.selectForList(qo);
        return new PageInfo<>(businesss);
    }

    @Override
    public Business queryMainBusiness() {

        return businessMapper.selectMainBusiness();
    }
}
