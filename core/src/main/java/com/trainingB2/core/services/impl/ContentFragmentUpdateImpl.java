package com.trainingB2.core.services.impl;

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
import com.trainingB2.core.services.ContentFragmentUpdate;
import com.trainingB2.core.util.TCPConstants;

@Component(service=ContentFragmentUpdate.class)
public class ContentFragmentUpdateImpl implements ContentFragmentUpdate {

	private static final Logger LOG = LoggerFactory.getLogger(ContentFragmentUpdateImpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resolver = null;

	private Session session;

	@Override
	public  JSONObject updateContentFragment(String FragmentName) throws JSONException{
	JSONObject response=new JSONObject();
	try {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, TCPConstants.SUB_SERVICE_USER);
		resolver=resolverFactory.getResourceResolver(param);
		session=resolver.adaptTo(Session.class);
		Resource resource=resolver.getResource(TCPConstants.FRAGMENT_PARENT_RESOURCE);
		if(resource!=null) {
			ContentFragment fragment=resource.adaptTo(ContentFragment.class);
			fragment.setDescription("updated");	
			Iterator<ContentElement> contentElements=fragment.getElements();
			while(contentElements.hasNext()) {
				ContentElement contentElement=contentElements.next();
				if (contentElement.getName().equalsIgnoreCase("age"))
					contentElement.setContent("99", contentElement.getContentType());
				if (contentElement.getName().equalsIgnoreCase("name"))
					contentElement.setContent("Satyam Sahoo Updated", contentElement.getContentType());
				Iterator<ContentVariation> contentVariations = contentElement.getVariations();
				while(contentVariations.hasNext()) {
					ContentVariation contentVariation=contentVariations.next();
					if (contentVariation.getName().equalsIgnoreCase("Tab")) {
						if (contentElement.getName().equalsIgnoreCase("age")) {
							FragmentData fragmentData = contentVariation.getValue();
							fragmentData.setValue("48");
							contentVariation.setValue(fragmentData);
							
						}
				}
			}
		}
		}
		session.save();
		session.refresh(true);
		response.put("status.message", "Updated");
		response.put("status.code", 200);
	}catch(Exception e) {
		LOG.error(e.getMessage());
		response.put("status.message", "Error");
		response.put("status.code", 404);
		LOG.error("Error while creating Content Fragment", e);
		
	}
	return response;
}	
}