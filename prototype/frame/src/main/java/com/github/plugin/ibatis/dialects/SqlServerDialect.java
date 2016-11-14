package com.github.plugin.ibatis.dialects;

public class SqlServerDialect implements Dialect {

    @Override
    public String getPaginationSql(String sql, int pageNo, int pageSize) {

        return new StringBuffer("select top ").append(pageSize).append(" from (").append(sql).append(") t where t.id not in (select top ").append((pageNo - 1) * pageSize).append(" t1.id from (")
                .append(sql).append(") t1)").toString();
    }

}