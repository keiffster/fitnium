package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForFields extends FitniumWait {
	
	protected static final String MSG = "Fields not present : ";
	
	protected String[] fields;
	
	public FitniumWaitForFields ( FitniumFixture fitnium, String fields ) {
		super (fitnium);
		this.fields = FitniumUtils.stringToArray (fields);
	}

	public void waitWithMessage () {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(fields));
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(fields), millis );
	}

	@Override
	public boolean until () {
		String[] currFields = fitnium.getSelenium().getAllFields();
		
		return (test==FitniumUtils.arrayContainsArray(currFields, fields));
	}
}
