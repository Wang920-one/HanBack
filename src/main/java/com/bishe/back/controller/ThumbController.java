package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Thumb;
import com.bishe.back.service.ThumbService;
import com.bishe.back.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/thumb")
public class ThumbController {
    @Autowired
    private ThumbService thumbService;

    //添加点赞
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addThumb(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId");     //用户id
        String videoId = request.getParameter("videoId");     //视频id
        String atcId = request.getParameter("atcId");     //专栏id
        String type = request.getParameter("type");     //类型（0视频1专栏）          //点赞内容

        if (new Byte(type) == 0) {
            if(videoId==null||videoId==""){
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "点赞视频为空");
                return jsonObject;
            }
            if(thumbService.existVideoId(Integer.parseInt(useId),Integer.parseInt(videoId))){
                jsonObject.put(Consts.CODE, 2);
                jsonObject.put(Consts.MSG, "已点赞");
                return jsonObject;
            }
        }else{
            if(atcId==null||atcId==""){
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "点赞专栏为空");
                return jsonObject;
            }
            if(thumbService.existAtcId(Integer.parseInt(useId),Integer.parseInt(atcId))){
                jsonObject.put(Consts.CODE, 2);
                jsonObject.put(Consts.MSG, "已点赞");
                return jsonObject;
            }
        }

        //保存到前端用户的对象中
        Thumb thumb = new Thumb();
        thumb.setUseId(Integer.parseInt(useId));
        thumb.setType(new Byte(type));

        if (new Byte(type) == 0) {
            thumb.setVideoId(Integer.parseInt(videoId));

        } else {
            thumb.setAtcId(Integer.parseInt(atcId));
        }

        boolean flag = thumbService.insert(thumb);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }

    //    删除点赞
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteThumb(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        boolean flag = thumbService.delete(Integer.parseInt(id));
        return flag;
    }

    //    查询所有点赞
    @RequestMapping(value = "/allThumb", method = RequestMethod.GET)
    public Object allThumb(HttpServletRequest request) {
        return thumbService.allThumb();
    }

    //查询某个用户的点赞列表
    @RequestMapping(value = "/thumbOfUseId", method = RequestMethod.GET)
    public Object thumbOfUseId(HttpServletRequest request) {
        String useId = request.getParameter("useId");
        return thumbService.thumbOfUseId(Integer.parseInt(useId));
    }

}
