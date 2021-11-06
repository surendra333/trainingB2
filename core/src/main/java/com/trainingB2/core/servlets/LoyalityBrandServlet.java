package com.trainingB2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.trainingB2.core.services.LoyalityBrandService;
import com.trainingB2.core.utils.PWAConstants;

@Component(service = Servlet.class, property={
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/fragment/loyality",
        "sling.servlet.extensions=" + "json"
})
public class LoyalityBrandServlet extends SlingAllMethodsServlet {
	
	  private static final long serialVersionUID = 7762806638577908286L;

		private static final Logger LOG = LoggerFactory.getLogger(LoyalityBrandServlet.class);


	@Reference
	private LoyalityBrandService loyalityBrandService;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
	try {
		String brandcode=req.getParameter("brandcode");
	//	String tier=req.getParameter("tier");
		//boolean brandCodeWithoutTier=loyalityBrandService.getBrandCodeWithoutTier(brandcode);
		if(StringUtils.isNotBlank(brandcode)) {
			
			resp.getWriter().print(loyalityBrandService.getbrandcontent(brandcode));
		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, PWAConstants.BAD_REQUEST_MESSAGE);
		}
	}catch (Exception e) {
		LOG.error(e.getLocalizedMessage());
	}
	
	}
}
