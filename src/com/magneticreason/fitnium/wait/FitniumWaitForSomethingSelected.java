package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForSomethingSelected extends FitniumWait {
	
	protected static final String MSG = "Nothing selected : ";
	
	protected String locator;
	
	public FitniumWaitForSomethingSelected ( FitniumFixture fitnium, String locator ) {
		super (fitnium);
		this.locator = locator;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator, millis );
	}

	@Override
	public boolean until () {
		boolean result = this.fitnium.getSelenium().isSomethingSelected(locator);
		return (test== result);
	}
}
