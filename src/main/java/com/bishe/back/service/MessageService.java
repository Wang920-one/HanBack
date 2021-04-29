package com.bishe.back.service;

import com.bishe.back.domain.Message;

import java.util.List;

//我的消息接口
public interface MessageService {
    //新增消息
    public boolean insert(Message message);

    //删除所有聊天记录
    public boolean deleteAll(Integer userId);

    //查询某个用户和其某个好友的聊天记录
    public List<Message> friendRecord(Integer userId, Integer friendId);
}
