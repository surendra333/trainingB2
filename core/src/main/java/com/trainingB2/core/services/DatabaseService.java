package com.trainingB2.core.services;

import java.sql.Connection;

public interface DatabaseService {
	
	 public Connection getConnection(String datasourceName);
   	 

}