package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForLocation extends FitniumWait {
	
	protected static final String MSG = "Location not found : ";
	
	protected String location;
	
	public FitniumWaitForLocation ( FitniumFixture fitnium, String location ) {
		super (fitnium);
		this.location = location;
	}

	public void waitWithMessage () {
		super.wait(MSG+location);
	}

	public void waitWithMessage ( long millis ) {
		super.wait(MSG+location, millis );
	}

	@Override
	public boolean until () {
		String loc = this.fitnium.getSelenium().getLocation();

    	if ( FitniumVariableAPI.isRegularExpression(location)) {
    		String regex = FitniumVariableAPI.getRegularExpression (location);
    		return(test==(loc.matches(regex)));
    	} else {
    		return (test==(loc.equals(location)));
    	}	
	}
}
