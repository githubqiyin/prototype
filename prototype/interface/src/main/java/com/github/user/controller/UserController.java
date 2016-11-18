package com.github.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.base.BaseController;
import com.github.base.BaseService;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public String getUser() {
        return JSONObject.toJSONString(userService.doQuery(new UserBean()));
    }

    @Override
    public BaseService<UserBean> getBaseService() {
        return userService;
    }

}