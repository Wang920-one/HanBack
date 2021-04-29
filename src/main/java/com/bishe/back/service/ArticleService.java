package com.bishe.back.service;

import com.bishe.back.domain.Active;
import com.bishe.back.domain.Article;

import java.util.List;

//稿件service接口
public interface ArticleService {
    //    增加
    public boolean insert(Article article);

    //    修改
    public boolean update(Article article);

    //    删除
    public boolean delete(Integer id);

    //    查询
    public Article selectByPrimaryKey(Integer id);

    //    查询所有稿件
    public List<Article> allArticle();

    //     根据名称模糊查询列表
    public List<Article> articleOfBioati(String bioati);

    //    根据用户姓名查询列表
    public List<Article> articleOfUseId(Integer useId);

    //根据账号查询
//    public Article getByUserAccount(String userAccount);
}
