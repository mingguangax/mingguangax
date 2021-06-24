package cn.wolfcode.service.impl;

import cn.wolfcode.mapper.ConsumptionReportMapper;
import cn.wolfcode.qo.ConsumptionReportQueryObject;
import cn.wolfcode.service.IConsumptionReportService;
import cn.wolfcode.vo.ConsumptionReportVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionReportServiceImpl implements IConsumptionReportService {
    @Autowired
    private ConsumptionReportMapper consumptionReportMapper;

    @Override
    public PageInfo<ConsumptionReportVO> list(ConsumptionReportQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        return new PageInfo<>(consumptionReportMapper.selectConsumptionReportVO(qo));
    }
}
