package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForValue extends FitniumWait {
	
	protected static final String MSG = "Value never found : ";
	
	protected String locator;
	protected String value;
	
	public FitniumWaitForValue ( FitniumFixture fitnium, String locator, String value ) {
		super (fitnium);
		this.locator = locator;
		this.value = value;
	}

	public void waitWithMessage () {
		super.wait(MSG + this.locator + " = " + value );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + this.locator + " = " + value, millis );
	}
	
	@Override
	public boolean until () {
		String str = this.fitnium.getSelenium().getValue(locator);		
		
		if ( FitniumVariableAPI.isRegularExpression(value)) {
    		String regex = FitniumVariableAPI.getRegularExpression (value);
    		return(test==(str.matches(regex)));
    	} else {
    		return (test==(str.equals(value)));
    	}	
	}
}
