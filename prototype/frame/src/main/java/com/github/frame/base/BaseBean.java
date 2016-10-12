package com.github.frame.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> extra = new HashMap<String, Object>();

    public void put(String key, Object value) {
        extra.put(key, value);
    }

    public Object get(String key) {
        return extra.get(key);
    }

    public void remove(String key) {
        extra.remove(key);
    }

    public void clear() {
        extra.clear();
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

}