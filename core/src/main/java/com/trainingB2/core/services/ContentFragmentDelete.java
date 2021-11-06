package com.trainingB2.core.services;

import org.json.JSONException;
import org.json.JSONObject;

public interface ContentFragmentDelete {

	JSONObject deleteContentFragment(String fragmentName) throws JSONException;

}
