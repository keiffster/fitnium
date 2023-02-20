package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumElementAPI;

public class FitniumWaitForElement extends FitniumWait {
	
	protected final static String MSG = "Element is not present : ";
	
	private String locator;
	
	public FitniumWaitForElement ( FitniumFixture fitnium, String loc ) {
		super (fitnium);
		this.locator = loc;
	}
	
	public void waitWithMessage () {
		super.wait(MSG + locator );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator, millis );
	}

	public boolean until () {
		return (test==FitniumElementAPI.isElementPresent( this.fitnium, this.locator ));
	}
}