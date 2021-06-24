package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private int currentPage;

    private int pageSize;

    private int totalCount;

    private int totalPage;

    private int prevPage;

    private int nextPage;

    private List<T> data;

    public PageResult(int currentPage,int pageSize,int count,List<T> data){
        this.data = data;
        this.pageSize=pageSize;
        this.currentPage=currentPage;
        this.totalCount=count;

        this.totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
        this.prevPage=currentPage>1?currentPage-1:1;
        this.nextPage=currentPage<totalPage?currentPage+1:totalPage;
    }
}
