package com.trainingB2.core.workflows;

import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.logging.Log;
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
		"process.label"+"= Geeks Workflow Process",
		Constants.SERVICE_VENDOR +"= trainingB2",
		Constants.SERVICE_DESCRIPTION +" = Custom Workflow Process."
})
public class CustomWorkflowDemo implements WorkflowProcess {
	private static final Logger LOG =  LoggerFactory.getLogger(CustomWorkflowDemo.class);

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		
		LOG.info("info");
		try{
			WorkflowData workflowData=workItem.getWorkflowData();
			
			if(workflowData.getPayloadType().equals("JCR_PATH")){
				Session session=workflowSession.adaptTo(Session.class);
				String path=workflowData.getPayload().toString() + "/jcr:content";
				LOG.info(path);
				Node node=(Node) session.getItem(path);
				String[] processArgs=processArguments.get("PROCESS_ARGS", "String").toString().split(",");
				
				for(String wfArgs:processArgs){
					String[] args=wfArgs.split(":");
					String prop=args[0];
					String value=args[1];
					LOG.info(prop,value);
					if(node!=null){
						node.setProperty(prop,value);
					}
				}
				MetaDataMap wfd=workItem.getWorkflow().getWorkflowData().getMetaDataMap();
				Set<String> keyset=wfd.keySet();
				Iterator<String> i=keyset.iterator();
				while(i.hasNext()){
					String key=i.next();
					
					LOG.info("ITEM key",key,wfd.get(key));
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			LOG.error(e.getMessage());
		}
		LOG.info("suri");
	}

		}
	