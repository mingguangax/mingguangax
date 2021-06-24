package cn.wolfcode.domain;

import cn.wolfcode.enums.AppointmentStatusEnum;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Appointment {

    /** 主键*/
    private Long id;

    /** 预约单流水号*/
    private String ano;

    /** 预约单状态 （预约中/履行中/消费中/归档/废弃单）*/
    private int status;
    public String getStatusName(){
        for (AppointmentStatusEnum statusEnum : AppointmentStatusEnum.values()) {
            if (this.status==statusEnum.getStatus()) {
                return statusEnum.getName();
            }
        }
        return "未知";
    }

    /** 业务分类*/
    private SystemDictionary systemDictionary;

    /** 预约说明*/
    private String info;

    /** 联系电话*/
    private String contactTel;

    /** 联系人名称*/
    private String contactName;

    /** 预约门店*/
    private Business business;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    /** 创建时间*/
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    /** 预约时间*/
    private Date appointmentTime;

    public String toJson(){
        Map<String ,Object> data = new HashMap<>();
        data.put("id",this.id);
        data.put("businessId",this.business.getId());
        data.put("appointmentTime",new SimpleDateFormat("yyyy-MM-dd").format(this.getAppointmentTime()));
        data.put("systemDictionaryId",this.systemDictionary.getId());
        data.put("contactName",this.contactName);
        data.put("contactTel",this.contactTel);
        data.put("info",this.info);
        return JSON.toJSONString(data);
    }


}