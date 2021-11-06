package com.trainingB2.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Pages {

	
	@Inject
	private String title;
	
	@Inject
	private List<MainPages> mainPages;

	public String getTitle() {
		return title;
	}

	public List<MainPages> getMainPages() {
		return mainPages;
	}
	
}
