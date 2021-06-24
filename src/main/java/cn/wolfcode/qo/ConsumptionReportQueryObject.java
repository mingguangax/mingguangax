package cn.wolfcode.qo;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class ConsumptionReportQueryObject extends QueryObject {
    private String groupType = "门店";

    private Long businessId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;
    private Boolean appointmentType;

    private static Map<String,String> groupMap = new HashMap<>();
    static {
        groupMap.put("门店","b.name");
        groupMap.put("年","DATE_FORMAT(c.create_time,'%Y')");
        groupMap.put("月","DATE_FORMAT(c.create_time,'%Y-%m')");
        groupMap.put("日","DATE_FORMAT(c.create_time,'%Y-%m-%d')");
    }
    public String getGroupTypeName(){
        return groupMap.get(this.groupType);
    }

    //时间转成最后一天
    public Date getEndCreateTime(){
        return this.endCreateTime!=null?DateUtil.endOfDay(this.endCreateTime) : null;

    }
}



















