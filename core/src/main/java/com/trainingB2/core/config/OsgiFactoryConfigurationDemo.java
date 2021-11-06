package com.trainingB2.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="MyOsgiFactoryConfiguration", description=" my configuration description")
public @interface OsgiFactoryConfigurationDemo{

@AttributeDefinition(name="StudentName",description="This is the name of the Student")
  String name();
@AttributeDefinition(name="StudentId",description="This is the id of the student")
  int id();
@AttributeDefinition(name="StudentAge",description="This is the age of the student")
 int age();

@AttributeDefinition(name="Skills",description="Skills known")
String[] skills();

@AttributeDefinition(name="Languages",description="Languages  known")
String[] languages();

@AttributeDefinition(name="Gender",description="gender",
options={
		@Option(label="Male",value="male")	,
		@Option(label="Female",value="female")	,
	 })
String gender();
}
