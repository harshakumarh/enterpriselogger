package com.umg.entlogger;

import com.mchange.v2.c3p0.ComboPooledDataSource

public enum DataSourceConnectionPool {

    INSTANCE;
    private final ComboPooledDataSource cpds

    private DataSourceConnectionPool() {
        this.cpds = initDataSource()
    }

    public ComboPooledDataSource getDataSource() {
        return this.cpds
    }

    private ComboPooledDataSource initDataSource() {
        ComboPooledDataSource _cpds = new ComboPooledDataSource();
        Properties prop = getProperties();
        _cpds.setDriverClass(prop.getProperty("sqlDriverClass")); //loads the jdbc driver
        _cpds.setJdbcUrl(prop.getProperty("jdbcUrl"));
        _cpds.setUser(prop.getProperty("dbUser"));
        _cpds.setPassword(prop.getProperty("dbPassword"));

        // the settings below are optional -- c3p0 can work with defaults
        if (prop.containsKey("minPoolSize")) {
            _cpds.setMinPoolSize(Integer.parseInt(prop.getProperty("minPoolSize")));
            _cpds.setInitialPoolSize(Integer.parseInt(prop.getProperty("minPoolSize")))
        }

        if (prop.containsKey("acquireIncrement"))
            _cpds.setAcquireIncrement(Integer.parseInt(prop.getProperty("acquireIncrement")));
        if (prop.containsKey("maxPoolSize"))
            _cpds.setMaxPoolSize(Integer.parseInt(prop.getProperty("maxPoolSize")));
        if (prop.containsKey("maxStatements"))
            _cpds.setMaxStatements(Integer.parseInt(prop.getProperty("maxStatements")));

        return _cpds
    }

    private Properties getProperties() {

        Reader reader = null;
        try {
            String props = System?.getProperties()?.toString();
            Properties prop = new Properties();
            String url = System.getProperty("logger.db.props");
            reader = new InputStreamReader(new URL(url).openStream(), "UTF-8"); // for example
            prop.load(reader);
            return prop;
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
