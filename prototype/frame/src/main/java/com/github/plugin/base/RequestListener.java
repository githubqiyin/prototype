package com.github.plugin.base;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class RequestListener implements ServletRequestListener {

    private static Logger logger = Logger.getLogger(RequestListener.class);

    public static final String LOG_ID = "ID";

    public static final String UNCAUGHT_EXCEPTION = "javax.servlet.error.exception";

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
        MDC.put(LOG_ID, System.currentTimeMillis());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        Object object = event.getServletRequest().getAttribute(UNCAUGHT_EXCEPTION);
        if (object != null && object instanceof Throwable) {
            logger.error(UNCAUGHT_EXCEPTION, (Throwable) object);
        }

        MDC.remove(LOG_ID);
    }
}