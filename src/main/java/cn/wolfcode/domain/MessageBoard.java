package cn.wolfcode.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MessageBoard {
    /** */
    private Long id;
    /** 昵称*/
    private String nickname;
    /** 留言内容*/
    private String content;
    /** 留言时间*/
    private Date createTime;

    private String fatherName;

    /** 预约业务*/
    private SystemDictionary systemDictionary;

    /** 回复状态(/已回复)*/
    private Boolean replyStatus=false;

    private MessageReply messageReply;
}