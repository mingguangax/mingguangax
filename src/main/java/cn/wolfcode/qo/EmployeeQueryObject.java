package cn.wolfcode.qo;

import lombok.Data;

/**
 * 封装员工分页过滤查询的参数
 */
@Data
public class EmployeeQueryObject extends QueryObject {

    private  String keyword;
    private  Integer deptId;

}
