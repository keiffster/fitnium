package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForElementPositionLeft  extends FitniumWait {
	
	protected static final String MSG = "Element left position not found : ";
	
	protected int left;
	protected String locator;
	
	public FitniumWaitForElementPositionLeft ( FitniumFixture fitnium, String locator, String left ) {
		super (fitnium);
		this.locator = locator;
		this.left = Integer.parseInt(left);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + left );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + left , millis );
	}

	@Override
	public boolean until () {
		Number currVal = this.fitnium.getSelenium().getElementPositionLeft(locator);
		return (test==(currVal.intValue()==left));
	}
}
