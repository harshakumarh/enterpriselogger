package com.umg.entlogger

import java.sql.Connection;
class JDBCLoggerConnectionFactory {

    public static Connection getDatabaseConnection() {
        return DataSourceConnectionPool.INSTANCE.getDataSource().getConnection();
    }

    /*public static void main(String[] args) {
        //System.setProperty("logger.db.props", "file:///C:/sandeep/api-rancher-11/scripts/enterpriselogger/src/main/resources/dbLogger.properties")
        getDatabaseConnection();
        System.out.println("done")
    }*/


}
