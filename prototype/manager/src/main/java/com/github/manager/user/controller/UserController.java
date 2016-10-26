package com.github.manager.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.core.user.model.UserBean;
import com.github.core.user.service.UserService;
import com.github.frame.base.BaseController;
import com.github.frame.base.BaseService;

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

    @Override
    public BaseService<UserBean> getBaseService() {
        return this.userService;
    }

}