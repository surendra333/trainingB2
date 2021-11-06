package com.trainingB2.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.json.JSONObject;

public interface GetContentFragmentsCURD {

	public JSONObject getContentFragment(String cf, SlingHttpServletRequest req);

	
}
