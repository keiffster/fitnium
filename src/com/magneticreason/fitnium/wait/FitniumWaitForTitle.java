package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForTitle extends FitniumWait {
	
	protected static final String MSG = "Title never found : ";
	
	protected String title;
	
	public FitniumWaitForTitle ( FitniumFixture fitnium, String title ) {
		super (fitnium);
		this.title = title;
	}

	public void waitWithMessage () {
		super.wait(MSG + title);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + title, millis );
	}

	@Override
	public boolean until () {
		String str = this.fitnium.getSelenium().getTitle();

		if ( FitniumVariableAPI.isRegularExpression(title)) {
    		String regex = FitniumVariableAPI.getRegularExpression (title);
    		return(test==(str.matches(regex)));
    	} else {
    		return (test==(str.equals(title)));
    	}	

	}
}
