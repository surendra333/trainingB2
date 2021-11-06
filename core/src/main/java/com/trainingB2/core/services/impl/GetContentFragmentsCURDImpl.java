package com.trainingB2.core.services.impl;

import java.util.Iterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.FragmentData;
import com.trainingB2.core.services.GetContentFragmentsCURD;

@Component(service = GetContentFragmentsCURD.class)
public class GetContentFragmentsCURDImpl implements GetContentFragmentsCURD {
	
	 private static final Logger LOG = (Logger) LoggerFactory.getLogger(GetContentFragmentsCURDImpl.class);
		
     private ResourceResolver resolver;

	@Override
	public JSONObject getContentFragment(String cf, SlingHttpServletRequest req) {
			String path = "/content/dam/trainingB2/content-fragments/";
			JSONObject jsonObject = new JSONObject();
			JSONObject elements = new JSONObject();
			resolver = req.getResourceResolver();
			
			Resource resource=resolver.getResource(path+cf);
			if(resource!=null) {
				ContentFragment fragment=resource.adaptTo(ContentFragment.class);
				Iterator<ContentElement> iterator=fragment.getElements();
				try {
					while(iterator.hasNext() ) {
						ContentElement element=iterator.next();
						FragmentData data=element.getValue();
						String dataType=data.getDataType().getTypeString();
						
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
					jsonObject.put("", elements);
				}catch(Exception e) {
					LOG.error(e.getMessage());
				}
			}else {
					LOG.error(cf+"notfound");
					jsonObject=null;
				}
				
			
			
		return jsonObject;
	}
}
	

