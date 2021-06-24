package cn.wolfcode.service;

import cn.wolfcode.qo.ConsumptionReportQueryObject;
import cn.wolfcode.vo.ConsumptionReportVO;
import com.github.pagehelper.PageInfo;

public interface IConsumptionReportService {
    PageInfo<ConsumptionReportVO> list(ConsumptionReportQueryObject qo);
}
