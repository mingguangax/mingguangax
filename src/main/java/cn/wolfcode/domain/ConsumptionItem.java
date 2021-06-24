package cn.wolfcode.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data

public class ConsumptionItem {
    /** 主键*/
    private Long id;

    /** 业务大类*/
    private SystemDictionary category;

    /** 业务小类*/
    private SystemDictionary categoryItem;

    /** 结算类型*/
    private SystemDictionary payType;

    /** 应收金额*/
    private BigDecimal amount;

    /** 实收金额*/
    private BigDecimal payAmount;

    /** 优惠金额*/
    private BigDecimal discountAmount;

    /** 创建人*/
    private Long createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 结算单流水号*/
    private String cno;



}