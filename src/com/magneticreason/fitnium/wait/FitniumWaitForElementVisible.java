package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumElementAPI;

public class FitniumWaitForElementVisible extends FitniumWait {
	
	protected final static String MSG = "Element is not visible : ";

	private String locator;
	
	public FitniumWaitForElementVisible ( FitniumFixture fitnium, String loc ) {
		super (fitnium);
		this.locator = loc;
	}
	
	public FitniumWaitForElementVisible ( FitniumFixture fitnium, String loc, boolean test ) {
		super (fitnium, test);
		this.locator = loc;
	}
	
	public void waitWithMessage () {
		super.wait(MSG + locator );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator, millis );
	}
	
	public boolean until () {
		return (test==FitniumElementAPI.isElementVisible( fitnium, this.locator ));
	}
}