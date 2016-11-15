package com.github.user.service;

import com.github.base.BaseService;
import com.github.user.model.UserBean;

public interface UserService extends BaseService<UserBean> {

    public UserBean doQuery(UserBean t);

}