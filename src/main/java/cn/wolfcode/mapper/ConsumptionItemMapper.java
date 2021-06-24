package cn.wolfcode.mapper;

import cn.wolfcode.domain.ConsumptionItem;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumptionItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumptionItem record);

    ConsumptionItem selectByPrimaryKey(Long id);

    List<ConsumptionItem> selectAll();

    int updateByPrimaryKey(ConsumptionItem record);

    List<ConsumptionItem> selectForList(@Param("qo") QueryObject qo);

    List<ConsumptionItem> selectByCno(String cno);
}