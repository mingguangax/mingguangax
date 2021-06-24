package cn.wolfcode.service;

import cn.wolfcode.domain.ConsumptionItem;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IConsumptionItemService {
    void insert(ConsumptionItem consumptionItem);

    void deleteById(Long id);

    void update(ConsumptionItem consumptionItem);

    ConsumptionItem selectById(Long id);

    List<ConsumptionItem> selectAll();

    PageInfo<ConsumptionItem> selectByList(QueryObject qo);

    List<ConsumptionItem> queryByCno(String cno);
}
