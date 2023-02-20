package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForElementWidth extends FitniumWait {
	
	protected static final String MSG = "Element width not found : ";
	
	protected String locator;
	protected int width;
	
	public FitniumWaitForElementWidth ( FitniumFixture fitnium, String locator, String width ) {
		super (fitnium);
		this.locator = locator;
		this.width = Integer.parseInt(width);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + width );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + width , millis );
	}

	@Override
	public boolean until () {
		Number currVal = this.fitnium.getSelenium().getElementWidth(locator);
		return (test==(currVal.intValue()==width));
	}
}
