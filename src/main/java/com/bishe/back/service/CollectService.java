package com.bishe.back.service;

import com.bishe.back.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//评论service接口
public interface CollectService {
    //    增加
    public boolean insert(Collect collect);

    //    删除
    public boolean delete(Integer id);

    //    查询所有收藏
    public List<Collect> allCollect();

    //查询某个用户的收藏列表
    public List<Collect> collectOfUseId(Integer useId);

    //查询某个是否收藏了某个专栏
    public boolean existAtcId(@Param("useId") Integer useId, @Param("atcId") Integer atcId);

    //查询某个是否收藏了某个视频
    public boolean existVideoId(@Param("useId") Integer useId,@Param("videoId") Integer videoId);

}
