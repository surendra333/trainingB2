package com.trainingB2.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationHeader {

	@ValueMapValue
	private String title;
	
	@ValueMapValue
	private String title1;
	
	@ValueMapValue
	private String href;

	public String getTitle() {
		return title;
	}

	public String getHref() {
		return href;
	}

	public String getTitle1() {
		return title1;
	}
	
	
}
