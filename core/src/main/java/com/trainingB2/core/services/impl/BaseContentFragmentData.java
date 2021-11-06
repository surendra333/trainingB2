package com.trainingB2.core.services.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
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
import com.adobe.cq.dam.cfm.DataType;
import com.adobe.cq.dam.cfm.FragmentData;
import com.trainingB2.core.services.ContentFragmentData;
import com.trainingB2.core.services.OSGIConfigService;
import com.trainingB2.core.utils.TB2Constants;

/**
 * 
 * @author arunkumar.p
 * This class is the implementation of ContentFragmentCRUD interface which is used to perform CRUD operations on Content Fragments
 */
@Component(service = ContentFragmentData.class)
@SuppressWarnings({ "AEM Rules:AEM-3" })
public class BaseContentFragmentData implements ContentFragmentData {

	private static final Logger LOG = LoggerFactory.getLogger(BaseContentFragmentData.class);
	private static final String ERROR_MSG = "Error while getting Content Fragment {}";
	@Reference
	private ResourceResolverFactory resolverFactory;

	@Reference
	private OSGIConfigService clientsConfigService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public JSONObject getContentFragment( String contentFragmentName) {
		JSONObject response = new JSONObject();
		ResourceResolver resolver = null;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, TB2Constants.SUB_SERVICE_USER);
			LOG.info("res"+param);
			resolver = resolverFactory.getServiceResourceResolver(param);
			Resource fragmentResource = resolver.getResource(TB2Constants.FRAGMENT_PARENT_RESOURCE+contentFragmentName);
			LOG.info("res"+fragmentResource.getPath());
			if(fragmentResource != null) {
			    ContentFragment fragment = fragmentResource.adaptTo(ContentFragment.class);
			    response.put(TB2Constants.CONTENT_FRAGMENT_NAME, fragment.getName());
			    response.put(TB2Constants.CONTENT_FRAGMENT_TITLE, fragment.getTitle());
			    response.put(TB2Constants.CONTENT_FRAGMENT_DESC, fragment.getDescription());
				Iterator<ContentElement> contentElements= fragment.getElements();
				JSONObject variationsJObjList = new JSONObject();				
				JSONObject masterElement = new JSONObject();
				while(contentElements.hasNext()) {
					ContentElement contentElement = contentElements.next();
					FragmentData  fragmentData  = contentElement.getValue();
					
					LOG.info("fragmentData"+fragmentData.getValue().toString());
					DataType dataType = fragmentData.getDataType();
					LOG.info("dataType"+dataType.getTypeString());
					if(!dataType.isMultiValue()) {
						setSinglevalue(dataType, fragmentData, masterElement, contentElement);
					}
					else {
						setMultivalue(dataType, fragmentData, masterElement, contentElement);
					}
					Iterator<ContentVariation> contentVariations = contentElement.getVariations();
					
					setVariations(contentVariations, contentElement, variationsJObjList);
					LOG.info("varaitions"+contentVariations);
					
				}
				String[] tags = (String[])fragment.getMetaData().get(TB2Constants.CONTENT_FRAGMENT_TAGS_KEY);
			    response.put(TB2Constants.CONTENT_FRAGMENT_TAGS, tags);
			    response.put(TB2Constants.CONTENT_FRAGMENT_DATA, masterElement);
			    response.put(TB2Constants.CONTENT_FRAGMENT_VARIATIONS, variationsJObjList);
			}
		}
		catch(JSONException | LoginException e) {
			LOG.error(ERROR_MSG, e);
		}
		finally {
			if(resolver != null && resolver.isLive()) {
				resolver.close();
			}
		}
		return response;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/*
	 * @Override public JSONObject getTCPContentFragment( String
	 * contentFragmentName) { JSONObject response = new JSONObject();
	 * ResourceResolver resolver = null; try { Map<String, Object> param = new
	 * HashMap<>(); param.put(ResourceResolverFactory.SUBSERVICE,
	 * PWAConstants.SUB_SERVICE_USER); resolver =
	 * resolverFactory.getServiceResourceResolver(param); Resource fragmentResource
	 * = resolver.getResource(contentFragmentName); if(fragmentResource != null) {
	 * ContentFragment fragment = fragmentResource.adaptTo(ContentFragment.class);
	 * response.put(PWAConstants.CONTENT_FRAGMENT_NAME, fragment.getName());
	 * response.put(PWAConstants.CONTENT_FRAGMENT_TITLE, fragment.getTitle());
	 * response.put(PWAConstants.CONTENT_FRAGMENT_DESC, fragment.getDescription());
	 * Iterator<ContentElement> contentElements= fragment.getElements(); JSONObject
	 * variationsJObjList = new JSONObject(); JSONObject masterElement = new
	 * JSONObject(); while(contentElements.hasNext()) { ContentElement
	 * contentElement = contentElements.next(); FragmentData fragmentData =
	 * contentElement.getValue(); DataType dataType = fragmentData.getDataType();
	 * if(!dataType.isMultiValue()) { setSinglevalue(dataType, fragmentData,
	 * masterElement, contentElement); } else { setMultivalue(dataType,
	 * fragmentData, masterElement, contentElement); } Iterator<ContentVariation>
	 * contentVariations = contentElement.getVariations();
	 * setVariations(contentVariations, contentElement, variationsJObjList); }
	 * String[] tags =
	 * (String[])fragment.getMetaData().get(PWAConstants.CONTENT_FRAGMENT_TAGS_KEY);
	 * response.put(PWAConstants.CONTENT_FRAGMENT_TAGS, tags);
	 * response.put(PWAConstants.CONTENT_FRAGMENT_DATA, masterElement);
	 * response.put(PWAConstants.CONTENT_FRAGMENT_VARIATIONS, variationsJObjList); }
	 * } catch(JSONException | LoginException e) { LOG.error(ERROR_MSG, e); }
	 * finally { if(resolver != null && resolver.isLive()) { resolver.close(); } }
	 * return response; }
	 */
	
	private void setMultivalue(DataType variationDataType, FragmentData variationData, JSONObject jObj,
			ContentElement contentElement) throws JSONException, LoginException {
		switch (variationDataType.getTypeString()) {
		case "long":
			evaluateLong(variationData, jObj, contentElement);
			break;
		case "string":
			evaluateString(jObj, variationData, contentElement);
			break;
		default:
			break;
		}

	}

	private void evaluateLong(FragmentData variationData, JSONObject jObj, ContentElement contentElement)
			throws JSONException {
		Long[] longArray = (Long[]) variationData.getValue();
		List<Long> contentValueList = Arrays.asList(longArray != null ? longArray : new Long[0]);
		jObj.put(contentElement.getName(), contentValueList);
	}

	private void evaluateString(JSONObject jObj, FragmentData variationData, ContentElement contentElement)
			throws JSONException, LoginException {
		String[] stringArray = (String[]) variationData.getValue();
		int i = 0;
		if (stringArray != null) {
			for (String data : stringArray) {
				if (data.contains(TB2Constants.FRAGMENT_PARENT_RESOURCE) && data.startsWith(TB2Constants.FRAGMENT_PARENT_RESOURCE) && !isContentFragment(data))
					stringArray[i] = clientsConfigService.getURL() + data;
				i++;
			}
		}
		List<String> contentValueList = Arrays.asList(stringArray != null ? stringArray : new String[0]);
		jObj.put(contentElement.getName(), contentValueList);
	}
	
	private void setSinglevalue(DataType variationDataType, FragmentData  variationData, JSONObject jObj, ContentElement contentElement) throws JSONException, LoginException {
		if("calendar".equalsIgnoreCase(variationDataType.getTypeString()) && variationData.getValue() != null){
			GregorianCalendar date =  (GregorianCalendar) variationData.getValue();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jObj.put(contentElement.getName(), df.format(date.getTime()));

		} else if("boolean".equalsIgnoreCase(variationDataType.getTypeString())) {
			jObj.put(contentElement.getName(), variationData.getValue());
		} else {
			if((variationData.getValue() != null) && (variationData.getValue().toString().contains(TB2Constants.FRAGMENT_PARENT_RESOURCE))&&(variationData.getValue().toString().startsWith(TB2Constants.FRAGMENT_PARENT_RESOURCE) && !isContentFragment(variationData.getValue().toString()))) {
				jObj.put(contentElement.getName(), ((variationData.getValue() != null) ? (clientsConfigService.getURL()+variationData.getValue().toString()) : ""));
			} else {
			jObj.put(contentElement.getName(), ((variationData.getValue() != null) ? (variationData.getValue().toString()) : ""));
			
			}
		}
	}
	
	private void setVariations(Iterator<ContentVariation> contentVariations, ContentElement contentElement, JSONObject variationsJObjList) throws JSONException, LoginException {
		while(contentVariations.hasNext()) {
			ContentVariation contentVariation = contentVariations.next();
			FragmentData  variationData  = contentVariation.getValue();
			DataType variationDataType = variationData.getDataType();
			JSONObject jObj = new JSONObject();

			if(variationDataType.isMultiValue()) {
				setMultivalue(variationDataType, variationData, jObj, contentElement);
			} else {
				setSinglevalue(variationDataType, variationData, jObj, contentElement);
			}
			if(variationsJObjList.has(contentVariation.getName())) {
				JSONObject x = variationsJObjList.getJSONObject(contentVariation.getName());
				x.put(contentElement.getName(), jObj.get(contentElement.getName()));
				variationsJObjList.put(contentVariation.getName(), x);
			}else {
				variationsJObjList.put(contentVariation.getName(), jObj);
			}
		}
	}
	
	private boolean isContentFragment(String path) throws LoginException {
		Map<String, Object> param = new HashMap<>();
		boolean isContentFragment = false;
		param.put(ResourceResolverFactory.SUBSERVICE, TB2Constants.SUB_SERVICE_USER);
		ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);
		Resource contentFragmentJCR = resolver.getResource(path + "/jcr:content");
		Node node = null;
		try {
		if(contentFragmentJCR != null) {
			node = contentFragmentJCR.adaptTo(Node.class);
			if (node != null  && node.hasProperty("contentFragment"))
				isContentFragment = contentFragmentJCR.getValueMap().get("contentFragment", Boolean.class);
		}
		} catch (RepositoryException e) {
			LOG.error(ERROR_MSG, e);
		}
		return isContentFragment;
	}

}
