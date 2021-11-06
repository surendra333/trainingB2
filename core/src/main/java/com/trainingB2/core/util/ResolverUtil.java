package com.trainingB2.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResolverUtil {
	private ResolverUtil(){
		
	}

	public static final  String NEW_USER="myuser";
	
	public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
		final Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put(ResourceResolverFactory.SUBSERVICE, NEW_USER);
		ResourceResolver resolver=resourceResolverFactory.getServiceResourceResolver(paraMap);
		return resolver;
		
		
	}
}
