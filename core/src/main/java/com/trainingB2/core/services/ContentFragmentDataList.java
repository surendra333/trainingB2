package com.trainingB2.core.services;

import javax.jcr.Node;


import org.apache.sling.api.SlingHttpServletRequest;
import org.json.JSONObject;

public interface ContentFragmentDataList {
	public JSONObject getContentFragmentList(String folderName, SlingHttpServletRequest req, Node node);


}
