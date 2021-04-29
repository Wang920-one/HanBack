package com.bishe.back.service;

import com.bishe.back.domain.Subscribe;
import com.bishe.back.domain.Subscribe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//评论service接口
public interface SubscribeService {
    //    增加
    public boolean insert(Subscribe subscribe);

    //    删除
    public boolean delete(Integer id);

    //    查询所有关注列表
    public List<Subscribe> allSubscribe();

    //查询某个用户的关注列表
    public List<Subscribe> listOfSubscribe(Integer subscribe);

    //查询某个用户的粉丝列表
    public List<Subscribe> listOfBeSubscribe(Integer beSubscribe);

    //查询某个用户是否关注了某个用户
    public boolean existSubscribe(@Param("subscribe") Integer subscribe,@Param("beSubscribe") Integer beSubscribe);

}
