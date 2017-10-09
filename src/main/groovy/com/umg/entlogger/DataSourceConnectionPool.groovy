package com.umg.entlogger;

import com.mchange.v2.c3p0.ComboPooledDataSource

public enum DataSourceConnectionPool {

    INSTANCE;
    private final ComboPooledDataSource cpds

    private DataSourceConnectionPool()
    {
          this.cpds=initDataSource()
    }

    public ComboPooledDataSource getDataSource()
    {
        return this.cpds
    }

    private ComboPooledDataSource initDataSource(){
        ComboPooledDataSource _cpds = new ComboPooledDataSource();
        Properties prop = getProperties();
        _cpds.setDriverClass(prop.getProperty("sqlDriverClass")); //loads the jdbc driver
        _cpds.setJdbcUrl(prop.getProperty("jdbcUrl"));
        _cpds.setUser( prop.getProperty("dbUser"));
        _cpds.setPassword(prop.getProperty("dbPassword"));

        // the settings below are optional -- c3p0 can work with defaults
        if(prop.containsKey("MinPoolSize"))
        {
            _cpds.setMinPoolSize(Integer.parseInt(prop.getProperty("MinPoolSize")));
            _cpds.setInitialPoolSize(Integer.parseInt(prop.getProperty("MinPoolSize")))
        }

        if(prop.containsKey("AcquireIncrement"))
            _cpds.setAcquireIncrement(Integer.parseInt(prop.getProperty("AcquireIncrement")));
        if(prop.containsKey("MaxPoolSize"))
            _cpds.setMaxPoolSize(Integer.parseInt(prop.getProperty("MaxPoolSize")));
        if(prop.containsKey("MaxStatements"))
            _cpds.setMaxStatements(Integer.parseInt(prop.getProperty("MaxStatements")));

        return _cpds
    }



    private Properties getProperties(){

        Reader reader = null;
        try {
            String props = System?.getProperties()?.toString();
            println "8888888888888888888888888888888 $props"
            Properties prop = new Properties();
            String url = System.getProperty("logger.db.props");
            println "###################################### $url"
            reader = new InputStreamReader(new URL(url).openStream(), "UTF-8"); // for example
            prop.load(reader);
            return prop;
        } finally {
            if(reader != null)
                reader.close();
        }
    }




}
