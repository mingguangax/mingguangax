package cn.wolfcode.service.impl;

import cn.wolfcode.domain.MessageBoard;
import cn.wolfcode.mapper.MessageBoardMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IMessageBoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBoardServiceImpl implements IMessageBoardService {

    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public void insert(MessageBoard messageBoard) {
        messageBoardMapper.insert(messageBoard);
    }

    @Override
    public void deleteById(Long id) {
        messageBoardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MessageBoard messageBoard) {
        messageBoardMapper.updateByPrimaryKey(messageBoard);
    }

    @Override
    public MessageBoard selectById(Long id) {
        MessageBoard messageBoard = messageBoardMapper.selectByPrimaryKey(id);
        return messageBoard;
    }

    @Override
    public List<MessageBoard> selectAll() {
        List<MessageBoard> messageBoards = messageBoardMapper.selectAll();
        return messageBoards;
    }

    @Override
    public PageInfo<MessageBoard> selectByList(QueryObject qo,Long id) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<MessageBoard> messageBoards =messageBoardMapper.selectForList(qo,id);
        return new PageInfo<>(messageBoards);
    }
}
