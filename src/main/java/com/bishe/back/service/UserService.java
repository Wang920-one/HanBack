package com.bishe.back.service;

import com.bishe.back.domain.Active;
import com.bishe.back.domain.User;

import java.util.List;

//用户service接口
public interface UserService {
//    验证密码是否正确
    public boolean verifyPassword(String userAccount, String userPassword);

    //    增加
    public boolean insert(User user);

    //    修改
    public boolean update(User user);

    //    删除
    public boolean delete(Integer id);

    //    查询
    public User selectByPrimaryKey(Integer id);

    //    查询所有用户
    public List<User> allUser();

    //    根据用户姓名查询列表
    public User getByUsername(String userName);

    //     根据名称模糊查询列表
    public List<User> userOfName(String userName);

    //根据账号查询
    public User getByUserAccount(String userAccount);
}
