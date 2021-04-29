package com.bishe.back.service;

import com.bishe.back.domain.Shop;

import java.util.List;

//店铺service接口
public interface ShopService {
    //    增加
    public boolean insert(Shop shop);

    //    修改
    public boolean update(Shop shop);

    //    删除
    public boolean delete(Integer shopId);

    //    查询
    public Shop selectByPrimaryKey(Integer shopId);

    //    查询所有稿件
    public List<Shop> allShop();

    //根据店铺名称模糊查询列表
    public List<Shop> shopOfName(String shopName);

//    根据店铺名字查询列表
    public Shop getByShopname(String shopName);

    //降序粉丝量查询所有，热门榜
    public List<Shop> shopOfFans();

    //降序认证时间查询所有，资历榜
    public List<Shop> shopOfYear();
}
