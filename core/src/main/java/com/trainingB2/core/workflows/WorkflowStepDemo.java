package com.trainingB2.core.workflows;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service=WorkflowProcess.class,immediate=true,
property={
		"process.label"+"= Workflow Step Demo ",
		Constants.SERVICE_VENDOR +"= trainingB2",
		Constants.SERVICE_DESCRIPTION +" = Custom Workflow Step."
})
public class WorkflowStepDemo implements WorkflowProcess {
	private static final Logger LOG =  LoggerFactory.getLogger(WorkflowStepDemo.class);


@Override
public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
	// TODO Auto-generated method stub
	
	LOG.info("\n=====workflowstep===");
	try{
		WorkflowData workflowData=workItem.getWorkflowData();
		
		if(workflowData.getPayloadType().equals("JCR_PATH")){
			Session session=workflowSession.adaptTo(Session.class);
			String path=workflowData.getPayload().toString() + "/jcr:content";
			LOG.info(path);
			Node node=(Node) session.getItem(path);
			String brand=processArguments.get("BRAND", "");
			boolean multinational=processArguments.get("MULTINATIONAL",false);
			LOG.info("\nBRAND:{},MULTINATIONAL:{}",brand,multinational);
			String[] countries=processArguments.get("COUNTRIES", new String[]{});
			for(String country:countries){
				LOG.info("\n COUNTRIES:{}",countries);
			}
}
	}catch(Exception e){
		LOG.info("\n ERROR ",e.getMessage());
}
}
}