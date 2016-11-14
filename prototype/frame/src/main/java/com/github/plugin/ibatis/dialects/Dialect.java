package com.github.plugin.ibatis.dialects;

public interface Dialect {

    public static enum Type {

        MYSQL {

            public String getValue() {
                return "mysql";
            }

        },

        SQLSERVER {

            public String getValue() {
                return "sqlserver";
            }

        },

        DB2 {

            public String getValue() {
                return "db2";
            }

        },
        ORACLE {

            public String getValue() {
                return "oracle";
            }

        };

        public abstract String getValue();

    }

    public String getPaginationSql(String sql, int offset, int limit);

}