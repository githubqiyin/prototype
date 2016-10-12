package com.github.frame.plugin.ibatis.dialects;

public class MySQL5Dialect implements Dialect {

    @Override
    public String getPaginationSql(String sql, int offset, int limit) {
        return new StringBuffer(sql).append(" limit ").append(limit).append(",").append(offset).toString();
    }

}