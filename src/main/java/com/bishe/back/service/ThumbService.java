package com.bishe.back.service;

import com.bishe.back.domain.Thumb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//点赞service接口
public interface ThumbService {
    //    增加
    public boolean insert(Thumb thumb);

    //    删除
    public boolean delete(Integer id);

    //    查询所有点赞
    public List<Thumb> allThumb();

    //查询某个用户的点赞列表
    public List<Thumb> thumbOfUseId(Integer useId);

    //查询某个是否点赞了某个专栏
    public boolean existAtcId(@Param("useId") Integer useId, @Param("atcId") Integer atcId);

    //查询某个是否点赞了某个视频
    public boolean existVideoId(@Param("useId") Integer useId,@Param("videoId") Integer videoId);

}
