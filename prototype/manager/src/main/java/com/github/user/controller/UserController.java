package com.github.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.base.BaseController;
import com.github.base.BaseService;
import com.github.user.model.UserBean;
import com.github.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("/gotoEdit")
    public String gotoEdit(UserBean userBean, ModelMap m) {
        m.addAttribute(userService.doFind(userBean));
        return basePath + "edit";
    }

    @RequestMapping("/listTest")
    public String listTest() {
        List<UserBean> list = new ArrayList<UserBean>();
        list.add(new UserBean("4"));
        list.add(new UserBean("10000011366"));
        userService.doUpdate(list);
        return basePath + "edit";
    }

    @Override
    public BaseService<UserBean> getBaseService() {
        return this.userService;
    }

}