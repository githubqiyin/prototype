package com.github.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.github.base.BaseController;
import com.github.base.BaseService;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Reference
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