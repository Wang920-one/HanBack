package com.bishe.back.dao;

import com.bishe.back.domain.Shop;
import com.bishe.back.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

//店铺DAO
@Repository
public interface ShopMapper {

    //    增加
    public int insert(Shop shop);

    //    修改
    public int update(Shop shop);

    //    删除
    public int delete(Integer shopId);

    //    查询
    public Shop selectByPrimaryKey(Integer shopId);

    //    查询所有店铺
    public List<Shop> allShop();

    //根据店铺名称模糊查询列表
    public List<Shop> shopOfName(String shopName);

    //    根据店铺名查询
    public Shop getByShopname(String shopName);

    //降序粉丝量查询所有，热门榜
    public List<Shop> shopOfFans();

    //降序认证时间查询所有，资历榜
    public List<Shop> shopOfYear();
}