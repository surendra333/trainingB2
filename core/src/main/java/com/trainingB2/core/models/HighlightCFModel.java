package com.trainingB2.core.models;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.models.annotations.Via;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class HighlightCFModel extends WCMUsePojo {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(HighlightCFModel.class);
	private String content = "";
	private String address;
	private String name;
	private String heading;
	private String age;
	private Boolean married;
	private String url;
	
	@Inject
	@Via("resource")
	private String lname;

	public String getLname() {
		return lname;
	}



	public String getUrl() {
		return url;
	}



	public String getContent() {
		return content;
	}

	

	public String getHeading() {
		return heading;
	}

	

	

	public Boolean getMarried() {
		return married;
	}



	public String getAddress() {
		return address;
	}



	public String getName() {
		return name;
	}



	public String getAge() {
		return age;
	}



	



	@Override
	public void activate() throws Exception {
		String cfInput = get("cfInput", String.class);
		String variation = "";
		System.out.println(cfInput);

		try {
			variation = get("variation", String.class);
			LOG.info("variation = " + variation);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		cfInput = cfInput + "/jcr:content/data/" + variation;
		try {
			Node cfNode = getResource().getResourceResolver().getResource(cfInput).adaptTo(Node.class);
			if (cfNode != null) {
				content = cfNode.getPath();
				System.out.println("path = "+content);
				if (cfNode.hasProperty("address")) {
					Property pro1 = cfNode.getProperty("address");
					address = pro1.getString();
				}

				if (cfNode.hasProperty("name")) {
					Property pro2 = cfNode.getProperty("name");
					name = pro2.getString();

				}
				if (cfNode.hasProperty("married")) {
					Property pro3 = cfNode.getProperty("married");
					married = pro3.getBoolean();

				}
				

				if (cfNode.hasProperty("heading")) {
					Property pro4 = cfNode.getProperty("heading");
					heading = pro4.getString();

				}

				if (cfNode.hasProperty("age")) {
					Property pro5 = cfNode.getProperty("age");
					age = pro5.getString();

				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			System.out.println("\nend");
		}
	}
}

