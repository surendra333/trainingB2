package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trainingB2.core.services.GetContentFragmentsCURD;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/api/Get",
        "sling.servlet.extensions=" + "json"
})
@ServiceDescription("Path Based Servlet in Content Fragment")
public class GFCRUD extends SlingSafeMethodsServlet {

private static final long serialVersionUID = 1L;
private static final Logger LOG =  LoggerFactory.getLogger(GFCRUD.class);

@Reference
private GetContentFragmentsCURD getContentFragmentsCURD;

@Override
protected void doGet(final SlingHttpServletRequest req,
 final SlingHttpServletResponse resp) throws ServletException, IOException {
String cf = req.getParameter("cf");

JSONObject data = getContentFragmentsCURD.getContentFragment(cf,req);
LOG.info("data"+data);
if(data!=null){

resp.getWriter().print(data);
}
else
{
resp.sendError(SlingHttpServletResponse.SC_BAD_REQUEST, "unable to find "+cf+" content fragment ");
}
}
}
