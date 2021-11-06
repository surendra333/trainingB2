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
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.util.TCPConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/api/DeleteNode", "sling.servlet.extensions=" + "json" })
public class DeleteNodeProperties extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DeleteNodeProperties.class);
	@Reference
	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private Node parentNode;
	
	private Session session;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		JSONObject node = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, TCPConstants.SUB_SERVICE_USER);
			resolver = resolverFactory.getServiceResourceResolver(param);
			session=resolver.adaptTo(Session.class);
			String nodename = req.getParameter("node");
			Resource resource = resolver.getResource(TCPConstants.FRAGMENT_PARENT_RESOURCE + "/" + nodename);
			LOG.info("hi");
			resp.setContentType("application/json");

			parentNode = resource.adaptTo(Node.class);
			parentNode.remove();
			if(parentNode.isNode()) {
				resp.getWriter().print("deleted");
			}
			session.save();
			

		} catch (Exception e) {
			LOG.error("Error while creating Content Fragment", e);
			resp.getWriter().print("not deleted");
		}
		
		session.logout();

	}

}
