package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumElementAPI;

public class FitniumWaitForElementPresent extends FitniumWait {
	
	protected final static String isMSG = "Element is not visible : ";
	protected final static String isntMSG = "Element is not visible : ";

	private String locator;
	
	public FitniumWaitForElementPresent ( FitniumFixture fitnium, String loc ) {
		super (fitnium);
		this.locator = loc;
	}
	
	public FitniumWaitForElementPresent ( FitniumFixture fitnium, String loc, boolean test ) {
		super (fitnium, test);
		this.locator = loc;
	}
	
	public void waitWithMessage () {
		if(true==test) {
			super.wait(isMSG + locator );
		} else {
			super.wait(isntMSG + locator );
		}
	}
	
	public void waitWithMessage ( long millis ) {
		if(true==test) {
			super.wait(isMSG + locator, millis );
		} else {
			super.wait(isntMSG + locator, millis );
		}
	}
	
	public boolean until () {
		return (test==FitniumElementAPI.isElementPresent( fitnium, this.locator ));
	}
}