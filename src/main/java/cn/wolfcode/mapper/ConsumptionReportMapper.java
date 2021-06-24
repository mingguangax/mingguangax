package cn.wolfcode.mapper;

import cn.wolfcode.qo.ConsumptionReportQueryObject;
import cn.wolfcode.vo.ConsumptionReportVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ConsumptionReportMapper {

    List<Map<String , BigDecimal>> selectConsumptionReport(ConsumptionReportQueryObject qo);

    List<ConsumptionReportVO> selectConsumptionReportVO(ConsumptionReportQueryObject qo);
}















