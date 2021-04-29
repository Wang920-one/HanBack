package com.bishe.back.dao;

import com.bishe.back.domain.Subscribe;
import com.bishe.back.domain.Subscribe;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//关注DAO
@Repository
public interface SubscribeMapper {

    //    增加
    public int insert(Subscribe subscribe);

    //    删除
    public int delete(Integer id);

    //    查询所有关注列表
    public List<Subscribe> allSubscribe();

    //查询某个用户的关注列表
    public List<Subscribe> listOfSubscribe(Integer subscribe);

    //查询某个用户的粉丝列表
    public List<Subscribe> listOfBeSubscribe(Integer beSubscribe);

    //查询某个用户是否关注了某个用户
    public int existSubscribe(@Param("subscribe") Integer subscribe,@Param("beSubscribe") Integer beSubscribe);

 }