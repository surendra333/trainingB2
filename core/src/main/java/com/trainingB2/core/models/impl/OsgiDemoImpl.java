package com.trainingB2.core.models.impl;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.trainingB2.core.models.OsgiDemo;
import com.trainingB2.core.services.OsgiConfigurationModule;

@Model(adaptables = SlingHttpServletRequest.class, adapters = OsgiDemo.class,

		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class OsgiDemoImpl implements OsgiDemo {

	@Inject
	@Via("resource")
	private String cardTitle;

	@Inject
	@Via("resource")
	private String buttonText;

	@OSGiService
	OsgiConfigurationModule osgiConfigurationmModel;

	@Override
	public String getName() {
		return osgiConfigurationmModel.getName();
	}

	@Override
	public int getId() {
		return osgiConfigurationmModel.getId();
	}

	@Override
	public String getUrl() {
		return osgiConfigurationmModel.getUrl();
	}

	@Override
	public String getCardTitle() {
		return cardTitle;
	}

	@Override
	public String getButtonText() {
		return buttonText;
	}

}
