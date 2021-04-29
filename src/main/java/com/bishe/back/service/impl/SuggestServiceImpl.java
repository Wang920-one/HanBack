package com.bishe.back.service.impl;

import com.bishe.back.dao.SuggestMapper;
import com.bishe.back.domain.Suggest;
import com.bishe.back.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//投诉建议service实现类
@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SuggestMapper suggestMapper;//立刻使用，声明为private

    //增加
    @Override
    public boolean insert(Suggest suggest) {
        return suggestMapper.insert(suggest)>0;
    }

    //更新
    @Override
    public boolean update(Suggest suggest) {
        return suggestMapper.update(suggest)>0;
    }

    //删除
    @Override
    public boolean delete(Integer sugId) {
        return suggestMapper.delete(sugId) >0;
    }

    //查询
    @Override
    public Suggest selectByPrimaryKey(Integer sugId) {
        return suggestMapper.selectByPrimaryKey(sugId);
    }

    //查询所有投诉建议
    @Override
    public List<Suggest> allSuggest() {
        return suggestMapper.allSuggest();
    }

//    //根据用户名查询用户
//    @Override
//    public User getByUsername(String userName) {
//        return articleMapper.getByUsername(userName);
//    }
//
//    //根据账号查询
//    @Override
//    public User getByUserAccount(String userAccount){
//        return articleMapper.getByUserAccount(userAccount);
//    }
}

