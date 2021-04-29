package com.bishe.back.service.impl;

import com.bishe.back.dao.UserMapper;
import com.bishe.back.domain.Active;
import com.bishe.back.domain.User;
import com.bishe.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户service实现类
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//立刻使用，声明为private

    //用户登录
    @Override
    public boolean verifyPassword(String userAccount, String userPassword) {
        return userMapper.verifyPassword(userAccount,userPassword)>0;
    }

    //增加
    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    //更新
    @Override
    public boolean update(User user) {
        return userMapper.update(user)>0;
    }

    //删除
    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id) >0;
    }

    //查询
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //查询所有用户
    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    //根据用户名查询用户
    @Override
    public User getByUsername(String userName) {
        return userMapper.getByUsername(userName);
    }

    //根据名称模糊查询列表
    @Override
    public List<User> userOfName(String userName){
        return userMapper.userOfName(userName);
    }

    //根据账号查询
    @Override
    public User getByUserAccount(String userAccount){
        return userMapper.getByUserAccount(userAccount);
    }
}

