package com.trainingB2.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import com.trainingB2.core.services.OsgiConfig;




@Component(service=OsgiConfig.class,immediate=true)
@Designate(ocd=OsgiConfigImpl.ServiceConfig.class)
public class OsgiConfigImpl implements OsgiConfig{
	
	

	@ObjectClassDefinition(name="AEM-OsgiConfiguration",description="Osgi Configuration Demo")
	
	public @interface ServiceConfig{
		
		@AttributeDefinition(name="ServiceName",description="Enter Service Name",type=AttributeType.STRING)
			
		public String ServiceName()	default "AEM Default Service";
		

		@AttributeDefinition(name="ServiceCount",description="Enter Service Count",type=AttributeType.INTEGER)
			
		 int getServiceCount()	default 5;
			
		@AttributeDefinition(name="LiveData",description="Check Live Data",type=AttributeType.BOOLEAN)
		
		 boolean getLiveData()	default false;
		
		@AttributeDefinition(name="Countries",description="Add countries to run the Services",type=AttributeType.STRING)
		
		 String[] getCountries()	default {"india","america"};
		
		 @AttributeDefinition(name="Runmodes",description="Select Run modes",
				 options={
					@Option(label="Author",value="author")	,
					@Option(label="Publish",value="publish")	,
					@Option(label="Both",value="both")	,
				 },
				 type=AttributeType.STRING)
			
		 String getRunmode() default "both";
	}
	private String ServiceName;
	private int ServiceCount;
	private boolean LiveData;
	private String[] Countries;
	private String Runmode;
	
	
	@Activate
	protected void activate(ServiceConfig serviceConfig){
		ServiceName=serviceConfig.ServiceName();
		ServiceCount=serviceConfig.getServiceCount();
		LiveData=serviceConfig.getLiveData();
		Countries=serviceConfig.getCountries();
		Runmode=serviceConfig.getRunmode();
	}

public String getServiceName() {
		return ServiceName;
	}
public int getServiceCount() {
		return ServiceCount;
	}
public boolean isLiveData() {
		return LiveData;
	}
public String[] getCountries() {
		return Countries;
	}



public String getRunmode() {
		return Runmode;
	}


	
	
}




