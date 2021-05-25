package com.bishe.back.service.impl;

import com.bishe.back.dao.ArticleMapper;
import com.bishe.back.domain.Article;
import com.bishe.back.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//稿件service实现类
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;//立刻使用，声明为private

    //增加
    @Override
    public boolean insert(Article article) {
        return articleMapper.insert(article)>0;
    }

    //更新
    @Override
    public boolean update(Article article) {
        return articleMapper.update(article)>0;
    }

    //删除
    @Override
    public boolean delete(Integer id) {
        return articleMapper.delete(id) >0;
    }

    //查询
    @Override
    public Article selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    //根据名称模糊查询列表
    @Override
    public List<Article> articleOfBioati(String bioati){
        return articleMapper.articleOfBioati(bioati);
    }

    //根据用户id查询
    @Override
    public List<Article> articleOfUseId(Integer useId){
        return articleMapper.articleOfUseId(useId);
    }

    //查询所有
    @Override
    public List<Article> allArticle() {
        return articleMapper.allArticle();
    }

    //查询所有后端
    @Override
    public List<Article> allArticles() {
        return articleMapper.allArticles();
    }

}

