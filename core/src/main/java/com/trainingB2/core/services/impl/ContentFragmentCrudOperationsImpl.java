package com.trainingB2.core.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentVariation;
import com.adobe.cq.dam.cfm.DataType;
import com.adobe.cq.dam.cfm.FragmentData;
import com.trainingB2.core.services.ContentFragmentCrudOperations;
import com.trainingB2.core.utils.TB2Constants;
@Component(service=ContentFragmentCrudOperations.class)
public class ContentFragmentCrudOperationsImpl implements ContentFragmentCrudOperations {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContentFragmentCrudOperationsImpl.class);

	
	
	@Reference
	ResourceResolverFactory resolverFactory;
	
	
	ResourceResolver resolver;
	

	@Override
	public JSONObject getContentFragment(String ContentFragment) {
		Integer n=0;
		LOG.info("hi");
		JSONObject jsonObject=new JSONObject();
		
		JSONObject elements=new JSONObject();
		 JSONObject variation=new JSONObject();
		JSONObject variations=new JSONObject();
		try {
			
			  Map<String, Object> param=new HashMap<String,Object>();
			  param.put(ResourceResolverFactory.SUBSERVICE, TB2Constants.SUB_SERVICE_USER);
			  resolver=resolverFactory.getServiceResourceResolver(param);
			 
			  
		//	resolver = req.getResourceResolver();
			Resource resource=resolver.getResource(TB2Constants.FRAGMENT_PARENT_RESOURCE+ContentFragment);
			LOG.info("res"+resource.getPath());
			if(resource!=null) {
				ContentFragment fragment=resource.adaptTo(ContentFragment.class);
				
				//getmaster values
				Iterator<ContentElement> contentElements=fragment.getElements();
				LOG.info("contentElements"+contentElements);
				while(contentElements.hasNext()) {
					ContentElement contentelement=contentElements.next();
					
					
					
					
					  FragmentData data=contentelement.getValue();
					  
					  DataType dataType = data.getDataType();
					  
					  elements.put(contentelement.getName(),data.getValue().toString());
					  LOG.info("elements"+elements); 
				
					//get variations
					
					  Iterator<ContentVariation> contentVariations=contentelement.getVariations();
					  LOG.info("contentVariations"+contentVariations);
					  
					  while(contentVariations.hasNext()) { 
						  ContentVariation contentVariation=contentVariations.next();
					  
					  LOG.info("contentVariations"+contentVariation); 
					 
					  
					 
					 
					  FragmentData variationData=contentVariation.getValue();
					  
					  
					
					  
					//  String variationDataType=variationData.getDataType().getTypeString();
					  
					  variation.put(contentelement.getName(),variationData.getValue().toString());
					  
					 //.put(contentVariation.getName(), variations);
					  LOG.info("variation"+variation);
					  variations.put(contentVariation.getName(),variation);
					 
					  LOG.info("variations"+variations);
					  }
					
					  
					
					
				//	elements.put(contentelement.getName(),element);
					
					
				}
				elements.put(TB2Constants.CONTENT_FRAGMENT_VARIATIONS,variations);
				jsonObject.put("data", elements);
				 LOG.info("json"+jsonObject);
			}
			
		}catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		}
		
		return jsonObject;
		
	}


}