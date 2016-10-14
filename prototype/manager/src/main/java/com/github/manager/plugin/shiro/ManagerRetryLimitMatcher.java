package com.github.manager.plugin.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class ManagerRetryLimitMatcher extends HashedCredentialsMatcher {

    private static final int RETRY_COUNT_LIMIT = 5;

    private Cache<String, AtomicInteger> passwordRetryCache;

    public ManagerRetryLimitMatcher() {
    }

    public ManagerRetryLimitMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() >= RETRY_COUNT_LIMIT) {
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period");
        }
        return true;
    }

    public Cache<String, AtomicInteger> getPasswordRetryCache() {
        return passwordRetryCache;
    }

    public void setPasswordRetryCache(Cache<String, AtomicInteger> passwordRetryCache) {
        this.passwordRetryCache = passwordRetryCache;
    }

}