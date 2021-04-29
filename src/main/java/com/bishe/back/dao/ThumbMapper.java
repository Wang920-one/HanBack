package com.bishe.back.dao;

import com.bishe.back.domain.Thumb;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//收藏DAO
@Repository
public interface ThumbMapper {

    //    增加
    public int insert(Thumb collect);

    //    删除
    public int delete(Integer id);

    //    查询所有收藏
    public List<Thumb> allThumb();

    //查询某个用户的点赞列表
    public List<Thumb> thumbOfUseId(Integer useId);

    //查询某个是否点赞了某个专栏
    public int existAtcId(@Param("useId") Integer useId,@Param("atcId") Integer atcId);

    //查询某个是否点赞了某个视频
    public int existVideoId(@Param("useId") Integer useId,@Param("videoId") Integer videoId);

 }