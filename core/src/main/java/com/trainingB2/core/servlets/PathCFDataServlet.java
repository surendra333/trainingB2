/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.trainingB2.core.servlets;


import java.io.IOException;

import javax.jcr.Node;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.ContentFragmentDataList;
import com.trainingB2.core.services.PathCFData;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service=Servlet.class,
           property={
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.paths="+ "/bin/api/PathCFData",
                   "sling.servlet.extensions=" + "json"
           })
@ServiceDescription("Path Based Servlet in Content Fragment")
public class PathCFDataServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
 private static final Logger LOG =  LoggerFactory.getLogger(PathCFDataServlet.class);
    
    @Reference
    private PathCFData pathCFData;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
    	String cflist = req.getParameter("cflist");
    	Node node = req.getResourceResolver().getResource(cflist).adaptTo(Node.class);
        
        JSONObject data = pathCFData.getContentFragmentPathData( cflist,req, node);
        LOG.info("data"+data);
       if(data!=null){
      
        resp.getWriter().print(data);
       }
       else
       {
    	   resp.sendError(SlingHttpServletResponse.SC_BAD_REQUEST, "unable to find "+cflist+" content fragment ");
       }
    }
}
   