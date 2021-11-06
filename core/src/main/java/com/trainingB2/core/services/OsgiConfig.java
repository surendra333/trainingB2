package com.trainingB2.core.services;

public interface OsgiConfig {

	String getServiceName();

	int getServiceCount();

	boolean isLiveData();

	String[] getCountries();

	String getRunmode();


}
