package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForAttributeFromAllWindows extends FitniumWait {
	
	protected static final String MSG = "Attribute not present : ";
	
	protected String name;
	
	public FitniumWaitForAttributeFromAllWindows ( FitniumFixture fitnium, String name ) {
		super (fitnium);
		this.name = name;
	}

	public void waitWithMessage () {
		super.wait(MSG + name );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + name, millis );
	}

	@Override
	public boolean until () {
		String[] val = fitnium.getSelenium().getAttributeFromAllWindows(name);	
		return (test==(val.length>0));
	}

}

