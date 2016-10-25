package com.github.frame.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.github.frame.common.Symbol;
import com.google.code.ssm.api.CacheKeyMethod;

public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;

    protected Map<String, Object> extra = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    @CacheKeyMethod
    public String cacheKey() {
        return new StringBuilder(this.getClass().getCanonicalName()).append(Symbol.POINT).append(this.getId()).toString();
    }
}