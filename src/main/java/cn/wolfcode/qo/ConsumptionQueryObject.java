package cn.wolfcode.qo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ConsumptionQueryObject extends QueryObject {

    private Integer staTus;
    private Long businessId;
    private Boolean appointmentType;
    private String customerName;
    private String customerTel;
    private String appointmentAno;
    private String cno;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginPayTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endPayTime;

}
