package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/executeworkflow",
        "sling.servlet.extensions=" + "json"
})
@ServiceDescription("Execute Workflow Model")
public class ExecuteWorkflow extends SlingSafeMethodsServlet {
	 private static final Logger LOG =  LoggerFactory.getLogger(ExecuteWorkflow.class);

	    

@Override
protected void doGet(final SlingHttpServletRequest req,
 final SlingHttpServletResponse resp) throws ServletException, IOException {

	String status="Workflow Executing";
	
	final ResourceResolver resourceResolver=req.getResourceResolver();
	 String payload=req.getRequestParameter("page").getString();
	
	 try{
		 if(StringUtils.isNotBlank(payload)){
		 WorkflowSession workflowSession=resourceResolver.adaptTo(WorkflowSession.class);
		 WorkflowModel workflowModel=workflowSession.getModel("/var/workflow/models/createpageversion");
		 WorkflowData workflowData=workflowSession.newWorkflowData("JCR_PATH", payload);
		 
		 workflowSession.startWorkflow(workflowModel, workflowData).getState();
		 
	 }
	 }catch (Exception e) {
		 LOG.info("Error Workflow{}",e.getMessage());
		
	}
	 resp.setContentType("application/json");
	 resp.getWriter().write(status);
}

}