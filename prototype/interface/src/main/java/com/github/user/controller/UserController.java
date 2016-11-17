package com.github.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.base.BaseController;
import com.github.base.BaseService;
import com.github.user.model.UserBean;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Override
    public BaseService<UserBean> getBaseService() {
        return null;
    }

}