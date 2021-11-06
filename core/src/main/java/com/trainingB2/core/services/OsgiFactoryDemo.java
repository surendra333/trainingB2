package com.trainingB2.core.services;

import java.util.List;

public interface OsgiFactoryDemo {

	public String getName();

	public String getGender();

	public String[] getLanguages();

	public String[] getSkills();

	public int getAge();

	public int getId();

	public List<OsgiFactoryDemo> getAllConfigs();

}
