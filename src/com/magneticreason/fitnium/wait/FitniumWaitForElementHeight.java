package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForElementHeight extends FitniumWait {
	
	protected static final String MSG = "Element height not found : ";
	
	protected int height;
	protected String locator;
	
	public FitniumWaitForElementHeight ( FitniumFixture fitnium, String locator, String height ) {
		super (fitnium);
		this.height = Integer.parseInt(height);
		this.locator = locator;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + height );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + height , millis );
	}

	@Override
	public boolean until () {
		Number currVal = this.fitnium.getSelenium().getElementHeight(locator);
		return (test==(currVal.intValue()==height));
	}
}
