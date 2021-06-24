package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Consumption;
import cn.wolfcode.domain.ConsumptionItem;
import cn.wolfcode.mapper.ConsumptionItemMapper;
import cn.wolfcode.mapper.ConsumptionMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IConsumptionItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionItemServiceImpl implements IConsumptionItemService {

    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Autowired
    private ConsumptionItemMapper consumptionItemMapper;

    @Override
    public void insert(ConsumptionItem consumptionItem) {
        //
        consumptionItemMapper.insert(consumptionItem);
        //
        Consumption consumption = consumptionMapper.selectByCno(consumptionItem.getCno());

        consumption.setTotalAmount(consumption.getTotalAmount().add(consumptionItem.getAmount()));
        consumption.setDiscountAmount(consumption.getDiscountAmount().add(consumptionItem.getDiscountAmount()));
        consumption.setPayAmount(consumption.getPayAmount().add(consumptionItem.getPayAmount()));

        //修改汇总
        consumptionMapper.updateSum(consumption);

    }

    @Override
    public void deleteById(Long id) {
        consumptionItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ConsumptionItem consumptionItem) {
        consumptionItemMapper.updateByPrimaryKey(consumptionItem);
    }

    @Override
    public ConsumptionItem selectById(Long id) {
        ConsumptionItem consumptionItem = consumptionItemMapper.selectByPrimaryKey(id);
        return consumptionItem;
    }

    @Override
    public List<ConsumptionItem> selectAll() {
        List<ConsumptionItem> consumptionItems = consumptionItemMapper.selectAll();
        return consumptionItems;
    }

    @Override
    public PageInfo<ConsumptionItem> selectByList(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        List<ConsumptionItem> consumptionItems =consumptionItemMapper.selectForList(qo);
        return new PageInfo<>(consumptionItems);
    }

    @Override
    public List<ConsumptionItem> queryByCno(String cno) {
        return consumptionItemMapper.selectByCno(cno);
    }
}





















