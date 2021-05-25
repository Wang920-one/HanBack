package com.bishe.back.service;

import com.bishe.back.domain.Article;
import com.bishe.back.domain.Video;

import java.util.List;

//稿件service接口
public interface VideoService {
    //    增加
    public boolean insert(Video video);

    //    修改
    public boolean update(Video video);

    //    删除
    public boolean delete(Integer id);

    //    查询
    public Video selectByPrimaryKey(Integer id);

    //    查询所有稿件
    public List<Video> allVideo();

    //    查询所有稿件后端
    public List<Video> allVideos();

    //     根据名称模糊查询列表
    public List<Video> videoOfTitle(String videoTitle);

    //根据类型模糊查询列表
    public List<Video> videoOfType(String videoType);

    //    根据用户id查询列表
    public List<Video> videoOfUseId(Integer useId);

    //降序浏览量查询所有
    public List<Video> videoOfBrowse();

    //降序收藏量查询所有，推荐榜
    public List<Video> videoOfCollect();

    //降序点赞量查询所有，热门榜
    public List<Video> videoOfThumse();

    //根据账号查询
//    public Video getByUserAccount(String userAccount);
}
