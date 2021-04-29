package com.bishe.back.service.impl;

import com.bishe.back.dao.ArticleMapper;
import com.bishe.back.dao.ShopMapper;
import com.bishe.back.domain.Article;
import com.bishe.back.domain.Shop;
import com.bishe.back.service.ArticleService;
import com.bishe.back.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//店铺service实现类
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;//立刻使用，声明为private

    //增加
    @Override
    public boolean insert(Shop shop) {
        return shopMapper.insert(shop)>0;
    }

    //更新
    @Override
    public boolean update(Shop shop) {
        return shopMapper.update(shop)>0;
    }

    //删除
    @Override
    public boolean delete(Integer shopId) {
        return shopMapper.delete(shopId) >0;
    }

    //查询
    @Override
    public Shop selectByPrimaryKey(Integer shopId) {
        return shopMapper.selectByPrimaryKey(shopId);
    }

    //查询所有用户
    @Override
    public List<Shop> allShop() {
        return shopMapper.allShop();
    }


    //根据店铺名称模糊查询列表
    @Override
    public List<Shop> shopOfName(String shopName){
        return shopMapper.shopOfName(shopName);
    }

    //根据店铺名查询店铺
    @Override
    public Shop getByShopname(String shopName) {
        return shopMapper.getByShopname(shopName);
    }

    //降序粉丝量查询所有，热门榜
    public List<Shop> shopOfFans(){
        return shopMapper.shopOfFans();
    }

    //降序认证时间查询所有，资历榜
    public List<Shop> shopOfYear(){
        return shopMapper.shopOfYear();
    }
}

