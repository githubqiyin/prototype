package com.github.core.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.core.user.dao.UserDAO;
import com.github.core.user.model.UserBean;
import com.github.core.user.service.UserService;
import com.github.frame.base.BaseDAO;
import com.github.frame.base.CacheServiceImpl;

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