package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForVisible extends FitniumWait {
	
	protected static final String isMSG = "Element never visible : ";
	protected static final String isntMSG = "Element never not visible : ";
	
	protected String locator;
	
	public FitniumWaitForVisible ( FitniumFixture fitnium, String locator ) {
		super (fitnium);
		this.locator = locator;
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
	
	@Override
	public boolean until () {
		return (test==(this.fitnium.getSelenium().isVisible(locator)));
	}
}
