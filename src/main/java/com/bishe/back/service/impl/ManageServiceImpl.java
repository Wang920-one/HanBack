package com.bishe.back.service.impl;

import com.bishe.back.dao.ManageMapper;
import com.bishe.back.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//管理员service实现类
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    //管理员登录
    @Override
    public boolean verifyPassword(String manageName, String managePassword) {
        return manageMapper.verifyPassword(manageName,managePassword)>0;
    }
}

