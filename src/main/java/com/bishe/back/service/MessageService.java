package com.bishe.back.service;

import com.bishe.back.domain.Message;

import java.util.List;

//我的消息接口
public interface MessageService {
    //新增消息
    public boolean insert(Message message);

    //删除所有聊天记录
    public boolean deleteAll(Integer userId);

    //删除和某个好友的聊天记录
    public boolean delFriendRecord(Integer userId,Integer friendId);

    //更新信息的状态
    public boolean updateStatus(Message message);

    //查询某个好友的未读信息
    public List<Message> allFriendUnRead(Integer senderId,Integer receiverId,Integer friendId);

    //查询所有未读信息
    public List<Message> allUnRead(Integer userId);

    //查询所有聊天，聊天列表
    public List<Message> allRecord(Integer userId);

    //查询某个用户和其某个好友的聊天记录
    public List<Message> friendRecord(Integer userId, Integer friendId);
}
