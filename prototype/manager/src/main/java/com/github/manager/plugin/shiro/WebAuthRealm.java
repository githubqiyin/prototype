package com.github.manager.plugin.shiro;

import java.util.ArrayList;
import java.util.List;

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

public class WebAuthRealm extends AuthorizingRealm {

    private static final Logger log = Logger.getLogger(WebAuthRealm.class);

    private UserService userService;

    private MenuService menuService;

    private RoleUserService roleUserService;

    private RoleService roleService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        WebToken token = (WebToken) authenticationToken;

        handleCaptcha(token);

        return handleAuth(token);

    }

    private void handleCaptcha(WebToken token) {

        String sessionCaptcha = (String) SessionUtil.getAttribute(Constant.SESSION_CAPTCHA_KEY);
        String requestCaptcha = token.getCaptcha();

        if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha) || !StringUtils.equalsIgnoreCase(requestCaptcha, sessionCaptcha)) {
            throw new AuthenticationException("验证码错误");
        }
    }

    private SimpleAuthenticationInfo handleAuth(WebToken token) {

        UserVO param = new UserVO();
        param.setUsername(token.getUsername());
        UserVO user = userService.doFind(param);

        if (user == null) {
            throw new AuthenticationException("用户名或者密码错误");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        SessionUtil.setAttribute(user.getUsername(), user.getId());
        SessionUtil.setAttribute(Constant.SESSION_USER, user);

        // 处理角色
        RoleUserVO roleUserVO = new RoleUserVO();
        roleUserVO.setUserId(user.getId());
        List<RoleUserVO> roleUserVOs = roleUserService.doFindAll(roleUserVO);
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        for (RoleUserVO vo : roleUserVOs) {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(vo.getRoleId());
            roleList.add(roleService.doFind(roleVO));
        }
        SessionUtil.setAttribute(Constant.SESSION_ROLE, roleList);
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO 角色、权限、资源控制
        String username = (String) principals.fromRealm(getName()).iterator().next();
        String userId = (String) SessionUtil.getAttribute(username);
        if (StringUtils.isNotBlank(userId)) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<MenuVO> permissions = menuService.doFindMenuUrlByUserId(userId);
            if (permissions == null || permissions.size() <= 0) {
                return info;
            }
            for (MenuVO menuVO : permissions) {
                String perm = menuVO.getUrl();
                if (perm == null || "".equals(perm)) {
                    continue;
                }
                if ("/".equals(perm.charAt(0))) {
                    perm = perm.substring(1);
                }
                int end = perm.lastIndexOf("/");
                if (end > 0) {
                    perm = perm.substring(0, end);
                }
                perm = perm.replaceAll("/+", ":");
                info.addStringPermission(perm + ":*");
            }
            return info;
        } else {
            return null;
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public RoleUserService getRoleUserService() {
        return roleUserService;
    }

    public void setRoleUserService(RoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}