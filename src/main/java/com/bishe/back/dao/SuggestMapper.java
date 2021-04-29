package com.bishe.back.dao;

import com.bishe.back.domain.Suggest;
import org.springframework.stereotype.Repository;

import java.util.List;

//投诉建议DAO
@Repository
public interface SuggestMapper {

    //    增加
    public int insert(Suggest article);

    //    修改
    public int update(Suggest article);

    //    删除
    public int delete(Integer sugId);

    //    查询
    public Suggest selectByPrimaryKey(Integer sugId);

    //    查询所有投诉建议
    public List<Suggest> allSuggest();

    //    根据姓名查询
//    public User getByUsername(String userName);

    //根据账号查询
//    public User getByUserAccount(String userAccount);
}