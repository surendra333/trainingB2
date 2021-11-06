package com.trainingB2.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= {Resource.class,SlingHttpServletRequest.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterSection {
	
	@ValueMapValue
	private String imageFirst;
	
	
	@ValueMapValue
	private String title1;
	
	@ValueMapValue
	private String title;
	
	@ValueMapValue
	private String title2;
	
	@ValueMapValue
	private String title3;
	@ValueMapValue
	private String desc;

	public String getImageFirst() {
		return imageFirst;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle() {
		return title;
	}

	public String getTitle2() {
		return title2;
	}

	public String getTitle3() {
		return title3;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
