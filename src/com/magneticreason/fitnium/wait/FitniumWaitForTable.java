package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForTable  extends FitniumWait {
	
	protected static final String MSG = "Table not found : ";
	
	protected String locator;
	protected String text;
	
	public FitniumWaitForTable ( FitniumFixture fitnium, String text, String locator ) {
		super (fitnium);
		this.locator = locator;
		this.text = text;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + text );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + text , millis );
	}

	@Override
	public boolean until () {
		String str = this.fitnium.getSelenium().getTable(locator);

		if ( FitniumVariableAPI.isRegularExpression(text)) {
    		String regex = FitniumVariableAPI.getRegularExpression (text);
    		return(test==(str.matches(regex)));
    	} else {
    		return (test==(str.equals(text)));
    	}	
	}
}
