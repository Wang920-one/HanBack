package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Subscribe;
import com.bishe.back.domain.Thumb;
import com.bishe.back.service.SubscribeService;
import com.bishe.back.service.ThumbService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    //添加关注
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSubscribe(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String beSubscribe = request.getParameter("beSubscribe");     //被关注者id
        String subscribe = request.getParameter("subscribe");     //关注者id
        if(beSubscribe==null||beSubscribe==""){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "关注的用户为空");
            return jsonObject;
        }
        if(subscribeService.existSubscribe(Integer.parseInt(subscribe),Integer.parseInt(beSubscribe))){
            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MSG, "已关注");
            return jsonObject;
        }
        //保存到前端用户的对象中
        Subscribe subscribe1 = new Subscribe();
        subscribe1.setBeSubscribe(Integer.parseInt(beSubscribe));
        subscribe1.setSubscribe(Integer.parseInt(subscribe));

        boolean flag = subscribeService.insert(subscribe1);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "关注成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "关注失败");
        return jsonObject;
    }

    //    删除关注
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSubscribe(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        boolean flag = subscribeService.delete(Integer.parseInt(id));
        return flag;
    }

    //    查询所有关注
    @RequestMapping(value = "/allSubscribe", method = RequestMethod.GET)
    public Object allSubscribe(HttpServletRequest request) {
        return subscribeService.allSubscribe();
    }

    //查询某个用户的关注列表
    @RequestMapping(value = "/listOfSubscribe", method = RequestMethod.GET)
    public Object listOfSubscribe(HttpServletRequest request) {
        String subscribe = request.getParameter("subscribe");
        return subscribeService.listOfSubscribe(Integer.parseInt(subscribe));
    }
    //查询某个用户的粉丝列表
    @RequestMapping(value = "/listOfBeSubscribe", method = RequestMethod.GET)
    public Object listOfBeSubscribe(HttpServletRequest request) {
        String beSubscribe = request.getParameter("beSubscribe");
        return subscribeService.listOfBeSubscribe(Integer.parseInt(beSubscribe));
    }
}
