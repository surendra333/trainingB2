package com.trainingB2.core.java;

import javax.jcr.Node;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

public class NodeCreation {
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	ResourceResolver resolver;
	private void addNodeOrComponentInAEM() {
		
	 try {
	  resolver = resolverFactory.getResourceResolver(null);
	  Node jcrContent=resolver.getResource("/content/dam/trainingB2/jcr:content").adaptTo(Node.class);
	  Node nextbuttonNode=jcrContent.addNode("nextButton","nt:unstructured");
	  nextbuttonNode.setProperty("buttonType", "submit");
	  nextbuttonNode.setProperty("componentName", "coralButton");
	  nextbuttonNode.setProperty("fieldId", "submit");
	  nextbuttonNode.setProperty("fieldLabel", "Next");
	  nextbuttonNode.setProperty("sling:resourceType", "granite/ui/components/coral/foundation/button");
	  
	 } catch (Exception e) {
	  
	 } 
	}
	}