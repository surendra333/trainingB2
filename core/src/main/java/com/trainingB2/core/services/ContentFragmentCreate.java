package com.trainingB2.core.services;

import org.json.JSONException;
import org.json.JSONObject;

public interface ContentFragmentCreate {
	
	public JSONObject createContentFragment(String fragmentName, String fragmentDescription, String modelTemplatePath) throws JSONException;

}
