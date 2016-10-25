package com.github.core.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.core.user.dao.UserDAO;
import com.github.core.user.model.UserBean;
import com.github.core.user.service.UserService;
import com.github.frame.base.BaseDAO;
import com.github.frame.base.BaseServiceImpl;
import com.github.frame.util.FrameUtil;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserBean> implements UserService {

    private static final Logger logger = Logger.getLogger(FrameUtil.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    @ReadThroughSingleCache(namespace = "singleCache", expiration = 30)
    public UserBean doFindFromCache(@ParameterValueKeyProvider UserBean userBean) {
        logger.info("未命中缓存");
        return doFind(userBean);
    }

    @Override
    public BaseDAO<UserBean> getBaseDAO() {
        return userDAO;
    }

}