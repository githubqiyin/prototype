package com.github.plugin.ibatis.dialects;

public class OrcaleDialect implements Dialect {

    @Override
    public String getPaginationSql(String sql, int pageNo, int pageSize) {
        return new StringBuffer("select * from (select rownum rn, t.* from (").append(sql).append(") t where rownum <= ").append(pageNo * pageSize).append(") t1 where t1.rn > ")
                .append((pageNo - 1) * pageSize).toString();
    }

}