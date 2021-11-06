package com.trainingB2.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MainBooks {

	
	@Inject
	private String titleName;

	public String getTitleName() {
		return titleName;
	}
	
	
}
