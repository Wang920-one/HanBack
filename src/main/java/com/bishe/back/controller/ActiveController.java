package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Active;
import com.bishe.back.domain.Shop;
import com.bishe.back.service.ActiveService;
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
@RequestMapping("/active")
public class ActiveController {
    @Autowired
    private ActiveService activeService;

    //添加
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addActive(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String acName = request.getParameter("acName").trim();      //活动名称
        String acAddress = request.getParameter("acAddress").trim();        //活动地址
        String acForm = request.getParameter("acForm").trim();        //活动形式
        String acTime = request.getParameter("acTime").trim();    //活动时间
        String acSponsor = request.getParameter("acSponsor").trim();  //活动主办方
        String acDetails = request.getParameter("acDetails").trim();   //活动详情
        String pic = request.getParameter("pic").trim();    //活动图片

        //把日期转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = dateFormat.parse(acTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到活动的对象中
        Active active = new Active();
        active.setAcName(acName);
        active.setAcAddress(acAddress);
        active.setAcForm(acForm);
        active.setAcTime(date1);
        active.setAcSponsor(acSponsor);
        active.setAcDetails(acDetails);
        active.setPic(pic);

        boolean flag = activeService.insert(active);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

//    修改信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateActive(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String acId = request.getParameter("acId").trim();          //主键
        String acName = request.getParameter("acName").trim();      //活动名称
        String acAddress = request.getParameter("acAddress").trim();        //活动地址
        String acForm = request.getParameter("acForm").trim();        //活动形式
        String acTime = request.getParameter("acTime").trim();    //活动时间
        String acSponsor = request.getParameter("acSponsor").trim();  //活动主办方
        String acDetails = request.getParameter("acDetails").trim();   //活动详情

        //把日期转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = dateFormat.parse(acTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到活动的对象中
        Active active = new Active();
        active.setAcId(Integer.parseInt(acId));
        active.setAcName(acName);
        active.setAcAddress(acAddress);
        active.setAcForm(acForm);
        active.setAcTime(date1);
        active.setAcSponsor(acSponsor);
        active.setAcDetails(acDetails);

        boolean flag = activeService.update(active);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

//    删除
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteActive(HttpServletRequest request){
        String acId = request.getParameter("acId").trim();          //主键
        boolean flag = activeService.delete(Integer.parseInt(acId));
        return flag;
    }

//    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String acId = request.getParameter("acId").trim();          //主键
        return activeService.selectByPrimaryKey(Integer.parseInt(acId));
    }

    //根据名称模糊查询列表
    @RequestMapping(value = "/activeOfName", method = RequestMethod.GET)
    public Object activeOfName(HttpServletRequest request) {
        String acName=request.getParameter("acName").trim();
        return activeService.activeOfName("%"+acName+"%");
    }

    //根据名称模糊查询列表
    @RequestMapping(value = "/activeOfForm", method = RequestMethod.GET)
    public Object activeOfForm(HttpServletRequest request) {
        String acForm=request.getParameter("acForm").trim();
        return activeService.activeOfForm("%"+acForm+"%");
    }

//    查询所有
    @RequestMapping(value = "/allActive",method = RequestMethod.GET)
    public Object allActive(HttpServletRequest request){
        return activeService.allActive();
    }

    //上传图片
    @RequestMapping(value = "/updateAcImg", method = RequestMethod.POST)
    public Object updateShopImage(@RequestParam("file") MultipartFile acFile, @RequestParam("acId")int acId){
        JSONObject jsonObject=new JSONObject();
        if(acFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName =System.currentTimeMillis()+acFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "activePic";
        //如果文件路径不存在，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件夹地址
        String storeShopPath="/img/activePic/"+fileName;
        try {
            acFile.transferTo(dest);
            Active active =new Active();
            active.setAcId(acId);
            active.setPic(storeShopPath);
            boolean flag=activeService.update(active);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("activeImage",storeShopPath);
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
