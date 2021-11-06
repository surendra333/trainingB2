package com.trainingB2.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Books {

	@Inject
	private List<MainBooks> mainBooks;
	@Inject
	private String navTitle;

	public String getNavTitle() {
		return navTitle;
	}

	public List<MainBooks> getMainBooks() {
		return mainBooks;
	}

}
