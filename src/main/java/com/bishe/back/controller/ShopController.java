package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Shop;
import com.bishe.back.service.ShopService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    //添加店铺
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addShop(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String shopName = request.getParameter("shopName").trim();      //店铺名称
        String shopHttp = request.getParameter("shopHttp").trim();        //店铺地址
        String pic = request.getParameter("pic").trim();        //店铺图像
        String shopYear = request.getParameter("shopYear").trim();    //店铺认证时间
        String shopFans = request.getParameter("shopFans").trim();  //店铺粉丝
        //把日期转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = dateFormat.parse(shopYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到店铺的对象中
        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setShopHttp(shopHttp);
        shop.setShopYear(date1);
        shop.setShopFans(Integer.parseInt(shopFans));
        shop.setPic(pic);
        boolean flag = shopService.insert(shop);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    //    修改店铺信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateShop(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String shopId = request.getParameter("shopId").trim();          //主键
        String shopName = request.getParameter("shopName").trim();      //店铺名称
        String shopHttp = request.getParameter("shopHttp").trim();        //店铺地址
        String shopYear = request.getParameter("shopYear").trim();    //店铺认证时间
        String shopFans = request.getParameter("shopFans").trim();  //店铺粉丝
        //把日期转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = dateFormat.parse(shopYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到店铺的对象中
        Shop shop = new Shop();
        shop.setShopId(Integer.parseInt(shopId));
        shop.setShopName(shopName);
        shop.setShopHttp(shopHttp);
        shop.setShopYear(date1);
        shop.setShopFans(Integer.parseInt(shopFans));
        boolean flag = shopService.update(shop);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    //    删除店铺
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteShop(HttpServletRequest request) {
        String shopId = request.getParameter("shopId").trim();          //主键
        boolean flag = shopService.delete(Integer.parseInt(shopId));
        return flag;
    }

    //    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String shopId = request.getParameter("shopId").trim();          //主键
        return shopService.selectByPrimaryKey(Integer.parseInt(shopId));
    }

    //根据店铺名称模糊查询列表
    @RequestMapping(value = "/shopOfName", method = RequestMethod.GET)
    public Object shopOfName(HttpServletRequest request) {
        String shopName=request.getParameter("shopName").trim();
        return shopService.shopOfName("%"+shopName+"%");
    }

    //    查询所有店铺
    @RequestMapping(value = "/allShop", method = RequestMethod.GET)
    public Object allShop(HttpServletRequest request) {
        return shopService.allShop();
    }

    //    降序粉丝量查询所有，热门榜
    @RequestMapping(value = "/shopOfFans", method = RequestMethod.GET)
    public Object shopOfFans(HttpServletRequest request) {
        return shopService.shopOfFans();
    }

    //    降序认证时间查询所有，资历榜
    @RequestMapping(value = "/shopOfYear", method = RequestMethod.GET)
    public Object shopOfYear(HttpServletRequest request) {
        return shopService.shopOfYear();
    }

    //上传店铺图片
    @RequestMapping(value = "/updateShopImage", method = RequestMethod.POST)
    public Object updateShopImage(@RequestParam("file") MultipartFile shopFile,@RequestParam("shopId")int shopId){
        JSONObject jsonObject=new JSONObject();
        if(shopFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName =System.currentTimeMillis()+shopFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "shopPic";
        //如果文件路径不存在，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件夹地址
        String storeShopPath="/img/shopPic/"+fileName;
        try {
            shopFile.transferTo(dest);
            Shop shop =new Shop();
            shop.setShopId(shopId);
            shop.setPic(storeShopPath);
            boolean flag=shopService.update(shop);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeShopPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }
        finally {
            return jsonObject;
        }
    }
}
