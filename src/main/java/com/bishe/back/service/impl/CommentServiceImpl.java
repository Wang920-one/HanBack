package com.bishe.back.service.impl;

import com.bishe.back.dao.CommentMapper;
import com.bishe.back.domain.Comment;
import com.bishe.back.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//评论实现类
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //增加
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    //修改
    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    //删除
    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    //根据主键查询
    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    //查询所有评论
    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    //查询某个视频的评论
    @Override
    public List<Comment> commentOfVideoId(Integer videoid) {
        return commentMapper.commentOfVideoId(videoid);
    }

    //查询某个专栏的评论
    @Override
    public List<Comment> commentOfArticleId(Integer articleid) {
        return commentMapper.commentOfArticleId(articleid);
    }
}
