package com.umg.entlogger

import java.sql.Connection;

class JDBCLoggerConnectionFactory {

    public static Connection getDatabaseConnection() {
        return DataSourceConnectionPool.INSTANCE.getDataSource().getConnection();
    }
}
