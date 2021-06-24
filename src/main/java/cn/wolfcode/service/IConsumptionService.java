package cn.wolfcode.service;

import cn.wolfcode.domain.Consumption;
import cn.wolfcode.qo.ConsumptionQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IConsumptionService {
    void insert(Consumption consumption);

    void deleteById(Long id);

    void update(Consumption consumption);

    Consumption selectById(Long id);

    List<Consumption> selectAll();

    PageInfo<Consumption> selectByList(ConsumptionQueryObject qo);

    Consumption save(Long appointmentId);
}
