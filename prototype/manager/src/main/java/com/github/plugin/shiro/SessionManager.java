package com.github.plugin.shiro;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

@Component
public class SessionManager extends DefaultWebSessionManager {

    @Override
    public Collection<Session> getActiveSessions() {
        return super.getActiveSessions();
    }
}