package com.trainingB2.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.ContentFragmentCrudOperations;
import com.trainingB2.core.util.ContentFragmentUtils;
import com.trainingB2.core.utils.PWAConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/api/MFContentFragment", "sling.servlet.extensions=" + "json" })
public class MultifieldContentFragmentField extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 7762806638577908286L;

	private static final Logger LOG = LoggerFactory.getLogger(MultifieldContentFragmentField.class);

	@Reference
	private ContentFragmentCrudOperations contentFragmentData;
	
	  
	
	
	/*
	 * @Reference private transient OSGIConfigService osgiConfigService;
	 */
	 

	private static final String PRODUCT_CODE_FOLDER_PATH = "content-fragments/";
	private static final String PRODUCT_CODE_OFFERS_FOLDER_PATH = "content-fragments/data-details/";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		JSONObject data= new JSONObject();

		try {
			response.setContentType("application/json");
			String selector = request.getRequestURI();
			String offerId = selector.substring(selector.indexOf('.') + 1, selector.lastIndexOf('.'));
			LOG.info("offer" + offerId);

			
			
			
			if (StringUtils.isNotBlank(offerId)) {
				 
				 jsonObject = contentFragmentData.getContentFragment(PRODUCT_CODE_FOLDER_PATH+offerId);
				 if (jsonObject.has(PWAConstants.CONTENT_FRAGMENT_DATA)) {
					 data=ContentFragmentUtils.getData(jsonObject);
				//jsonObject = contentFragmentData.getContentFragment(PRODUCT_CODE_FOLDER_PATH + offerId.toLowerCase());
				LOG.info("hi"+jsonObject);
				
				 
					
				  
				 
					LOG.info("data" + data);
					JSONArray variationData = new JSONArray();

					LOG.info("data" + variationData);

					JSONObject productOfferData = contentFragmentData.getContentFragment(PRODUCT_CODE_OFFERS_FOLDER_PATH + offerId.toLowerCase());
					LOG.info("prodouct" + productOfferData);

					if (productOfferData.has(PWAConstants.CONTENT_FRAGMENT_VARIATIONS)) {
						JSONObject variationList = productOfferData.getJSONObject("variations");

						LOG.info("varaiationlist" + variationList);

						Iterator variationKeys = variationList.keys();

						LOG.info("variations" + variationKeys);

						while (variationKeys.hasNext()) {
							JSONObject keyObject = variationList.getJSONObject(variationKeys.next().toString());

							LOG.info("key " + keyObject);

							variationData.put(keyObject);
						}
						data.put("listItems", variationData);
						LOG.info("listitems"+variationData);
						jsonObject.put("data", data);
						LOG.info("data"+data);

					}
				} else {
					JSONObject failureResponse = new JSONObject();
					failureResponse.put(PWAConstants.ERROR_MSG, "Product code not found");
					jsonObject.put(PWAConstants.ERROR, failureResponse);
				}

			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, PWAConstants.BAD_REQUEST_MESSAGE);
			}

		} catch (JSONException e) {
			LOG.error(PWAConstants.ERROR, e);
			Map<String, Object> hashMap = new HashMap<>();
			hashMap.put(PWAConstants.STATUS, PWAConstants.FAILURE);
			jsonObject = new JSONObject(hashMap);
		}
		jsonObject.remove(PWAConstants.CONTENT_FRAGMENT_NAME);
	//	jsonObject.remove(PWAConstants.CONTENT_FRAGMENT_VARIATIONS);
		jsonObject.remove(PWAConstants.CONTENT_FRAGMENT_DESC);
		jsonObject.remove(PWAConstants.CONTENT_FRAGMENT_TITLE);

		response.getWriter().write(data.toString());
	}
}
