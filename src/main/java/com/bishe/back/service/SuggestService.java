package com.bishe.back.service;

import com.bishe.back.domain.Suggest;

import java.util.List;

//投诉建议service接口
public interface SuggestService {
    //    增加
    public boolean insert(Suggest suggest);

    //    修改
    public boolean update(Suggest suggest);

    //    删除
    public boolean delete(Integer sugId);

    //    查询
    public Suggest selectByPrimaryKey(Integer sugId);

    //    查询所有投诉建议
    public List<Suggest> allSuggest();

    //    根据用户姓名查询列表
//    public Article getByUsername(String userName);

    //根据账号查询
//    public Article getByUserAccount(String userAccount);
}
