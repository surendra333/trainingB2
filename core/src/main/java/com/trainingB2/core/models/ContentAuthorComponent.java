package com.trainingB2.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.ContentFragmentCrudOperations;

@Model(adaptables = SlingHttpServletRequest.class,

defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ContentAuthorComponent {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ContentAuthorComponent.class);
	
	
	//public String cf = "suri";
	
	@SlingObject
	SlingHttpServletRequest req;
	
	@Inject
	@Source("sling-object")
	private ResourceResolver resourceResolver;
	
	@Reference
	private ContentFragmentCrudOperations contentFragmentCrudOperations;
	
	
	@Inject
	@Via("resource")
	private String heading;
	
	@Inject
	@Via("resource")
	private String cfdata;
	
	@Inject
	@Via("resource")
	private String variation;

	
	public String getHeading() {
		return heading;
	}

	



	public String getVariation() {
		return variation;
	}
	
	
	
	
	
	/*
	 * public String getCfData() { cfdata =
	 * contentFragmentCrudOperations.getContentFragment(cfdata, req).toString();
	 * return cfdata; }
	 */





		
		
		 
}
