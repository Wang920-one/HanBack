package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.service.ManageService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ManageController {
    @Autowired
    private ManageService manageService;

    //判断是否登录成功
    @RequestMapping(value = "/manage/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject=new JSONObject();
        String manageName= request.getParameter("manageName");
        String managePassword= request.getParameter("managePassword");
        boolean flag=manageService.verifyPassword(manageName,managePassword);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.MANAGENAME,manageName);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或者密码错误");
        return jsonObject;
    }
}
