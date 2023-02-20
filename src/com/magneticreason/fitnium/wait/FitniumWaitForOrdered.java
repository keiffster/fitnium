package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForOrdered extends FitniumWait {
	
	protected static final String MSG = "Elements never ordered : ";
	
	protected String locator1;
	protected String locator2;
	
	public FitniumWaitForOrdered ( FitniumFixture fitnium, String locator1, String locator2 ) {
		super (fitnium);
		this.locator1 = locator1;
		this.locator2 = locator2;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator1 + ", " + locator2);
	}

	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator1 + ", " + locator2, millis );
	}

	@Override
	public boolean until () {
		boolean result = this.fitnium.getSelenium().isOrdered ( locator1, locator2 );
		return (test==result);
	}
}
