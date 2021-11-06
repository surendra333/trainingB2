package com.trainingB2.core.services.impl;

import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.FragmentData;
import com.day.cq.commons.jcr.JcrConstants;
import com.trainingB2.core.services.PathCFData;


@Component(service = PathCFData.class)
public class PathCFDataImpl implements PathCFData {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(PathCFDataImpl.class);
	
	private ResourceResolver resolver;
	

	JSONObject jsonObject = new JSONObject();
	JSONArray cfList = new JSONArray();
	
	@Override
	public JSONObject getContentFragmentPathData(String cf, SlingHttpServletRequest req, Node node) {
		LOG.info("hi");
		try {
			if (node.hasNodes()) {
				NodeIterator nodeIterator = node.getNodes();
			
				while (nodeIterator.hasNext()) {
					JSONObject elements = new JSONObject();
					Node currentNode = nodeIterator.nextNode();
					LOG.info("currentnode "+currentNode.getPath());
					
					if (currentNode.getProperty(JcrConstants.JCR_PRIMARYTYPE).getValue().getString().equals("dam:Asset")) {
					
						if (currentNode.hasNode("jcr:content")) {
							
							
							Node cNode = currentNode.getNode("jcr:content");
							LOG.info("curnode "+cNode);
							if (cNode.hasProperty("contentFragment")) {
								
								LOG.info("names "+currentNode.getName());
								elements.put("name ", currentNode.getName());
								
								
								String cPath=currentNode.getPath();
								LOG.info("cpath"+cPath);
								resolver = req.getResourceResolver();
								Resource resource=resolver.getResource(cPath);
								LOG.info("resorce"+resource.getPath());
								
								if(resource!=null) {
									ContentFragment fragment=resource.adaptTo(ContentFragment.class);
									Iterator<ContentElement> iterator=fragment.getElements();
									try {
										while(iterator.hasNext() ) {
											ContentElement element=iterator.next();
											FragmentData data=element.getValue();
											String dataType=data.getDataType().getTypeString();
											LOG.info("dta"+dataType);
											
											switch(dataType)
											{
											case "string":{

												elements.put(element.getName(),data.getValue().toString());
												break;
												
											}
											
											case "long":{

												elements.put(element.getName(),data.getValue().toString());
												break;
												
											}
											case "integer":{

												elements.put(element.getName(),data.getValue().toString());
												break;
												
											}
											
											case "boolean":{

												elements.put(element.getName(),data.getValue().toString());
												break;
												
											}
											

											default:
												LOG.error("error");
												break;
											}
										}
										cfList.put(elements);
							
								}catch(Exception e) {
									LOG.error(e.getMessage());
								}
							}else {
									LOG.error(cf+"notfound");
									jsonObject=null;
								}							
								
							}
						}

					}
				}
				jsonObject.put("data", cfList);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return jsonObject;
	}

}
