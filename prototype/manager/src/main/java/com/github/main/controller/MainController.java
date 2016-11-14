package com.github.main.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.common.Code;
import com.github.exception.ServiceException;
import com.github.plugin.shiro.ManagerToken;
import com.github.user.model.UserBean;
import com.github.util.FrameUtil;

@Controller
public class MainController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String gotoLogin() {
        return "main/login";
    }

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

}