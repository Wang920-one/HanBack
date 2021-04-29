package com.bishe.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.back.domain.Active;
import com.bishe.back.domain.User;
import com.bishe.back.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //判断是否登录成功
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String userAccount= request.getParameter("userAccount").trim();
        String userPassword= request.getParameter("userPassword").trim();
        if(userAccount==null||userAccount.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"账号不能为空");
            return jsonObject;
        }
        if(userPassword==null||userPassword.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //保存到前端用户对象中
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        boolean flag=userService.verifyPassword(userAccount,userPassword);
        if(flag){
            //验证成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            jsonObject.put("userMsg",userService.getByUserAccount(userAccount));
//            jsonObject.put("userMsg",userService.getByUsername(userAccount));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"账号或者密码错误");
        return jsonObject;
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userAccount = request.getParameter("userAccount").trim();     //账号
        String userName = request.getParameter("userName").trim();     //昵称
        String userPassword = request.getParameter("userPassword").trim();          //密码
        String userSex = request.getParameter("userSex").trim();     //性别
        String userImage = request.getParameter("userImage").trim();           //头像
        String userPhone = request.getParameter("userPhone").trim();      //电话
        String userQQ = request.getParameter("userQQ").trim();          //QQ
        String userAddress = request.getParameter("userAddress").trim();          //地址
        String userBirthday = request.getParameter("userBirthday").trim();          //生日
        String userSign = request.getParameter("userSign").trim();          //签名
        String userEmail = request.getParameter("userEmail").trim();          //邮箱
        String createTime = request.getParameter("createTime").trim();          //创建时间、
        String updateTime = request.getParameter("updateTime").trim();          //更新时间、

        if(userAccount==null||userAccount.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"账号不能为空");
            return jsonObject;
        }

        User user1 = userService.getByUserAccount(userAccount);
//        User user2 = userService.getByUsername(userName);
        if(user1!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"账号已存在");
            return jsonObject;
        }
//        if(user2!=null){
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"用户名已存在");
//            return jsonObject;
//        }
        if(userPassword==null||userPassword.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        Date birthDate1 = new Date();
        Date birthDate2 = new Date();
        try {
            birthDate = dateFormat.parse(userBirthday);
            birthDate1 = dateFormat.parse(createTime);
            birthDate2 = dateFormat.parse(updateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //保存到前端用户的对象中
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserSex(new Byte(userSex));
        user.setUserImage(userImage);
        user.setUserPhone(userPhone);
        user.setUserBirthday(birthDate);
        user.setUpdateTime(birthDate2);
        user.setCreateTime(birthDate1);
        user.setUserQQ(userQQ);
        user.setUserAddress(userAddress);
        user.setUserSign(userSign);
        user.setUserEmail(userEmail);

        boolean flag = userService.insert(user);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

//    修改用户信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();
        String userName = request.getParameter("userName").trim();     //昵称、
        String userPassword = request.getParameter("userPassword").trim();          //密码、
        String userSex = request.getParameter("userSex").trim();     //性别、
        String userPhone = request.getParameter("userPhone").trim();      //电话
        String userQQ = request.getParameter("userQQ").trim();          //QQ
        String userAddress = request.getParameter("userAddress").trim();          //地址、
        String userBirthday = request.getParameter("userBirthday").trim();          //生日、
        String userSign = request.getParameter("userSign").trim();          //签名、
        String userEmail = request.getParameter("userEmail").trim();          //邮箱、
        String updateTime = request.getParameter("updateTime").trim();          //更新时间、

        if(userPassword==null||userPassword.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        Date birthDate2 = new Date();
        try {
            birthDate = dateFormat.parse(userBirthday);
            birthDate2 = dateFormat.parse(updateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //保存到前端用户的对象中
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserSex(new Byte(userSex));
        user.setUserPhone(userPhone);
        user.setUserBirthday(birthDate);
        user.setUpdateTime(birthDate2);
        user.setUserQQ(userQQ);
        user.setUserAddress(userAddress);
        user.setUserSign(userSign);
        user.setUserEmail(userEmail);

        boolean flag = userService.update(user);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

//    删除前端用户
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = userService.delete(Integer.parseInt(id));
        return flag;
    }

//    根据主键查询整个对象
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return userService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //根据名称模糊查询列表
    @RequestMapping(value = "/userOfName", method = RequestMethod.GET)
    public Object userOfName(HttpServletRequest request) {
        String userName=request.getParameter("userName").trim();
        return userService.userOfName("%"+userName+"%");
    }

//    查询所有前端用户
    @RequestMapping(value = "/allUser",method = RequestMethod.GET)
    public Object allUser(HttpServletRequest request){
        return userService.allUser();
    }

    //上传图片
    @RequestMapping(value = "/updateUserImg", method = RequestMethod.POST)
    public Object updateShopImage(@RequestParam("file") MultipartFile userFile, @RequestParam("id")int id){
        JSONObject jsonObject=new JSONObject();
        if(userFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间毫秒+原来的文件名
        String fileName =System.currentTimeMillis()+userFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "userPic";
        //如果文件路径不存在，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件夹地址
        String storeShopPath="/img/userPic/"+fileName;
        try {
            userFile.transferTo(dest);
            User user =new User();
            user.setId(id);
            user.setUserImage(storeShopPath);
            boolean flag=userService.update(user);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("userImage",storeShopPath);
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
