package com.trainingB2.core.services.impl;

import javax.jcr.Node;

import javax.jcr.NodeIterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.trainingB2.core.services.ContentFragmentDataList;

@Component(service = ContentFragmentDataList.class)
public class ContentFragmentDataListImpl implements ContentFragmentDataList {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ContentFragmentDataListImpl.class);
	
	JSONObject jsonObject = new JSONObject();
	
	

	@Override
	public JSONObject getContentFragmentList(String folderName, SlingHttpServletRequest req, Node node) {
		LOG.info("hi");	
		try {
				if (node.hasNodes()) {
				NodeIterator nodeIterator = node.getNodes();
				LOG.info("nodeiterator",nodeIterator);
				while (nodeIterator.hasNext()) {
					Node currentNode = nodeIterator.nextNode();
					LOG.info("currentnode"+currentNode);
					if (currentNode.getProperty(JcrConstants.JCR_PRIMARYTYPE).getValue().getString().equals("dam:Asset")) {
					
						if (currentNode.hasNode("jcr:content")) {
							
							
							Node cNode = currentNode.getNode("jcr:content");
							LOG.info("curnode",cNode);
							if (cNode.hasProperty("contentFragment")) {
								
								jsonObject.put(currentNode.getName(), currentNode.hashCode());
								
							}
						}

					}
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return jsonObject;
	}

}
