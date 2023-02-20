package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForElementPositionTop extends FitniumWait {
	
	protected static final String MSG = "Element top not found : ";
	
	protected int top;
	protected String locator;
	
	public FitniumWaitForElementPositionTop ( FitniumFixture fitnium, String locator, String top ) {
		super (fitnium);
		this.locator = locator;
		this.top = Integer.parseInt(top);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + top );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + top , millis );
	}

	@Override
	public boolean until () {
		Number currVal = this.fitnium.getSelenium().getElementPositionTop(locator);
		return (test==(currVal.intValue()==top));
	}
}
