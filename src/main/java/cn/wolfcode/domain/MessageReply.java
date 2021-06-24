package cn.wolfcode.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MessageReply {
    /** */
    private Long id;
    /** 回复内容*/
    private Employee employee;

    private String content;
    /** 所属留言*/
    private Long messageId;
    /** 回复人*/
    private Long replyUserId;
    /** 回复时间*/
    private Date createTime;
}