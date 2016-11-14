package com.github.plugin.ibatis.interceptor;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

public class PaginationWrapperFactory implements ObjectWrapperFactory {

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaobject, Object obj) {
        return null;
    }

    @Override
    public boolean hasWrapperFor(Object obj) {
        return false;
    }
}