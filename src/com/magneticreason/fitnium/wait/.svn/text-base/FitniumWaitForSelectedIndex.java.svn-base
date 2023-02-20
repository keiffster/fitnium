package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForSelectedIndex extends FitniumWait {
	
	protected static final String MSG = "Selected index not found : ";
	
	protected String locator;
	protected String value;
	
	public FitniumWaitForSelectedIndex ( FitniumFixture fitnium, String locator, String value ) {
		super (fitnium);
		this.locator = locator;
		this.value = value;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + value );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + value , millis );
	}

	@Override
	public boolean until () {
		String str = this.fitnium.getSelenium().getSelectedIndex(locator);
		
    	if ( FitniumVariableAPI.isRegularExpression(value)) {
    		String regex = FitniumVariableAPI.getRegularExpression (value);
    		return(test==(str.matches(regex)));
    	} else {
    		return (test==(str.equals(value)));
    	}	
	}
}
