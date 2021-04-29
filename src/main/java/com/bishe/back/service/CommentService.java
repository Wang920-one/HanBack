package com.bishe.back.service;

import com.bishe.back.domain.Comment;

import java.util.List;

//评论service接口
public interface CommentService {
    //    增加
    public boolean insert(Comment comment);

    //    修改
    public boolean update(Comment comment);

    //    删除
    public boolean delete(Integer id);

    //    查询
    public Comment selectByPrimaryKey(Integer id);

    //    查询所有评论
    public List<Comment> allComment();

    //查询某个视频的所有评论
    public List<Comment> commentOfVideoId(Integer videoid);

    //查询某个专栏的所有评论
    public List<Comment> commentOfArticleId(Integer articleid);
}
