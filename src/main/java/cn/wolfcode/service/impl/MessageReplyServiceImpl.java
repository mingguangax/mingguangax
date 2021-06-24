package cn.wolfcode.service.impl;

import cn.wolfcode.domain.MessageReply;
import cn.wolfcode.mapper.MessageReplyMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IMessageReplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageReplyServiceImpl implements IMessageReplyService {

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Override
    public void insert(MessageReply messageReply) {
        messageReplyMapper.insert(messageReply);
    }

    @Override
    public void deleteById(Long id) {
        messageReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MessageReply messageReply) {
        messageReplyMapper.updateByPrimaryKey(messageReply);
    }

    @Override
    public List<MessageReply> selectById(Long id) {
        return messageReplyMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<MessageReply> selectAll() {
        List<MessageReply> messageReplys = messageReplyMapper.selectAll();
        return messageReplys;
    }

    @Override
    public PageInfo<MessageReply> selectByList(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        List<MessageReply> messageReplys =messageReplyMapper.selectForList(qo);
        return new PageInfo<>(messageReplys);
    }
}
