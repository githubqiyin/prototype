package com.github.base;

import java.util.List;

import org.apache.log4j.Logger;

import com.github.util.FrameConstant;
import com.google.code.ssm.api.InvalidateMultiCache;
import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

public abstract class CacheServiceImpl<T> extends BaseServiceImpl<T> implements BaseService<T> {

    public static final Logger logger = Logger.getLogger(CacheServiceImpl.class);

    @Override
    @ReadThroughSingleCache(namespace = FrameConstant.CACHE_NAMESPACE, expiration = FrameConstant.CACHE_CYCLE.LONG)
    public T doFind(@ParameterValueKeyProvider T t) {
        return super.doFind(t);
    }

    @Override
    @InvalidateSingleCache(namespace = FrameConstant.CACHE_NAMESPACE)
    public int doUpdate(@ParameterValueKeyProvider T t) {
        return super.doUpdate(t);
    }

    @Override
    @InvalidateMultiCache(namespace = FrameConstant.CACHE_NAMESPACE)
    public int doUpdate(@ParameterValueKeyProvider List<T> ts) {
        return super.doUpdate(ts);
    }

    @Override
    @InvalidateSingleCache(namespace = FrameConstant.CACHE_NAMESPACE)
    public int doDelete(@ParameterValueKeyProvider T t) {
        return super.doUpdate(t);
    }

    public abstract BaseDAO<T> getBaseDAO();

}