package com.bishe.back.service.impl;

import com.bishe.back.dao.MessageMapper;
import com.bishe.back.domain.Message;
import com.bishe.back.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//我的消息实现类
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    //新增消息
    @Override
    public boolean insert(Message message) {
        return messageMapper.insert(message)>0;
    }

    //删除所有聊天记录
    @Override
    public boolean deleteAll(Integer userId) {
        return messageMapper.deleteAll(userId)>0;
    }

    //删除和某个好友的聊天记录
    @Override
    public boolean delFriendRecord(Integer userId,Integer friendId) {
        return messageMapper.delFriendRecord(userId,friendId)>0;
    }

    //更新信息的状态
    @Override
    public boolean updateStatus(Message message) {
        return messageMapper.updateStatus(message)>0;
    }

    //查询某个好友的未读信息
    @Override
    public List<Message> allFriendUnRead(Integer senderId,Integer receiverId, Integer friendId) {
        return messageMapper.allFriendUnRead(senderId,receiverId,friendId);
    }

    //查询所有未读信息
    @Override
    public List<Message> allUnRead(Integer userId,Integer receiverId) {
        return messageMapper.allUnRead(userId,receiverId);
    }

    //查询所有聊天，聊天列表
    @Override
    public List<Message> allRecord(Integer userId) {
        return messageMapper.allRecord(userId);
    }

    //查询某个用户和其某个好友的聊天记录
    @Override
    public List<Message> friendRecord(Integer userId, Integer friendId) {
        return messageMapper.friendRecord(userId,friendId);
    }
}

