package com.trainingB2.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;


import com.trainingB2.core.models.OsgiFactoryModelDemo;
import com.trainingB2.core.services.OsgiFactoryDemo;



@Model(adaptables = SlingHttpServletRequest.class,
		adapters=OsgiFactoryModelDemo.class,

		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

		public class OsgiFactoryConfigurationDemoImpl implements OsgiFactoryModelDemo {
			
		@OSGiService
		OsgiFactoryDemo osgiFactoryDemo;

		@Override
		public List<OsgiFactoryDemo> getAllOSGiConfigs(){
		return osgiFactoryDemo.getAllConfigs();	
		}
}