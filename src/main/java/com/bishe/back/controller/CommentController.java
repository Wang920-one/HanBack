package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Comment;
import com.bishe.back.domain.User;
import com.bishe.back.service.CommentService;
import com.bishe.back.service.UserService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //添加评论
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId");     //用户id
        String videoid = request.getParameter("videoid");     //视频id
        String articleid = request.getParameter("articleid");     //专栏id
//        String parentId = request.getParameter("parentId");
        String type = request.getParameter("type");     //评论类型（0视频1专栏）
        String comtContent = request.getParameter("comtContent").trim();          //评论内容

        //保存到前端用户的对象中
        Comment comment = new Comment();
        comment.setUseId(Integer.parseInt(useId));
//        comment.setParentId(Integer.parseInt(parentId));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setVideoid(Integer.parseInt(videoid));
        } else {
            comment.setArticleid(Integer.parseInt(articleid));
        }
        comment.setComtContent(comtContent);
        boolean flag = commentService.insert(comment);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评论失败");
        return jsonObject;
    }

    //    删除评论
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }

    //    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        return commentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //    查询所有评论
    @RequestMapping(value = "/allComment", method = RequestMethod.GET)
    public Object allComment(HttpServletRequest request) {
        return commentService.allComment();
    }

    //查询某个视频的评论
    @RequestMapping(value = "/commentOfVideoId", method = RequestMethod.GET)
    public Object commentOfVideoId(HttpServletRequest request) {
        String videoid = request.getParameter("videoid");
        return commentService.commentOfVideoId(Integer.parseInt(videoid));
    }

    //查询某个专栏的评论
    @RequestMapping(value = "/commentOfArticleId", method = RequestMethod.GET)
    public Object commentOfArticleId(HttpServletRequest request) {
        String articleid = request.getParameter("articleid");
        return commentService.commentOfArticleId(Integer.parseInt(articleid));
    }

    //给某个评论点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();      //主键
        String up = request.getParameter("up").trim();          //评论点赞数

        //保存到前端用户的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.update(comment);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }
}
