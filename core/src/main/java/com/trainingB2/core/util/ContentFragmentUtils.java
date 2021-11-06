package com.trainingB2.core.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.trainingB2.core.utils.PWAConstants;

public class ContentFragmentUtils {

	public static JSONObject getData(JSONObject data) throws JSONException {
		
		return data.getJSONObject(PWAConstants.CONTENT_FRAGMENT_DATA);
	}

}
