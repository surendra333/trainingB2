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

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentVariation;
import com.adobe.cq.dam.cfm.FragmentData;
import com.trainingB2.core.services.LoyalityBrandService;
import com.trainingB2.core.utils.PWAConstants;

@Component(service=LoyalityBrandService.class)
public class BaseLoyalityBrandService implements LoyalityBrandService{
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	ResourceResolver resolver;
	

	@Override
	public JSONObject getbrandcontent(String brandcode) throws Exception {
		
         JSONObject response=new JSONObject();
	
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, PWAConstants.SUB_SERVICE_USER);
		resolver = resolverFactory.getServiceResourceResolver(param);
		
		Resource fragmentResource=resolver.getResource(PWAConstants.FRAGMENT_PARENT_RESOURCE+brandcode);
		
		if(fragmentResource!=null) {
			ContentFragment fragment=fragmentResource.adaptTo(ContentFragment.class);
			Iterator<ContentElement> contentElements=fragment.getElements();
			while(contentElements.hasNext()) {
				ContentElement contentElement=contentElements.next();
				FragmentData fragmentData=contentElement.getValue();
				response.put(contentElement.getName(), fragmentData.getValue().toString());
				/*
				 * if(fragmentData==null) { response.put(contentElement.getName(),""); }else {
				 * response.put(contentElement.getName(), fragmentData.getValue().toString()); }
				 */
				Iterator<ContentVariation> contentVariations=contentElement.getVariations();
				
			while (contentVariations.hasNext()) {
				ContentVariation contentVariation = contentVariations.next();
				JSONObject benefitsTier = new JSONObject();
				JSONObject membershipTier = new JSONObject();
				FragmentData contentVariationData = contentVariation.getValue();
				response.put(contentElement.getName(),contentVariationData);
				
		}
			}
		}
			
		return response;
	
		
	}

	
	
	
	
	

}