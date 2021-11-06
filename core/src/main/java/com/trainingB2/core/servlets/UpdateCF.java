package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.ContentFragmentUpdate;

@Component(service = Servlet.class, property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/trainingB2/fragment/update",
        "sling.servlet.extensions=" + "json"
})
public class UpdateCF extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UpdateCF.class);
	
	@Reference
	private ContentFragmentUpdate contentFragmentUpdate;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject response=new JSONObject();
		
		try {
			resp.setContentType("application/json");
			response = contentFragmentUpdate.updateContentFragment("satyam");
			LOG.info("resp",response);
	}catch(Exception e) {
		LOG.error("error",e);
	}
		resp.getWriter().write(response.toString());
		
	}
}
