package com.trainingB2.core.services;

import javax.jcr.Node;

import org.apache.sling.api.SlingHttpServletRequest;
import org.json.JSONObject;

public interface PathCFData {

	JSONObject getContentFragmentPathData(String cf, SlingHttpServletRequest req, Node node);

}
