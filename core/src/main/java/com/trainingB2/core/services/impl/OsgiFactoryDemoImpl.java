package com.trainingB2.core.services.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.config.OsgiFactoryConfigurationDemo;
import com.trainingB2.core.services.OsgiFactoryDemo;

@Component(service=OsgiFactoryDemo.class)
@Designate(ocd=OsgiFactoryConfigurationDemo.class,factory=true)
public class OsgiFactoryDemoImpl implements OsgiFactoryDemo {

	//private static final Logger LOGGER =LoggerFactory.getLogger(OsgiFactoryDemoImpl.class);
	
	private String name;
    private	int id;
	private int age;
	private String[] skills;
	private String[] languages;
	private String gender;
	private List<OsgiFactoryDemo> configsList;
	
	@Activate
	@Modified
	public void activate(final OsgiFactoryConfigurationDemo config){
	
		name=config.name();
		id=config.id();
		age=config.age();
		skills=config.skills();
		languages=config.languages();
		gender=config.gender();
		
	}
	@Reference(service=OsgiFactoryDemo.class,cardinality=ReferenceCardinality.MULTIPLE,policy=ReferencePolicy.DYNAMIC)
	public void bindOsgiFactoryConfig(final OsgiFactoryDemo config){
		if(configsList==null){
			configsList= new ArrayList<>();
		}
		configsList.add(config);
	}
	public void unbindOsgiFactoryConfig(final OsgiFactoryDemo config){
		
		configsList.remove(config);
	}
	
   @Override
	public String getName() {
		return name;
	}
   @Override
	public int getId() {
		return id;
	}
   @Override
	public int getAge() {
		return age;
	}
   @Override
	public String[] getSkills() {
		return skills;
	}
   @Override
	public String[] getLanguages() {
		return languages;
	}
   @Override
	public String getGender() {
		return gender;
	}
@Override
public List<OsgiFactoryDemo> getAllConfigs() {
	return configsList;
}
	
}
