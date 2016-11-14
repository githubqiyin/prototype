package com.github.plugin.base;

import javax.servlet.ServletContextEvent;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {

    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }

    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

}