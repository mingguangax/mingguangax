package cn.wolfcode.mapper;

import cn.wolfcode.domain.MessageBoard;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageBoardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageBoard record);

    MessageBoard selectByPrimaryKey(Long id);

    List<MessageBoard> selectAll();

    int updateByPrimaryKey(MessageBoard record);

    List<MessageBoard> selectForList(QueryObject qo, @Param("id") Long id);
}