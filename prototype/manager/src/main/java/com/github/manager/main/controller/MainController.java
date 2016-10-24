package com.github.manager.main.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.core.user.model.UserBean;
import com.github.frame.base.BaseBean;
import com.github.frame.base.BaseController;
import com.github.frame.base.BaseService;
import com.github.frame.common.Code;
import com.github.frame.exception.ServiceException;
import com.github.frame.util.FrameUtil;
import com.github.manager.plugin.shiro.ManagerToken;

@Controller
public class MainController extends BaseController<BaseBean> {

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

    @Override
    public BaseService<BaseBean> getBaseService() {
        return null;
    }

}