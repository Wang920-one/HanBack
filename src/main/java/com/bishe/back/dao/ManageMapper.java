package com.bishe.back.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

//管理员DAO
@Repository
public interface ManageMapper {
//    验证密码是否正确
    public int verifyPassword(String manageName,String managePassword);
}