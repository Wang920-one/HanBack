package com.bishe.back.service.impl;

import com.bishe.back.dao.CollectMapper;
import com.bishe.back.domain.Collect;
import com.bishe.back.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//评论实现类
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    //查询所有评论
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    @Override
    public List<Collect> collectOfUseId(Integer useId) {
        return collectMapper.collectOfUseId(useId);
    }

    @Override
    public boolean existAtcId(Integer useId, Integer atcId) {
        return collectMapper.existAtcId(useId,atcId)>0;
    }

    @Override
    public boolean existVideoId(Integer useId, Integer videoId) {
        return collectMapper.existVideoId(useId,videoId)>0;
    }

}
