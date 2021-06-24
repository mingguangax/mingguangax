package cn.wolfcode.service;

import cn.wolfcode.domain.MessageBoard;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageBoardService {
    void insert(MessageBoard messageBoard);

    void deleteById(Long id);

    void update(MessageBoard messageBoard);

    MessageBoard selectById(Long id);

    List<MessageBoard> selectAll();

    PageInfo<MessageBoard> selectByList(QueryObject qo,Long id);
}
