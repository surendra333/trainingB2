package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.commons.jcr.JcrConstants;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.resourceTypes="+ "trainingB2/components/structure/page", 
       
        "sling.servlet.extensions=" + "txt"
})
@ServiceDescription("Simple Demo Servlet")
public class ResourceTypeServlet extends SlingSafeMethodsServlet {

private static final long serialVersionUID = 1L;

@Override
protected void doGet(final SlingHttpServletRequest req,
 final SlingHttpServletResponse resp) throws ServletException, IOException {
final Resource resource = req.getResource();
String day=req.getParameter("day");
String day1=req.getParameter("day1");

resp.setContentType("text/plain");
resp.getWriter().write((Integer.parseInt(day)+Integer.parseInt(day1)));


}
}
