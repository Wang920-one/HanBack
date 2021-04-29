package com.bishe.back.dao;

import com.bishe.back.domain.Active;
import com.bishe.back.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//用户DAO
@Repository
public interface UserMapper {

    //    验证密码是否正确
    public int verifyPassword(String userAccount, String userPassword);

    //    增加
    public int insert(User user);

    //    修改
    public int update(User user);

    //    删除
    public int delete(Integer id);

    //    查询
    public User selectByPrimaryKey(Integer id);

    //    查询所有用户
    public List<User> allUser();

    //    根据姓名查询
    public User getByUsername(String userName);

    //根据名称模糊查询列表
    public List<User> userOfName(String userName);

    //根据账号查询
    public User getByUserAccount(String userAccount);
}