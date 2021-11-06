package com.trainingB2.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/renditionworkflow",
        "sling.servlet.extensions=" + "json"
})
@ServiceDescription("Remove Renditions Workflow")
public class RemoveImageRenditions extends SlingSafeMethodsServlet {
	private static final Logger LOG =  LoggerFactory.getLogger(RemoveImageRenditions.class);
	
	@Reference
	private  QueryBuilder builder;
	
	 
	
     @Override
     public void doGet(final SlingHttpServletRequest slingHTTPrequest,final SlingHttpServletResponse response) throws IOException { 
    	 String status="Workflow Executing";
    	 
    	
    	 
    	 LOG.info("hi");
         try{ 
        	 ResourceResolver resourceResolver = slingHTTPrequest.getResourceResolver(); 
             String path = slingHTTPrequest.getParameter("path");  
            Session session = resourceResolver.adaptTo(Session.class);  
           
             Map<String, String> map = new HashMap<String, String>();  
             map.put("path", path);  
             map.put("property", "jcr:primaryType");  
             map.put("property.1_value", "nt:folder");   
			Query query = builder.createQuery(PredicateGroup.create(map), session); 
             SearchResult result = query.getResult();
             List<Hit> hits = result.getHits();  
             for(Hit hit: hits){  
            	 Resource renditionResource = resourceResolver.resolve(hit.getPath());  
            	 Iterator<Resource> reneditionIterator = renditionResource.listChildren(); 
            	 while(reneditionIterator.hasNext()){  
                     Resource specificResource= reneditionIterator.next(); 
                     Node renditionNode = specificResource.adaptTo(Node.class);
                     if(!renditionNode.getName().equals("original")){  
                         renditionNode.remove();  
                      
             }
            	 }
            	 
             }
             session.save(); 
             session.logout();
            
         }catch(Exception e){  
             e.printStackTrace();  
        }  
         response.setContentType("application/json");
         response.getWriter().write(status);
         }
}