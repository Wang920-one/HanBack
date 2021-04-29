package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Video;
import com.bishe.back.service.VideoService;
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
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    //添加稿件
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addVideo(HttpServletRequest request,@RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId").trim();      //用户Id
        String videoTitle = request.getParameter("videoTitle").trim();        //标题
        String videoDes = request.getParameter("videoDes").trim();    //详情
        String videoType = request.getParameter("videoType").trim();  //类型
        //上传视频文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"视频上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"video";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/video/"+fileName;
        try {
            mpFile.transferTo(dest);
//            videoFile.transferTo(dest1);
            //保存到稿件的对象中
            Video video = new Video();
            video.setUseId(Integer.parseInt(useId));
            video.setVideoTitle(videoTitle);
//            video.setPic(storeVideoPath1);
            video.setVideoDes(videoDes);
            video.setVideoType(videoType);
            video.setVideoFile(storeUrlPath);
            boolean flag = videoService.insert(video);
            if(flag){   //保存成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"添加成功");
                jsonObject.put("videofile",storeUrlPath);
//                jsonObject.put("videopic",storeVideoPath1);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

//    修改稿件信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateVideo(HttpServletRequest request,@RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();      //Id
        String useId = request.getParameter("useId").trim();      //用户Id
        String videoTitle = request.getParameter("videoTitle").trim();        //标题
        String videoDes = request.getParameter("videoDes").trim();    //详情
//        String videoType = request.getParameter("videoType").trim();  //类型
        //上传视频文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"视频上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"video";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/video/"+fileName;
        try {
            mpFile.transferTo(dest);
//            videoFile.transferTo(dest1);
            //保存到稿件的对象中
            Video video = new Video();
            video.setId(Integer.parseInt(id));
            video.setUseId(Integer.parseInt(useId));
            video.setVideoTitle(videoTitle);
//            video.setPic(storeVideoPath1);
            video.setVideoDes(videoDes);
//            video.setVideoType(videoType);
            video.setVideoFile(storeUrlPath);
            boolean flag = videoService.update(video);
            if(flag){   //保存成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"修改成功");
                jsonObject.put("videofile",storeUrlPath);
//                jsonObject.put("videopic",storeVideoPath1);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

//    删除稿件
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteVideo(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = videoService.delete(Integer.parseInt(id));
        return flag;
    }

//    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id");          //主键
        return videoService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //根据名称模糊查询列表
    @RequestMapping(value = "/videoOfTitle", method = RequestMethod.GET)
    public Object videoOfTitle(HttpServletRequest request) {
        String videoTitle=request.getParameter("videoTitle");
        return videoService.videoOfTitle(videoTitle);
    }

    //根据类型模糊查询列表
    @RequestMapping(value = "/videoOfType", method = RequestMethod.GET)
    public Object videoOfType(HttpServletRequest request) {
        String videoType=request.getParameter("videoType").trim();
        return videoService.videoOfType("%"+videoType+"%");
    }

    //查询某个用户的稿件
    @RequestMapping(value = "/videoOfUseId", method = RequestMethod.GET)
    public Object videoOfUseId(HttpServletRequest request) {
        String useId = request.getParameter("useId");
        return videoService.videoOfUseId(Integer.parseInt(useId));
    }

    //    查询所有稿件
    @RequestMapping(value = "/allVideo",method = RequestMethod.GET)
    public Object allVideo(HttpServletRequest request){
        return videoService.allVideo();
    }

    //    降序浏览量查询所有
    @RequestMapping(value = "/videoOfBrowse",method = RequestMethod.GET)
    public Object videoOfBrowse(HttpServletRequest request){
        return videoService.videoOfBrowse();
    }

    //    降序收藏量查询所有，推荐榜
    @RequestMapping(value = "/videoOfCollect",method = RequestMethod.GET)
    public Object videoOfCollect(HttpServletRequest request){
        return videoService.videoOfCollect();
    }

    //    降序点赞量查询所有，热门榜
    @RequestMapping(value = "/videoOfThumse",method = RequestMethod.GET)
    public Object videoOfThumse(HttpServletRequest request){
        return videoService.videoOfThumse();
    }
    //上传图片
    @RequestMapping(value = "/updateVideoImg", method = RequestMethod.POST)
    public Object updateVideoImage(@RequestParam("file") MultipartFile videoFile, @RequestParam("id")int id){
        JSONObject jsonObject=new JSONObject();
        if(videoFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName =System.currentTimeMillis()+videoFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "articlePic";
        //如果文件路径不存在，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件夹地址
        String storeVideoPath="/img/articlePic/"+fileName;
        try {
            videoFile.transferTo(dest);
            Video video =new Video();
            video.setId(id);
            video.setPic(storeVideoPath);
            boolean flag=videoService.update(video);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("videoImage",storeVideoPath);
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

    //给视频点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String videoThumes = request.getParameter("videoThumes").trim();          //评论点赞数

        //保存到前端用户的对象中
        Video video = new Video();
        video.setId(Integer.parseInt(id));
        video.setVideoThumes(Integer.parseInt(videoThumes));

        boolean flag = videoService.update(video);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失败");
        return jsonObject;
    }

    //审核视频
    @RequestMapping(value = "/shenHe", method = RequestMethod.POST)
    public Object shenHe(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String videoReview = request.getParameter("videoReview").trim();          //审核状态

        //保存到前端用户的对象中
        Video video = new Video();
        video.setId(Integer.parseInt(id));
        video.setVideoReview(new Byte(videoReview));

        boolean flag = videoService.update(video);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"审核成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"审核失败");
        return jsonObject;
    }

    //收藏视频
    @RequestMapping(value = "/shouC", method = RequestMethod.POST)
    public Object shouC(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String videoSc = request.getParameter("videoSc").trim();          //评论点赞数

        //保存到前端用户的对象中
        Video video = new Video();
        video.setId(Integer.parseInt(id));
        video.setVideoSc(Integer.parseInt(videoSc));

        boolean flag = videoService.update(video);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    //分享视频
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public Object share(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String videoShare = request.getParameter("videoShare").trim();          //评论点赞数

        //保存到前端用户的对象中
        Video video = new Video();
        video.setId(Integer.parseInt(id));
        video.setVideoShare(Integer.parseInt(videoShare));

        boolean flag = videoService.update(video);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"分享成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"分享失败");
        return jsonObject;
    }

    //浏览视频
    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public Object browse(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String videoBrowse = request.getParameter("videoBrowse").trim();          //评论点赞数

        //保存到前端用户的对象中
        Video video = new Video();
        video.setId(Integer.parseInt(id));
        video.setVideoBrowse(Integer.parseInt(videoBrowse));

        boolean flag = videoService.update(video);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"浏览成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"浏览失败");
        return jsonObject;
    }
}
