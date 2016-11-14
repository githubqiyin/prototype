package com.github.plugin.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.common.Code;
import com.github.exception.ServiceException;

public class ServiceExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = Logger.getLogger(ServiceExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView mv = new ModelAndView("exception");
        mv.addObject(e instanceof ServiceException ? (ServiceException) e : new ServiceException(Code.SYSTEM_ERROR, "系统异常"));
        logger.info(e);
        return mv;
    }
}