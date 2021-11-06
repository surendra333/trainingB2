package com.trainingB2.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.json.JSONObject;

public interface NodeCreation {

	JSONObject createNode(SlingHttpServletRequest req,String name);

	
	
}
