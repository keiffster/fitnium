package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForElementIndex extends FitniumWait {
	
	protected static final String MSG = "Element index not found : ";
	
	protected String locator;
	protected int index;
	
	public FitniumWaitForElementIndex ( FitniumFixture fitnium, String locator, String index ) {
		super (fitnium);
		this.locator = locator;
		this.index = Integer.parseInt(index);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + index );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + index , millis );
	}

	@Override
	public boolean until () {
		Number currIndex = this.fitnium.getSelenium().getElementIndex(locator);
		return (test==(currIndex.intValue()==index));
	}
}
