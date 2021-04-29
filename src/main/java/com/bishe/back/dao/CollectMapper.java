package com.bishe.back.dao;

import com.bishe.back.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//收藏DAO
@Repository
public interface CollectMapper {

    //    增加
    public int insert(Collect collect);

    //    删除
    public int delete(Integer id);

    //    查询所有收藏
    public List<Collect> allCollect();

    //查询某个用户的收藏列表
    public List<Collect> collectOfUseId(Integer useId);

    //查询某个是否收藏了某个专栏
    public int existAtcId(@Param("useId") Integer useId,@Param("atcId") Integer atcId);

    //查询某个是否收藏了某个视频
    public int existVideoId(@Param("useId") Integer useId,@Param("videoId") Integer videoId);

 }