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

import com.trainingB2.core.services.ContentFragmentVariations;
import com.trainingB2.core.utils.TB2Constants;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/api/GetCFVariations",
        "sling.servlet.extensions=" + "json"
})
public class GetCFDatawithVariations extends SlingAllMethodsServlet {
	
	 private static final long serialVersionUID = 1L;
	 private static final Logger LOG =  LoggerFactory.getLogger(GetCFDatawithVariations.class);

	 @Reference
	 private ContentFragmentVariations contentFragmentVariations;
	 
	  @Override
	    protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException {
		  String cf=req.getParameter("cf");
		  
		  JSONObject data=new JSONObject();
		  try {
			  
		  
		   data=contentFragmentVariations.getContentFragment(cf);
		  LOG.info("data"+data);
		
		 
		  
	  }catch (Exception e) {
		LOG.error(e.getMessage());
	}
		 
		  resp.getWriter().print(data);
	  }
}
	 
