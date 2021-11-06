package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.ContentFragmentDelete;
import com.trainingB2.core.services.ContentFragmentVariations;



@Component(service = Servlet.class, property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/fragment/delete",
        "sling.servlet.extensions=" + "json"
})
public class DeleteCF extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DeleteCF.class);
	
	@Reference
	private ContentFragmentVariations contentFragmentVariations;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject response=new JSONObject();
		
		
		String day=req.getParameter("day");
		  String hours=req.getParameter("hours");
		  
		  
		  
		  int data = 0;
		  int data1=0;
		  int data2=0;
		 
		 if(Integer.parseInt(day)<=31&&Integer.parseInt(hours)<=24) {
	 
		   data = (Integer.parseInt(day))*Integer.parseInt(hours);
		   data1=(Integer.parseInt(day));
				   data2=Integer.parseInt(hours);
		   try {
			response.put("data", data);
			response.put("day", data1);
			response.put("hours", data2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 }else {
			resp.getWriter().print(false); 
	}
		  
		  
	
      /*  JSONObject response=null;
        try {
			resp.getWriter().print(data); response = contentFragmentDelete.deleteContentFragment(req.getParameter("ContentFragment"));
		}
		catch (Exception e) {
			LOG.error(e.getMessage());
		}
		resp.getWriter().write(response.toString());

	}
*/
resp.getWriter().print(response); 

	}
	}

