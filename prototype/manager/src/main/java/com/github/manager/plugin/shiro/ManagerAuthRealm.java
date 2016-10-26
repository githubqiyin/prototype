package com.github.manager.plugin.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.github.core.common.Constant;
import com.github.core.user.model.UserBean;
import com.github.core.user.service.UserService;
import com.github.frame.util.FrameUtil;

public class ManagerAuthRealm extends AuthorizingRealm {

    private static final Logger log = Logger.getLogger(ManagerAuthRealm.class);

    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        ManagerToken token = (ManagerToken) authenticationToken;

        // 校验验证码
        validaeCaptcha(token);

        // TODO 查询数据库
        UserBean userBean = new UserBean();
        userBean.setUsername(token.getUsername());
        userBean.setPassword(new String(token.getPassword()));
        return new SimpleAuthenticationInfo(userBean.getUsername(), userBean.getPassword(), getName());
    }

    private void validaeCaptcha(ManagerToken token) {

        String requestCaptcha = token.getCaptcha();
        String sessionCaptcha = (String) FrameUtil.getSession().getAttribute(Constant.SESSION_CAPTCHA_KEY);

        if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha) || !StringUtils.equalsIgnoreCase(requestCaptcha, sessionCaptcha)) {
            throw new AuthenticationException("验证码错误");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}