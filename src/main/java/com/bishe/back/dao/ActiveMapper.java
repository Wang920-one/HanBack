package com.bishe.back.dao;

import com.bishe.back.domain.Active;
import com.bishe.back.domain.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

//活动DAO
@Repository
public interface ActiveMapper {

    //    增加
    public int insert(Active active);

    //    修改
    public int update(Active active);

    //    删除
    public int delete(Integer acId);

    //    查询
    public Active selectByPrimaryKey(Integer acId);

    //    查询所有活动
    public List<Active> allActive();

    //根据名称模糊查询列表
    public List<Active> activeOfName(String acName);

    //根据形式模糊查询列表
    public List<Active> activeOfForm(String acForm);

    //    根据名称查询
    public Active getByActivename(String acName);

    //根据账号查询
//    public User getByUserAccount(String userAccount);
}