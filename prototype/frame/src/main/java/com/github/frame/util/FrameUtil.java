package com.github.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.frame.common.Symbol;

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

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

}