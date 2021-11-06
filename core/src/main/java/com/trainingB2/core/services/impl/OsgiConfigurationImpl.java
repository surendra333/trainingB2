package com.trainingB2.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.trainingB2.core.config.MyOsgiConfigurationDemo;
import com.trainingB2.core.services.OsgiConfigurationModule;


@Component(service=OsgiConfigurationModule.class)
@Designate(ocd=MyOsgiConfigurationDemo.class)
public class OsgiConfigurationImpl implements OsgiConfigurationModule{
 String name;
 int id;
 String url;
 
 @Activate
 protected void activate(MyOsgiConfigurationDemo config){
  name= config.name();
   id= config.id();
  url = config.url();
  
 }
 @Override
public String getName() {
	return name;
}
 @Override
public int getId() {
	return id;
}
 public String getUrl() {
	return url;
}

 
}

