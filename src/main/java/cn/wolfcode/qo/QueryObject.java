package cn.wolfcode.qo;

import lombok.Data;

@Data
public class QueryObject {
    private int currentPage=1;
    private int pageSize=5;

}
