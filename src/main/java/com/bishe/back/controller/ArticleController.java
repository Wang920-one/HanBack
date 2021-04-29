package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Article;
import com.bishe.back.service.ArticleService;
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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //添加稿件
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addArticle(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String useId = request.getParameter("useId").trim();      //用户Id
        String bioati = request.getParameter("bioati").trim();        //稿件标题
        String pic = request.getParameter("pic").trim();        //稿件封面
        String atcDescribe = request.getParameter("atcDescribe").trim();    //稿件详情
        String atcType = request.getParameter("atcType").trim();    //稿件详情
        String actText = request.getParameter("actText").trim();   //专栏内容
        //保存到稿件的对象中
        Article article = new Article();
        article.setUseId(Integer.parseInt(useId));
        article.setBioati(bioati);
        article.setPic(pic);
        article.setAtcType(atcType);
        article.setAtcDescribe(atcDescribe);
        article.setActText(actText);

        boolean flag = articleService.insert(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

//    修改稿件信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateArticle(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String useId = request.getParameter("useId").trim();      //用户Id
        String bioati = request.getParameter("bioati").trim();        //稿件标题
        String atcDescribe = request.getParameter("atcDescribe").trim();    //稿件详情
//        String atcTime = request.getParameter("atcTime").trim();   //发布时间
        String actText = request.getParameter("actText").trim();   //专栏内容

        //保存到稿件的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setUseId(Integer.parseInt(useId));
        article.setBioati(bioati);
        article.setAtcDescribe(atcDescribe);
        article.setActText(actText);

        boolean flag = articleService.update(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

//    删除稿件
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteArticle(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = articleService.delete(Integer.parseInt(id));
        return flag;
    }

//    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return articleService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //根据名称模糊查询列表
    @RequestMapping(value = "/articleOfBioati", method = RequestMethod.GET)
    public Object articleOfBioati(HttpServletRequest request) {
        String bioati=request.getParameter("bioati").trim();
        return articleService.articleOfBioati("%"+bioati+"%");
    }

    //查询某个用户的稿件
    @RequestMapping(value = "/articleOfUseId", method = RequestMethod.GET)
    public Object articleOfUseId(HttpServletRequest request) {
        String useId = request.getParameter("useId");
        return articleService.articleOfUseId(Integer.parseInt(useId));
    }

//    查询所有稿件
    @RequestMapping(value = "/allArticle",method = RequestMethod.GET)
    public Object allArticle(HttpServletRequest request){
        return articleService.allArticle();
    }

    //上传图片
    @RequestMapping(value = "/updateArticleImg", method = RequestMethod.POST)
    public Object updateArticleImage(@RequestParam("file") MultipartFile articleFile, @RequestParam("id")int id){
        JSONObject jsonObject=new JSONObject();
        if(articleFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName =System.currentTimeMillis()+articleFile.getOriginalFilename();
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
        String storeShopPath="/img/articlePic/"+fileName;
        try {
            articleFile.transferTo(dest);
            Article article =new Article();
            article.setId(id);
            article.setPic(storeShopPath);
            boolean flag=articleService.update(article);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("articleImage",storeShopPath);
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

    //给专栏点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String atcThumes = request.getParameter("atcThumes").trim();          //评论点赞数

        //保存到前端用户的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setAtcThumes(Integer.parseInt(atcThumes));

        boolean flag = articleService.update(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失败");
        return jsonObject;
    }

    //审核专栏
    @RequestMapping(value = "/shenHe", method = RequestMethod.POST)
    public Object shenHe(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String actReview = request.getParameter("actReview").trim();          //分享数

        //保存到前端用户的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setActReview(new Byte(actReview));

        boolean flag = articleService.update(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"审核成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"审核失败");
        return jsonObject;
    }

    //收藏专栏
    @RequestMapping(value = "/shouC", method = RequestMethod.POST)
    public Object shouC(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String atcSc = request.getParameter("atcSc").trim();          //收藏数

        //保存到前端用户的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setAtcSc(Integer.parseInt(atcSc));

        boolean flag = articleService.update(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    //分享专栏
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public Object share(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String actShare = request.getParameter("actShare").trim();          //分享数

        //保存到前端用户的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setActShare(Integer.parseInt(actShare));

        boolean flag = articleService.update(article);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"分享成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"分享失败");
        return jsonObject;
    }

    //浏览专栏
    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public Object browse(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();      //主键
        String atcBrowse = request.getParameter("atcBrowse").trim();          //浏览数

        //保存到前端用户的对象中
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setAtcBrowse(Integer.parseInt(atcBrowse));

        boolean flag = articleService.update(article);
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
