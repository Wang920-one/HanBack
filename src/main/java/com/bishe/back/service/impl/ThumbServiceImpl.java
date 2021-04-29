package com.bishe.back.service.impl;

import com.bishe.back.dao.ThumbMapper;
import com.bishe.back.domain.Thumb;
import com.bishe.back.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//点赞实现类
@Service
public class ThumbServiceImpl implements ThumbService {
    @Autowired
    private ThumbMapper thumbMapper;

    @Override
    public boolean insert(Thumb collect) {
        return thumbMapper.insert(collect)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return thumbMapper.delete(id)>0;
    }

    //查询所有点赞
    @Override
    public List<Thumb> allThumb() {
        return thumbMapper.allThumb();
    }

    @Override
    public List<Thumb> thumbOfUseId(Integer useId) {
        return thumbMapper.thumbOfUseId(useId);
    }

    @Override
    public boolean existAtcId(Integer useId, Integer atcId) {
        return thumbMapper.existAtcId(useId,atcId)>0;
    }

    @Override
    public boolean existVideoId(Integer useId, Integer videoId) {
        return thumbMapper.existVideoId(useId,videoId)>0;
    }

}
