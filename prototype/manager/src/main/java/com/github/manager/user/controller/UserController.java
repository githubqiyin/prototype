package com.github.manager.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.core.user.model.UserBean;
import com.github.core.user.service.UserService;
import com.github.frame.base.BaseController;
import com.github.frame.base.BaseService;
import com.github.frame.common.Code;
import com.github.frame.exception.ServiceException;
import com.github.frame.util.FrameUtil;
import com.github.manager.plugin.shiro.ManagerToken;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(UserBean user, String captcha, ModelMap m) {
        try {
            SecurityUtils.getSubject().login(new ManagerToken(user.getUsername(), FrameUtil.md5Hash(user.getPassword()), StringUtils.upperCase(captcha)));
            return "redirect:/index.html";
        } catch (AuthenticationException e) {
            m.addAttribute(new ServiceException(Code.AUTH_FALL));
            return "main/login";
        }
    }

    @Override
    public BaseService<UserBean> getBaseService() {
        return this.userService;
    }

}