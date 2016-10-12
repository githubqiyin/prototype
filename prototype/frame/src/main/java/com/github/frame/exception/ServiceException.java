package com.github.frame.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String code) {
        super(new Throwable());
        this.code = code;
    }

    public ServiceException(String code, String msg) {
        super(msg, new Throwable(msg));
        this.code = code;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}