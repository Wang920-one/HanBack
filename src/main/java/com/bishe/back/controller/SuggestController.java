package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Suggest;
import com.bishe.back.service.SuggestService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/suggest")
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    //添加投诉建议
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSuggest(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId").trim();      //用户编号
        String sugTitle = request.getParameter("sugTitle").trim();        //投诉标题
        String sugDetail = request.getParameter("sugDetail").trim();        //投诉内容
//        String sugTime = request.getParameter("sugTime").trim();      //投诉时间

        //保存到投诉建议的对象中
        Suggest suggest = new Suggest();
        suggest.setUseId(Integer.parseInt(useId));
//        suggest.setSugTime(date1);
        suggest.setSugTitle(sugTitle);
        suggest.setSugDetail(sugDetail);

        boolean flag = suggestService.insert(suggest);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    //    修改投诉信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSuggest(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId").trim();      //用户编号
        String sugTitle = request.getParameter("sugTitle").trim();        //投诉标题
        String sugDetail = request.getParameter("sugDetail").trim();        //投诉内容
//        String sugTime = request.getParameter("sugTime").trim();      //投诉时间

        //保存到投诉建议的对象中
        Suggest suggest = new Suggest();
        suggest.setUseId(Integer.parseInt(useId));
//        suggest.setSugTime(date1);
        suggest.setSugTitle(sugTitle);
        suggest.setSugDetail(sugDetail);

        boolean flag = suggestService.update(suggest);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    //    删除投诉建议
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSuggest(HttpServletRequest request) {
        String sugId = request.getParameter("sugId").trim();          //主键
        boolean flag = suggestService.delete(Integer.parseInt(sugId));
        return flag;
    }

    //    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String sugId = request.getParameter("sugId").trim();          //主键
        return suggestService.selectByPrimaryKey(Integer.parseInt(sugId));
    }

    //    查询所有投诉建议
    @RequestMapping(value = "/allSuggest", method = RequestMethod.GET)
    public Object allSuggest(HttpServletRequest request) {
        return suggestService.allSuggest();
    }

}
