package com.github.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.base.BaseDAO;
import com.github.base.CacheServiceImpl;
import com.github.user.dao.UserDAO;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends CacheServiceImpl<UserBean> implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public BaseDAO<UserBean> getBaseDAO() {
        return userDAO;
    }

}