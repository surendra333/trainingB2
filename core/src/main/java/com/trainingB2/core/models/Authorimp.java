package com.trainingB2.core.models;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.trainingB2.core.services.impl.ContentFragmentCrudOperationsImpl;


@Model(adaptables = SlingHttpServletRequest.class,
    adapters=Author.class,
    
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )

public class Authorimp implements Author{
	 private static final Logger LOG = (Logger) LoggerFactory.getLogger(Authorimp.class);
	
	@ScriptVariable
	Page currentPage;
	
	@RequestAttribute(name = "rAttribute")
	private String reqAttribute;
	
	@ResourcePath(path="/content/trainingB2/us/en/homepage")@Via("resource")
	Resource resource;
	
	@Inject
	@Via("resource")
	@Default(values="surendra")
	String name;
	
	@Inject
	@Via("resource")
	@Default(values="surendra")
	String lname;

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getLastName() {
		
		return lname;
	}

	@Override
	public String getPageTitle() {
		
		return currentPage.getTitle();
	}
    @Override
	public String getHomePageName() {
		return resource.getName();
	}

	
    
   @Override
	public String getRequestAttribute() {
		return reqAttribute;
	}

	
	@PostConstruct
    protected void init(){
    	LOG.info("PageTitle{}:{}",currentPage.getTitle(),resource.getPath());
    }
	
}
