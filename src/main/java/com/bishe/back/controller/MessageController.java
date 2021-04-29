package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Message;
import com.bishe.back.service.ManageService;
import com.bishe.back.service.MessageService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //添加私信
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addMessage(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");     //发送者Id，非真实
        String friendId = request.getParameter("friendId");     //接收者Id，非真实
        String senderId = request.getParameter("senderId");     //发送者Id，真实
        String receiverId = request.getParameter("receiverId");     //接收者Id，真实
//        String status = request.getParameter("status");     //消息状态,1未读，2已读,3删除
        String content = request.getParameter("content");   //私信内容

        //保存到前端用户的对象中
        Message message = new Message();
        message.setUserId(Integer.parseInt(userId));
        message.setFriendId(Integer.parseInt(friendId));
        message.setSenderId(Integer.parseInt(senderId));
        message.setReceiverId(Integer.parseInt(receiverId));
        message.setContent(content);
        boolean flag = messageService.insert(message);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "信息发送成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "信息发送失败");
        return jsonObject;
    }

    //    删除某个好友的聊天记录
    @RequestMapping(value = "/delFriendRecord", method = RequestMethod.GET)
    public Object delFriendRecord(HttpServletRequest request) {
        String userId = request.getParameter("userId").trim();
        String friendId = request.getParameter("friendId").trim();
        boolean flag = messageService.delFriendRecord(Integer.parseInt(userId),Integer.parseInt(friendId));
        return flag;
    }

    //    查询所有聊天，聊天列表
    @RequestMapping(value = "/allRecord", method = RequestMethod.GET)
    public Object allRecord(HttpServletRequest request) {
        String userId = request.getParameter("userId").trim();
        return messageService.allRecord(Integer.parseInt(userId));
    }

    //查询某个用户和其某个好友的聊天记录
    @RequestMapping(value = "/friendRecord", method = RequestMethod.GET)
    public Object friendRecord(HttpServletRequest request) {
        String userId = request.getParameter("userId").trim();
        String friendId = request.getParameter("friendId").trim();
        return messageService.friendRecord(Integer.parseInt(userId),Integer.parseInt(friendId));
    }

}
