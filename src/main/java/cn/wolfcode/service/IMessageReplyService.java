package cn.wolfcode.service;

import cn.wolfcode.domain.MessageReply;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageReplyService {
    void insert(MessageReply messageReply);

    void deleteById(Long id);

    void update(MessageReply messageReply);

    List<MessageReply> selectById(Long id);

    List<MessageReply> selectAll();

    PageInfo<MessageReply> selectByList(QueryObject qo);
}
