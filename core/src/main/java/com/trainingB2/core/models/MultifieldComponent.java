package com.trainingB2.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldComponent {
	
	 @Inject
	public List<NavigationHeader> mainNavigation ;

	public List  getMainNavigation() {
		 
		 return mainNavigation;
	}

	
}
