package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Department {
    /** */
    private Long id;

    /** */
    private String name;

    /** */
    private String sn;

public String toJson(){
    return JSON.toJSONString(this);//当谁调用方法时,就会把当前的所有对象转换成JSON字符串;
}
}