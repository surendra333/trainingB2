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

import com.trainingB2.core.services.ContentFragmentCreate;
import com.trainingB2.core.services.NodeCreation;
import com.trainingB2.core.services.impl.NodeCreationImpl;



@Component(service = Servlet.class, property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/api/createNode",
        "sling.servlet.extensions=" + "json"
})
public class NodeCreationServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(NodeCreationServlet.class);
	
	 
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		NodeCreation nodeCreation=new NodeCreationImpl();
		LOG.info(nodeCreation.toString());
        JSONObject response=new JSONObject();
        try {
        	LOG.info("hi");
	        resp.setContentType("application/json");
	        String nodename=req.getParameter("node");
	        response = nodeCreation.createNode(req,nodename);
		LOG.info("end");
		LOG.info("res"+response);
        }catch(Exception e) {
			LOG.error("Error while creating Content Fragment", e);
		}
		resp.getWriter().print("created");

	}
		
	}

