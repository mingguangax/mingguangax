package cn.wolfcode.mapper;

import cn.wolfcode.domain.MessageReply;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageReply record);

    List<MessageReply> selectByPrimaryKey(Long id);

    List<MessageReply> selectAll();

    int updateByPrimaryKey(MessageReply record);

    List<MessageReply> selectForList(QueryObject qo);
}