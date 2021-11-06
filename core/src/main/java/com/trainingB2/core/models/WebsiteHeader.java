package com.trainingB2.core.models;


import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class WebsiteHeader {

	@Inject
	@Via("resource")
	private String nav1title;
	
	@ValueMapValue(name = "nav2title")
	private String nav2title;
	
	@ValueMapValue
	private String nav3title;
	
	@ValueMapValue(name = "nav4title")
	private String nav4title;
	
	@ValueMapValue(name = "nav5title")
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
	
}
