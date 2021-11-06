package com.trainingB2.core.services.impl;

import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;
import com.trainingB2.core.services.DatabaseService;

@Component(service = DatabaseService.class,immediate = true )
public class DatabaseServiceImpl implements DatabaseService {
	
	 private static final Logger LOG = (Logger) LoggerFactory.getLogger(DatabaseService.class);
	 
     @Reference
     DataSourcePool source;
     
     public Connection getConnection(String datasourceName) {
    	 
    	 DataSource dataSource = null;
    	 Connection connection = null;
    	 try
    	 {
    		 LOG.info("inside connection, source is {}", source);
    		 dataSource = (DataSource) source.getDataSource("test");
    		 connection = dataSource.getConnection();
    		 return connection;
    	 } catch(Exception e ) {
    		 LOG.error(e.getMessage());
    	 }
    	 return null;
     }
}
