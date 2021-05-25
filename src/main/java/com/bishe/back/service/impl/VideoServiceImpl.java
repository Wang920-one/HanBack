package com.bishe.back.service.impl;

import com.bishe.back.dao.VideoMapper;
import com.bishe.back.domain.Article;
import com.bishe.back.domain.Video;
import com.bishe.back.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//稿件service实现类
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;//立刻使用，声明为private

    //增加
    @Override
    public boolean insert(Video Video) {
        return videoMapper.insert(Video) > 0;
    }

    //更新
    @Override
    public boolean update(Video Video) {
        return videoMapper.update(Video) > 0;
    }

    //删除
    @Override
    public boolean delete(Integer id) {
        return videoMapper.delete(id) > 0;
    }

    //查询
    @Override
    public Video selectByPrimaryKey(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    //根据名称模糊查询列表
    @Override
    public List<Video> videoOfTitle(String videoTitle) {
        return videoMapper.videoOfTitle(videoTitle);
    }

    //根据类型模糊查询列表
    @Override
    public List<Video> videoOfType(String videoType) {
        return videoMapper.videoOfType(videoType);
    }

    //根据用户id查询
    @Override
    public List<Video> videoOfUseId(Integer useId) {
        return videoMapper.videoOfUseId(useId);
    }

    //查询所有
    @Override
    public List<Video> allVideo() {
        return videoMapper.allVideo();
    }

    //查询所有后端
    @Override
    public List<Video> allVideos() {
        return videoMapper.allVideos();
    }

    //降序浏览量查询所有
    @Override
    public List<Video> videoOfBrowse() {
        return videoMapper.videoOfBrowse();
    }

    //    降序点赞量查询所有，热门榜
    @Override
    public List<Video> videoOfThumse() {
        return videoMapper.videoOfThumse();
    }

    //    降序收藏量查询所有，推荐榜
    @Override
    public List<Video> videoOfCollect() {
        return videoMapper.videoOfCollect();
    }
}

