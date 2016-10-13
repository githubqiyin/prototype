package com.github.manager.plugin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class SimplePermissionsAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String perm = ((HttpServletRequest) request).getServletPath();
        if (perm == null || perm == "" || perm.equals("/")) {
            return true;
        }
        int end = perm.lastIndexOf(".");
        if (end > 0) {
            perm = perm.substring(0, end);
        }
        if ("/".equals(perm.charAt(0) + "")) {
            perm = perm.substring(1);
        }
        perm = perm.replaceAll("/+", ":");
        if (subject.isPermitted(perm)) {
            return true;
        }
        return false;
    }

}
