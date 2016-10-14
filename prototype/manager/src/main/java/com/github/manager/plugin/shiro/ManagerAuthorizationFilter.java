package com.github.manager.plugin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.github.frame.common.Symbol;

public class ManagerAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String path = ((HttpServletRequest) request).getServletPath();
        if (StringUtils.isEmpty(path) || StringUtils.equals(path, Symbol.SLASH)) {
            return true;
        }

        int end = StringUtils.lastIndexOf(path, Symbol.POINT);
        if (end > 0) {
            path = StringUtils.substring(path, 0, end);
        }
        if (StringUtils.startsWith(path, Symbol.SLASH)) {
            path = StringUtils.substring(path, 1);
        }
        path = StringUtils.replace(path, Symbol.SLASH, Symbol.COLON);

        //return getSubject(request, response).isPermitted(path) ? true : false;
        return true;
    }

}