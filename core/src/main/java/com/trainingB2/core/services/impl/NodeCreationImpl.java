package com.trainingB2.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.NodeCreation;
import com.trainingB2.core.util.TCPConstants;

@Component(service=NodeCreation.class)
public class NodeCreationImpl implements NodeCreation {
	private static final Logger LOG = LoggerFactory.getLogger(NodeCreationImpl.class);
	
	
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	 private ResourceResolver resolver;
	
	private Session session;
	
	
	
	 
	public JSONObject createNode(SlingHttpServletRequest request,String name) {
		JSONObject response =new JSONObject();
		   
		try {
			 response.put("response", "created");
				/*
				 * Map<String, Object> param=new HashMap<String, Object>();
				 * param.put(ResourceResolverFactory.SUBSERVICE,TCPConstants.SUB_SERVICE_USER);
				 * 
				 * 
				 * resolver=resolverFactory.getServiceResourceResolver(param);
				 */
			
			
			
			resolver=request.getResourceResolver();
			session=resolver.adaptTo(Session.class);
			Resource resource=resolver.getResource("/content/dam/trainingB2");
			
			Node node=resource.adaptTo(Node.class);
			LOG.info("node address");
			LOG.info(node.toString());
			 Node nextbuttonNode=node.addNode(name,"nt:unstructured");
			 nextbuttonNode.setProperty("buttonType", "submit");
			  nextbuttonNode.setProperty("componentName", "coralButton");
			  nextbuttonNode.setProperty("fieldId", "submit");
			  nextbuttonNode.setProperty("fieldLabel", "Next");
			  nextbuttonNode.setProperty("sling:resourceType", "granite/ui/components/coral/foundation/button");
			  Node nextbuttonNode1=nextbuttonNode.addNode("subnode","nt:unstructured");
			  
			nextbuttonNode1.setProperty("no","13");
			  session.save();
				session.refresh(true);
			
		}catch (Exception e) {
			LOG.error(e.getMessage());
			
		}
		
		
		return response;
		
		
	}
}
