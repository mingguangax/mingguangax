package cn.wolfcode.web.controller;

import cn.wolfcode.domain.MessageBoard;
import cn.wolfcode.domain.MessageReply;
import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IMessageBoardService;
import cn.wolfcode.service.IMessageReplyService;
import cn.wolfcode.service.ISystemDictionaryService;
import cn.wolfcode.util.RequirePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/messageBoard")
public class MessageBoardController {
    private IMessageBoardService messageBoardService;

    @Autowired
    public void setMessageBoardService(IMessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @Autowired
    private IMessageReplyService messageReplyService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    //messageBoard/list"
    //处理查询所有留言板的请求
    @RequestMapping("/list")
    public String list(Model model , QueryObject qo) {
        PageInfo<MessageBoard> pageInfo = messageBoardService.selectByList(qo,null);
        model.addAttribute("pageInfo",pageInfo);
        List<SystemDictionary> systemDictionaries =
                systemDictionaryService.quertItemById(1L);
        model.addAttribute("systemDictionary",systemDictionaries);
        return "messageBoard/list";
    }

    @RequestMapping("classItem")
    @ResponseBody
    public List<SystemDictionary> classItem(Long id){
        return systemDictionaryService.selectById(id);
    }


    @RequestMapping("/insert")
    public String insert(MessageBoard messageBoard){
        Date now = new Date();
        messageBoard.setCreateTime(now);
        //System.out.println(messageBoard);
        messageBoardService.insert(messageBoard);
        return "redirect:/messageBoard/list";
    }

    //查询一个
    //把原来信息信息查出来
    //把对应的回复信息也查出来
    @RequestMapping("/selectOne")
    public String listForOne(Model model , QueryObject qo,Long id) {

        PageInfo<MessageBoard> pageInfo = messageBoardService.selectByList(qo,id);
        model.addAttribute("pageInfo",pageInfo);
        for (MessageBoard messageBoard : pageInfo.getList()) {
            Long messageId = messageBoard.getId();
            model.addAttribute("messageId",messageId);
        }
        //查询回复表
        List<MessageReply> messageReplies = messageReplyService.selectById(id);
        int size = messageReplies.size();
        model.addAttribute("refuse",messageReplies);
        model.addAttribute("refuseSize",size);
        return "messageBoard/detail";
    }



    //保存&修改
    @RequestMapping("/saveOrUpdate")
    @RequirePermission(name = "留言板编辑" ,expression = "messageBoard:saveOrUpdate")
    public  String saveOrUpdate(MessageBoard messageBoard){
        if(messageBoard.getId() == null){
            messageBoardService.insert(messageBoard);
        }else {
            messageBoardService.update(messageBoard);
        }
        return "redirect:/messageBoard/list";
    }

//    @RequestMapping("/input")
//    public String input(){
//        //页面跳转
//        return "messageBoard/"
//    }
}
