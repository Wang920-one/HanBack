package com.bishe.back.dao;

import com.bishe.back.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

//评论DAO
@Repository
public interface CommentMapper {

    //    增加
    public int insert(Comment comment);

    //    修改
    public int update(Comment comment);

    //    删除
    public int delete(Integer id);

    //    查询
    public Comment selectByPrimaryKey(Integer id);

    //    查询所有评论
    public List<Comment> allComment();

    //查询某个视频的所有评论
    public List<Comment> commentOfVideoId(Integer videoid);

    //查询某个专栏的所有评论
    public List<Comment> commentOfArticleId(Integer articleid);

 }