package com.bishe.back.service;

import com.bishe.back.domain.Active;
import com.bishe.back.domain.Article;
import com.bishe.back.domain.Shop;

import java.util.List;

//活动service接口
public interface ActiveService {
    //    增加
    public boolean insert(Active active);

    //    修改
    public boolean update(Active active);

    //    删除
    public boolean delete(Integer acId);

    //    查询
    public Active selectByPrimaryKey(Integer acId);

    //    查询所有活动
    public List<Active> allActive();

    //    根据名称查询列表
    public Active getByActivename(String acName);

    //     根据名称模糊查询列表
    public List<Active> activeOfName(String acName);

    //     根据名称模糊查询列表
    public List<Active> activeOfForm(String acForm);

    //  根据账号查询
//    public Article getByUserAccount(String userAccount);
}
