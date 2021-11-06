package com.trainingB2.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.trainingB2.core.models.OsgiConfigDemo;
import com.trainingB2.core.services.OsgiConfig;

@Model(adaptables = SlingHttpServletRequest.class,
adapters=OsgiConfigDemo.class,

defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class OsgiConfigModelImpl implements OsgiConfigDemo {
	
@OSGiService
OsgiConfig osgiConfig;

public String getServiceName(){
return osgiConfig.getServiceName();	
}

public int getServiceCount(){
return osgiConfig.getServiceCount();	
}

public boolean isLiveData(){
return osgiConfig.isLiveData();	
}

public String[] getCountries(){
return osgiConfig.getCountries();	
}
public String getRunmode(){
return osgiConfig.getRunmode();	
}
}
