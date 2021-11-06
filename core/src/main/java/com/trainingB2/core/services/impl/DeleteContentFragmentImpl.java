package com.trainingB2.core.services.impl;

import java.util.HashMap;
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

import com.trainingB2.core.services.ContentFragmentDelete;
import com.trainingB2.core.servlets.CreateCF;
import com.trainingB2.core.util.TCPConstants;
@Component(service=ContentFragmentDelete.class)
public class DeleteContentFragmentImpl implements ContentFragmentDelete {

	private static final Logger LOG = LoggerFactory.getLogger(DeleteContentFragmentImpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;

	ResourceResolver resolver = null;

	private Session session;
	
	
	
	@Override
	public JSONObject deleteContentFragment(String fragmentName) throws JSONException {
		JSONObject response = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, TCPConstants.SUB_SERVICE_USER);
			resolver = resolverFactory.getServiceResourceResolver(param);
			session = resolver.adaptTo(Session.class);
			Resource fragmentResource = resolver.getResource(TCPConstants.FRAGMENT_PARENT_RESOURCE + "/" + fragmentName);
			LOG.info(fragmentResource.getPath());
			resolver.delete(fragmentResource);
			session.save();
			session.refresh(true);
			response.put("status.message", "Deleted");
			response.put("status.code", 200);
		}
		catch (Exception e) {
			response.put("status.message", "Error");
			response.put("status.code", 404);
			LOG.error("Error while creating Content Fragment", e);
		}
		return response;
	}
}

