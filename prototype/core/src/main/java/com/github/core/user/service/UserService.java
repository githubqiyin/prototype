package com.github.core.user.service;

import com.github.core.user.model.UserBean;
import com.github.frame.base.BaseService;

public interface UserService extends BaseService<UserBean> {

    public UserBean doFindFromCache(UserBean userBean);

}