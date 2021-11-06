package com.trainingB2.core.services.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentVariation;
import com.adobe.cq.dam.cfm.FragmentData;
import com.adobe.cq.dam.cfm.FragmentTemplate;
import com.trainingB2.core.services.ContentFragmentCreate;
import com.trainingB2.core.util.TCPConstants;


@Component(service = ContentFragmentCreate.class)
public class ContentFragmentCreateImpl implements ContentFragmentCreate {
	private static final Logger LOG = LoggerFactory.getLogger(ContentFragmentCreateImpl.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resolver = null;

	private Session session;
	
	@Override
	public JSONObject createContentFragment(String fragmentName, String fragmentDescription, String modelTemplatePath)
			throws JSONException {
		JSONObject response=new JSONObject();
		
		try {
			Map<String, Object> param=new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE,TCPConstants.SUB_SERVICE_USER);
			resolver=resolverFactory.getServiceResourceResolver(param);
			session=resolver.adaptTo(Session.class);
			Resource fragmentModelResource=resolver.getResource(modelTemplatePath);
			LOG.info(fragmentModelResource.getPath());
			FragmentTemplate fragmentTemplate=fragmentModelResource.adaptTo(FragmentTemplate.class);
			Resource parentFragment=resolver.getResource(TCPConstants.FRAGMENT_PARENT_RESOURCE);
			ContentFragment newFragment=fragmentTemplate.createFragment(parentFragment, fragmentName, fragmentDescription);
			newFragment.setDescription("Fragment Created");
			if(newFragment!=null) {
				Iterator<ContentElement> contentElement = newFragment.getElements();
				while(contentElement.hasNext()) {
					ContentElement element = contentElement.next();
					switch (element.getName()) {
					case "name": {
						element.setContent("Satyam Sahoo", element.getContentType());
						break;
					}
					case "multifield": {
						String[] names = new String[] { "satyam", "sahoo", "satyam", "sahoo", "satyam", "sahoo" };
						FragmentData data = element.getValue();
						data.setValue(names);
						element.setValue(data);
						break;
					}
					case "time": {
						FragmentData data = element.getValue();
						data.setValue(Calendar.getInstance());
						element.setValue(data);
						break;
					}
					case "contentReferance": {
						element.setContent("/content/dam", element.getContentType());
						break;
					}
					
					case "married": {
						element.setContent("true", element.getContentType());
						break;
					}
					case "namesMultifield": {

						Double[] d = new Double[] { 10.8, 66.7 };
						FragmentData data = element.getValue();
						data.setValue(d);
						element.setValue(data);
						break;
					}
					case "about": {
						element.setContent("I am Shubham's brother", element.getContentType());
						break;
					}
					case "gender": {
						element.setContent("Male", element.getContentType());
						break;
					}
					case "age": {
						element.setContent("19", element.getContentType());
						break;
					}
					default:
						break;
					}
				}


				}
			newFragment.createVariation("mobile", "Mobile Variation", "Mobile Variation Description");
			newFragment.createVariation("tab", "Tab Variation", "Tab Variation Description");
			newFragment.createVariation("web", "Web Variation", "Web Variation Description");
		//	String[] tags = {"tcp:westside", "tcp:westside/chroma0"};
			//newFragment.setMetaData("cq:tags", tags);
			Iterator<ContentElement> contentElementForVariations = newFragment.getElements();
			while(contentElementForVariations.hasNext()) {
				ContentElement contentElementForVariation=contentElementForVariations.next();
				Iterator<ContentVariation> contentVariations=contentElementForVariation.getVariations();
				while(contentVariations.hasNext()) {
					ContentVariation contentVariation=contentVariations.next();
					switch(contentVariation.getName()) {
					case"mobile":{
						contentVariation.setTitle("Mobile Variation");
						contentVariation.setDescription("Mobile Desciption");
						mobileVariation(contentElementForVariation,contentVariation);
						break;
					}
					case"tab":{
						contentVariation.setTitle("Tab Variation");
						contentVariation.setDescription("Tab Desciption");
						tabVariation(contentElementForVariation,contentVariation);
						break;
					}
					default:{
						contentVariation.setTitle("Web Variation");
						contentVariation.setDescription("Web Desciption");
						webVariation(contentElementForVariation,contentVariation);
						break;
					}	
					}
					}
					
				}
			session.save();
			session.refresh(true);
			response.put("status.message", "Created");
			response.put("status.code", 201);
			
		
		}catch(Exception e) {
		
		
	
		}
		return response;
		
	}

	private void webVariation(ContentElement contentElementForVariation, ContentVariation contentVariation) throws Exception {
		switch (contentElementForVariation.getName()) {
		
		case "title": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Web Variation Data - Title");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "description": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Web Variation Data - Description");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "imagePath": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Web Variation Data - ImagaPath");
			contentVariation.setValue(fragmentData);
			break;
		}
		default: {
			break;
		}
		}

		
	}

	private void tabVariation(ContentElement contentElementForVariation, ContentVariation contentVariation) throws Exception {
		switch (contentElementForVariation.getName()) {
		case "title": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Tab Variation Data - Title");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "description": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Tab Variation Data - Description");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "imagePath": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Tab Variation Data - ImagaPath");
			contentVariation.setValue(fragmentData);
			break;
		}
		default: {
			break;
		}
		}

		
	}

	private void mobileVariation(ContentElement contentElementForVariation, ContentVariation contentVariation) throws Exception {
		switch (contentElementForVariation.getName()) {
		case "title": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Mobile Variation Data - Title");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "description": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Mobile Variation Data - Description");
			contentVariation.setValue(fragmentData);
			break;
		}
		case "imagePath": {
			FragmentData fragmentData = contentVariation.getValue();
			fragmentData.setValue("Mobile Variation Data - ImagaPath");
			contentVariation.setValue(fragmentData);
			break;
		}
		default: {
			break;
		}
		}

		
	}
	
	


}

