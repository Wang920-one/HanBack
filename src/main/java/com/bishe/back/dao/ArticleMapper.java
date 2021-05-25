package com.bishe.back.dao;

import com.bishe.back.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

//稿件DAO
@Repository
public interface ArticleMapper {

    //    增加
    public int insert(Article article);

    //    修改
    public int update(Article article);

    //    删除
    public int delete(Integer id);

    //    查询
    public Article selectByPrimaryKey(Integer id);

    //    查询所有稿件
    public List<Article> allArticle();

    //    查询所有稿件后端
    public List<Article> allArticles();

    //     根据名称模糊查询列表
    public List<Article> articleOfBioati(String bioati);

    //    根据用户id查询
    public List<Article> articleOfUseId(Integer useId);

    //根据账号查询
//    public User getByUserAccount(String userAccount);
}