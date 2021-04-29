package com.bishe.back.service.impl;

import com.bishe.back.dao.ActiveMapper;
import com.bishe.back.domain.Active;
import com.bishe.back.domain.Shop;
import com.bishe.back.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//活动service实现类
@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveMapper activeMapper;//立刻使用，声明为private

    //增加
    @Override
    public boolean insert(Active active) {
        return activeMapper.insert(active)>0;
    }

    //更新
    @Override
    public boolean update(Active active) {
        return activeMapper.update(active)>0;
    }

    //删除
    @Override
    public boolean delete(Integer acId) {
        return activeMapper.delete(acId) >0;
    }

    //查询
    @Override
    public Active selectByPrimaryKey(Integer acId) {
        return activeMapper.selectByPrimaryKey(acId);
    }

    //查询所有活动
    @Override
    public List<Active> allActive() {
        return activeMapper.allActive();
    }

    //根据名称模糊查询列表
    @Override
    public List<Active> activeOfName(String acName){
        return activeMapper.activeOfName(acName);
    }

    //根据形式模糊查询列表
    @Override
    public List<Active> activeOfForm(String acForm){
        return activeMapper.activeOfForm(acForm);
    }

    //根据用户名查询用户
    @Override
    public Active getByActivename(String acName) {
        return activeMapper.getByActivename(acName);
    }
//
//    //根据账号查询
//    @Override
//    public User getByUserAccount(String userAccount){
//        return articleMapper.getByUserAccount(userAccount);
//    }
}

