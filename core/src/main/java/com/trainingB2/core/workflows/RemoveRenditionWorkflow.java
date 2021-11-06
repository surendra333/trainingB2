package com.trainingB2.core.workflows;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.logging.Log;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.scf.User;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;


@Component(service=WorkflowProcess.class,immediate=true,
property={
		"process.label"+"= Remove Rendition Process",
		Constants.SERVICE_DESCRIPTION +" = Renditions Workflow Process."
})
public class RemoveRenditionWorkflow implements WorkflowProcess {
	
   private static final Logger LOG =  LoggerFactory.getLogger(RemoveRenditionWorkflow.class);
     @Reference
     private QueryBuilder builder;
     @Reference
     private ResourceResolverFactory resourceResolverFactory;
     public static final  String NEW_USER="myuser";
     
	@Override
	public void execute(WorkItem item, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		final Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put(ResourceResolverFactory.SUBSERVICE, NEW_USER);
		
			try{
				LOG.info("info");
				ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(paraMap); 
				 LOG.info("info1");
	              WorkflowData workflowData = item.getWorkflowData();  
	        String path = workflowData.getPayload().toString(); 
	        
	        path = path.replace("/jcr:content/renditions", "");  
	       
	       Session  session = resourceResolver.adaptTo(Session.class);  
	              Map<String, String> map = new HashMap<String, String>();  
	              map.put("path", path);  
	              map.put("property", "jcr:primaryType");  
	              map.put("property.1_value", "nt:folder"); 
	              LOG.info("info2");
	              Query query = builder.createQuery(PredicateGroup.create(map), session);  
	        SearchResult result = query.getResult(); 
	        
	        List<Hit> hits = result.getHits();  
	        for(Hit hit: hits){  
           	 Resource renditionResource = resourceResolver.resolve(hit.getPath()); 
           	 LOG.info("hitpath"+hit.getPath());
           	 Iterator<Resource> reneditionIterator = renditionResource.listChildren(); 
           	 while(reneditionIterator.hasNext()){  
                    Resource specificResource= reneditionIterator.next(); 
                    Node renditionNode = specificResource.adaptTo(Node.class);
                    LOG.info("Node"+renditionNode.getPath());
                    if(!renditionNode.getName().equals("original")){  
                        renditionNode.remove();  
	                  LOG.info("execute");
	             }  
	        }  
           	 session.save(); 
             session.logout();
	        }       
	         
	}catch(Exception e){
		e.printStackTrace();
	}
	}
		}
	