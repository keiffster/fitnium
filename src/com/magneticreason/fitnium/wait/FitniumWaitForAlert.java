package com.magneticreason.fitnium.wait;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumJavascriptAPI;

public class FitniumWaitForAlert extends FitniumWait {
	
	protected static final String MSG = "Alert not present";
	
	public FitniumWaitForAlert ( FitniumFixture fitnium ) {
		super (fitnium);
	}

	public void waitWithMessage () {
		super.wait(MSG);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG, millis );
	}

	@Override
	public boolean until () {
            return (test==FitniumJavascriptAPI.hasAlertOccurred (fitnium) );
	}
}