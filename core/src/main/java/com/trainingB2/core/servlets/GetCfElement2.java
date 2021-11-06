package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
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
import com.trainingB2.core.util.TCPConstants;


@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/api/GetCFElements",
        "sling.servlet.extensions=" + "json"
})
public class GetCfElement2 extends SlingAllMethodsServlet {
	
	 private static final long serialVersionUID = 1L;
	 private static final Logger LOG =  LoggerFactory.getLogger(GetCfElement2.class);

	 @Reference
	 private ContentFragmentVariations contentFragmentVariations;
	 
	  @Override
	    protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException {
		  String cf=req.getParameter("cf");
		 
		  JSONObject result= new JSONObject();
		  JSONObject data=new JSONObject();
		  try {
			  
		  
		   data=contentFragmentVariations.getContentFragment("content-fragments/"+cf);
		  LOG.info("data"+data);
		  JSONObject cfData = data.getJSONObject(TCPConstants.CONTENT_FRAGMENT_DATA);
		  LOG.info("cfdata"+cfData);
	     
		  String actor=cfData.getString("actor");
		  Double age=cfData.getDouble("age");
		  String desciption=cfData.getString("desciption");
		  if((cfData!=null)) {
		      actor="surya";
		      age=26.;
		      desciption="ssssssss";
			  
		
			 cfData.put("actor", actor);
			 cfData.put("age", age);
			 cfData.put("desciption", desciption);
			 LOG.info("name"+actor);
			 result.put("data", cfData);
			  
		  }else {
				result.put(TCPConstants.CODE, 404);
				result.put(TCPConstants.STATUS, TCPConstants.FAILURE);
				result.put(TCPConstants.MESSAGE, TCPConstants.NOT_FOUND);
		  }
	  }catch (Exception e) {
		LOG.error(e.getMessage());
	}
		 
		  resp.getWriter().print(result);
	  }
}
	 
