package com.trainingB2.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},


defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Authors {
	
	 private static final Logger LOG = (Logger) LoggerFactory.getLogger(Authors.class);

	 

		@Inject
		@Via("resource")
		@Default(values="surendra")
		String name;
		
		@Inject
		@Via("resource")
		@Default(values="123")
		String id;
		
		
		public String getName() {
			
			return name;
		}

	
		public String getId() {
			
			return id;
		}
}
