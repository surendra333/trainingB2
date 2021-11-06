package com.trainingB2.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Products {
	
	@Inject
	private String imageFirst;
	
	@Inject
	private String secondImage;
	
	@Inject
	private String topLabel;
	
	@Inject
	private String title;
	
	@Inject
	private String price;

	@Inject
	private String oldPrice;

	public String getImageFirst() {
		return imageFirst;
	}

	public String getSecondImage() {
		return secondImage;
	}

	public String getTopLabel() {
		return topLabel;
	}

	public String getTitle() {
		return title;
	}

	public String getPrice() {
		return price;
	}

	public String getOldPrice() {
		return oldPrice;
	}
	

}
