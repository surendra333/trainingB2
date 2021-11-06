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
package com.trainingB2.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.trainingB2.core.beans.QuickLinks;


@Model(adaptables = {Resource.class,SlingHttpServletRequest.class})
public class FooterSectionModel {

@Inject
@Optional
private String dolorText;

@Inject
@Optional
private String subscribeText;

@Inject
@Optional
private String description;





@ChildResource
@Optional
private Resource quickLinks;

@SlingObject
private SlingHttpServletRequest request;

private List<QuickLinks> quickLinkList = new ArrayList<QuickLinks>();

@PostConstruct
protected void init() throws PathNotFoundException, RepositoryException
{
Node quickLinksNode = quickLinks.adaptTo(Node.class);

Iterator<Resource> quickLinkItemNodes = quickLinks.listChildren();

while (quickLinkItemNodes.hasNext()) {
Resource itemResource = quickLinkItemNodes.next();
Node itemNode = itemResource.adaptTo(Node.class);

quickLinkList.add(new QuickLinks(itemNode.getProperty("title").getValue().toString(), itemNode.getProperty("image").getValue().toString()));
}
}

public String getDolorText() {
return dolorText;
}

public void setDolorText(String dolorText) {
this.dolorText = dolorText;
}

public String getSubscribeText() {
return subscribeText;
}

public void setSubscribeText(String subscribeText) {
this.subscribeText = subscribeText;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Resource getQuickLinks() {
return quickLinks;
}

public void setQuickLinks(Resource quickLinks) {
this.quickLinks = quickLinks;
}

public List<QuickLinks> getQuickLinkList() {
return quickLinkList;
}

public void setQuickLinkList(List<QuickLinks> quickLinkList) {
this.quickLinkList = quickLinkList;
}


}



