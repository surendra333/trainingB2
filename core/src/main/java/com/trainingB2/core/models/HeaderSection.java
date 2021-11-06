package com.trainingB2.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= {Resource.class,SlingHttpServletRequest.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderSection {
	
	@ValueMapValue
	private String nav1title;
	
	@ValueMapValue
	private String logo;
	
	@ValueMapValue
	private String nav2title;
	
	@ValueMapValue
	private String nav3title;
	
	@ValueMapValue
	private String nav4title;
	
	@ValueMapValue
	private String nav5title;

	public String getNav1title() {
		return nav1title;
	}

	public String getNav2title() {
		return nav2title;
	}

	public String getNav3title() {
		return nav3title;
	}

	public String getNav4title() {
		return nav4title;
	}
	
	public String getNav5title() {
		return nav5title;
	}
	
	public String getLogo() {
		return logo;
	}

}
