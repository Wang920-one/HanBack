package com.bishe.back.dao;

import com.bishe.back.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

//稿件DAO
@Repository
public interface VideoMapper {

    //    增加
    public int insert(Video video);

    //    修改
    public int update(Video video);

    //    删除
    public int delete(Integer id);

    //    查询
    public Video selectByPrimaryKey(Integer id);

    //    查询所有用户
    public List<Video> allVideo();

    //     根据名称模糊查询列表
    public List<Video> videoOfTitle(String videoTitle);

    //根据类型模糊查询列表
    public List<Video> videoOfType(String videoType);

    //    根据用户id查询
    public List<Video> videoOfUseId(Integer useId);

    //降序浏览量查询所有
    public List<Video> videoOfBrowse();

    //降序收藏量查询所有，推荐榜
    public List<Video> videoOfCollect();

    //降序点赞量查询所有，热门榜
    public List<Video> videoOfThumse();
}