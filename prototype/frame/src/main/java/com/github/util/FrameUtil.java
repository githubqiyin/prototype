package com.github.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.common.Symbol;

public class FrameUtil {

    private static Logger logger = Logger.getLogger(FrameUtil.class);

    public static String getBasePath(Class<?> clazz) {
        StringBuffer path = new StringBuffer(clazz.getAnnotation(RequestMapping.class).value()[0]);
        if (!StringUtils.startsWith(path, Symbol.SLASH)) {
            path.insert(0, Symbol.SLASH);
        }
        if (!StringUtils.endsWith(path, Symbol.SLASH)) {
            path.append(Symbol.SLASH);
        }
        return path.toString();
    }

    public static String getBasePath(HttpServletRequest request) {
        StringBuffer basePath = new StringBuffer();
        basePath.append(request.getScheme()).append(Symbol.COMMA).append(Symbol.SLASH).append(Symbol.SLASH).append(request.getServerName()).append(Symbol.COLON).append(request.getServerPort())
                .append(request.getContextPath()).append(Symbol.SLASH);
        return basePath.toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static String md5Hash(String source, String salt) {
        return new Md5Hash(source, salt, 3).toString();
    }

    public static String md5Hash(String source) {
        return new Md5Hash(source).toString();
    }
}