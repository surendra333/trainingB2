package com.trainingB2.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MainShop {

	@Inject
	private String title;
	
	@Inject
	public List<MainChilds> mainChilds;
	
	public String getTitle() {
		return title;
	}
	public List<MainChilds> getMainChilds() {
		return mainChilds;
	}



	
	
	
	


	
	

	
	
}
