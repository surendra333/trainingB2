package com.trainingB2.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.trainingB2.core.services.ContentFragmentVariations;
import com.trainingB2.core.utils.PWAConstants;


@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Servlet which is used to get the Offer Details",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/rawData" })
public class SampleDemoServlet extends SlingAllMethodsServlet{

	@Reference
	private  ContentFragmentVariations contentFragmentVariations;
	private static final String DESCRIPTION="description";

	
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
		
		JSONObject jsonObject=new JSONObject();
		JSONObject result=new JSONObject();
		JSONObject rawData=new JSONObject();
		
		try {
		String selector = req.getRequestURI();
		String offerId = selector.substring(selector.indexOf('.') + 1, selector.lastIndexOf('.'));
		
		if(StringUtils.isNotBlank(offerId)) {
			jsonObject=contentFragmentVariations.getContentFragment(offerId);
			
			 if (jsonObject.has(PWAConstants.CONTENT_FRAGMENT_DATA)) {
				//JSONObject data=ContentFragmentUtil.getData(jsonObject);
				
				JSONArray varitionList=new JSONArray();
				try {
					int days=Integer.parseInt(req.getParameter("days"));
					int hours=Integer.parseInt(req.getParameter("hours"));
				JSONObject	jsonVariation=jsonObject.getJSONObject(PWAConstants.CONTENT_FRAGMENT_VARIATIONS);
				
				Iterator<String> contentVaritions=jsonVariation.keys();
				while(contentVaritions.hasNext()) {
					String contentVariation=contentVaritions.next();
					 rawData=jsonVariation.getJSONObject(contentVariation);
					String decdata=jsonVariation.getJSONObject(contentVariation).getString(DESCRIPTION);
					if(decdata.contains("days*hours")) {
					  decdata=decdata.replace("days*hours", String.valueOf(days*hours));
					}
					if(decdata.contains("days&amp;hours")) {
						  decdata=decdata.replace("days&amp;hours", String.valueOf(days)+"&"+String.valueOf(hours));
						}
					rawData.put(DESCRIPTION	, decdata);
					varitionList.put(rawData);
				}
				
				
				}catch (Exception e) {
					e.getLocalizedMessage();
				}
				result.put("result", varitionList);
			
			 }else {
				 result.put("error", false);
			 }
		}
		
	}catch(Exception e) {
		e.getLocalizedMessage();
	}
		resp.getWriter().print(result.toString());
}
}
