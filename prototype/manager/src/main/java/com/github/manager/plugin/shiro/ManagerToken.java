package com.github.manager.plugin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ManagerToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private String captcha;

    public ManagerToken(String username, String password, String captcha) {
        super(username, password);
        this.captcha = captcha;
    }

    public ManagerToken(String username, String password, boolean remberme, String captcha) {
        super(username, password, remberme);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}