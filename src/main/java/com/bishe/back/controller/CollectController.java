package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Collect;
import com.bishe.back.service.CollectService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    //添加收藏
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId");     //用户id
        String videoId = request.getParameter("videoId");     //视频id
        String atcId = request.getParameter("atcId");     //专栏id
        String type = request.getParameter("type");     //类型（0视频1专栏）          //收藏内容

        if (new Byte(type) == 0) {
            if(videoId==null||videoId==""){
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "收藏视频为空");
                return jsonObject;
            }
            if(collectService.existVideoId(Integer.parseInt(useId),Integer.parseInt(videoId))){
                jsonObject.put(Consts.CODE, 2);
                jsonObject.put(Consts.MSG, "已收藏");
                return jsonObject;
            }
        }else{
            if(atcId==null||atcId==""){
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "收藏专栏为空");
                return jsonObject;
            }
            if(collectService.existAtcId(Integer.parseInt(useId),Integer.parseInt(atcId))){
                jsonObject.put(Consts.CODE, 2);
                jsonObject.put(Consts.MSG, "已收藏");
                return jsonObject;
            }
        }

        //保存到前端用户的对象中
        Collect collect = new Collect();
        collect.setUseId(Integer.parseInt(useId));
        collect.setType(new Byte(type));

        if (new Byte(type) == 0) {
            collect.setVideoId(Integer.parseInt(videoId));

        } else {
            collect.setAtcId(Integer.parseInt(atcId));
        }

        boolean flag = collectService.insert(collect);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "收藏失败");
        return jsonObject;
    }

    //    删除收藏
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        boolean flag = collectService.delete(Integer.parseInt(id));
        return flag;
    }

    //    查询所有前端用户
    @RequestMapping(value = "/allCollect", method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request) {
        return collectService.allCollect();
    }

    //查询某个用户的收藏列表
    @RequestMapping(value = "/collectOfUseId", method = RequestMethod.GET)
    public Object collectOfUseId(HttpServletRequest request) {
        String useId = request.getParameter("useId");
        return collectService.collectOfUseId(Integer.parseInt(useId));
    }

}
