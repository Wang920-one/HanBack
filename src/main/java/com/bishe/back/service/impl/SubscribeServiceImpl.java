package com.bishe.back.service.impl;

import com.bishe.back.dao.CollectMapper;
import com.bishe.back.dao.SubscribeMapper;
import com.bishe.back.domain.Collect;
import com.bishe.back.domain.Subscribe;
import com.bishe.back.service.CollectService;
import com.bishe.back.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//评论实现类
@Service
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    private SubscribeMapper subscribeMapper;

    @Override
    public boolean insert(Subscribe subscribe) {
        return subscribeMapper.insert(subscribe)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return subscribeMapper.delete(id)>0;
    }

    @Override
    public List<Subscribe> allSubscribe() {
        return subscribeMapper.allSubscribe();
    }

    @Override
    public List<Subscribe> listOfSubscribe(Integer subscribe) {
        return subscribeMapper.listOfSubscribe(subscribe);
    }

    @Override
    public List<Subscribe> listOfBeSubscribe(Integer beSubscribe) {
        return subscribeMapper.listOfBeSubscribe(beSubscribe);
    }

    @Override
    public boolean existSubscribe(Integer subscribe, Integer beSubscribe) {
        return subscribeMapper.existSubscribe(subscribe,beSubscribe)>0;
    }
}
