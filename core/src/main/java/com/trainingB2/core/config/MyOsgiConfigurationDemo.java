package com.trainingB2.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="MyOsgiConfigurationDemo", description=" my configuration description")
public @interface MyOsgiConfigurationDemo{

@AttributeDefinition(name="StudentName",description="This is the name of the Student")
  String name();

@AttributeDefinition(name="StudentId",description="This is the id of the student")
  int id();

@AttributeDefinition(name="url",description="This is the url of the student")
 String url();


}