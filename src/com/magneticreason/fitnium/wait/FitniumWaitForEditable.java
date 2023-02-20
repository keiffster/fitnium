package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForEditable extends FitniumWait {
	
	protected static final String MSG = "Element never editable : ";
	
	protected String locator;
	
	public FitniumWaitForEditable ( FitniumFixture fitnium, String locator ) {
		super (fitnium);
		this.locator = locator;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator, millis );
	}

	@Override
	public boolean until () {
		return (test==this.fitnium.getSelenium().isEditable(locator));
	}
}
