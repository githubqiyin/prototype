package com.github.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.base.BaseDAO;
import com.github.base.CacheServiceImpl;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Service
public class UserServiceImpl extends CacheServiceImpl<UserBean> implements UserService {

    @Autowired
    public UserBean doQuery(UserBean t) {
        return new UserBean("1");
        // return super.doFind(t);
    }

    @Override
    public BaseDAO<UserBean> getBaseDAO() {
        return null;
    }

}