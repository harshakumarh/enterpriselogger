# Enterprise Logger

This library is specific for logging with Mule 3.8.5 enterprise version

## Build
mvn clean install

This component neither depends on nor ships any extra libraries other than mule defaults

## Usage

* Put enterprise-logger-< version >.jar under ${MULE_HOME}/lib/boot
* Put JDBC driver library under ${MULE_HOME}/lib/boot
* **Move** below Jars from ${MULE_HOME}/lib/opt/ **To** ${MULE_HOME}/lib/boot
    * jackson-annotations-2.6.0.jar
    * jackson-core-2.6.6.jar
    * jackson-databind-2.6.6.jar
    * joda-time-2.9.1.jar

* Add an entry on  ${MULE_HOME}/conf/wrapper.conf 
    * wrapper.java.additional.< n >=-Dlogger.db.props=<dbLogger.properties> **_(IMP: If you enable any of the options on wrapper.conf , 
                                                                                you _must_ change the < n > to be a consecutive number 
                                                                                (based on the number of additional properties) otherwise 
                                                                                Java will not parse this properties file correctly!)_**



Refer [Mule 3.8.5 upgrade With Enterprise logger integration](https://github.com/sandeep-kotha/mule385upgrade) to get started with enterprise logger and Mule application.

Refer [log4j2.xml](https://github.com/sandeep-kotha/mule385upgrade/blob/master/src/main/resources/log4j2.xml) to get started with configuration within your Mule app
