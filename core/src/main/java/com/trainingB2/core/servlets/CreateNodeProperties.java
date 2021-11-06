package com.trainingB2.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.trainingB2.core.util.TCPConstants;




@Component(service = Servlet.class, property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/createNode",
        "sling.servlet.extensions=" + "json"
})
public class CreateNodeProperties extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CreateNodeProperties.class);
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	 private ResourceResolver resolver;
	
	private Session session;
	 
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		
        JSONObject jsonObject=new JSONObject();
        try {
        	Map<String, Object> param=new HashMap<String, Object>();
    		param.put(ResourceResolverFactory.SUBSERVICE,TCPConstants.SUB_SERVICE_USER);
    		resolver=resolverFactory.getServiceResourceResolver(param); 
    		session=resolver.adaptTo(Session.class);
    		 Resource resource=resolver.getResource("/apps/trainingB2/components/content/");
        	LOG.info("hi");
	        resp.setContentType("application/json");
	        String nodename=req.getParameter("node");
	        if(resource!=null) {
	       Node parentNode=resource.adaptTo(Node.class);
	        Node nextbuttonNode=parentNode.addNode(nodename,"cq:Component");
	        nextbuttonNode.setProperty("buttonType", "submit");
			  nextbuttonNode.setProperty("componentName", "cButton");
			  nextbuttonNode.setProperty("fieldId", "submit");
			  nextbuttonNode.setProperty("fieldLabel", "Next");
			  nextbuttonNode.setProperty("sling:resourceType", "granite/ui/components/coral/foundation/button");
			  jsonObject.put(nodename, "NewComponent Created");
	        }else {
	        	jsonObject.put(nodename, "failure");
	        }
			  session.save();
			  
				session.refresh(true);
			
		
        }catch(Exception e) {
			LOG.error("Error while creating Content Fragment", e);
		}
		resp.getWriter().print(jsonObject);

	}
		
	}

