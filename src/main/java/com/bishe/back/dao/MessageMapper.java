package com.bishe.back.dao;

import com.bishe.back.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

//我的消息DAO
@Repository
public interface MessageMapper {
    //新增消息
    public int insert(Message message);

    //删除所有聊天记录
    public int deleteAll(Integer userId);

    //删除和某个好友的聊天记录
    public int delFriendRecord(Integer userId,Integer friendId);

    //查询所有聊天，聊天列表
    public List<Message> allRecord(Integer userId);

    //查询某个用户和其某个好友的聊天记录
    public List<Message> friendRecord(Integer userId,Integer friendId);
}