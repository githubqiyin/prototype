package com.github.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.base.BaseDAO;
import com.github.base.BaseServiceImpl;
import com.github.user.dao.UserDAO;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserBean> implements UserService {

    @Autowired
    private UserDAO userDAO;

    // 测试接口连通
    @Autowired
    public UserBean doQuery(UserBean t) {
        return doFind(new UserBean("1"));
    }

    // 测试事务回滚
    @Autowired
    public int doEdit(UserBean u) {

        u.setFullname("123");
        doUpdate(u);

        u.setFullname("1231333333333333333333333333312313333333333333333333333333");
        return doUpdate(u);
    }

    @Override
    public BaseDAO<UserBean> getBaseDAO() {
        return userDAO;
    }

}