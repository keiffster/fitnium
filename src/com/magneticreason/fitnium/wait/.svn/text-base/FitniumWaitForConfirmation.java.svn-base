package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumJavascriptAPI;

public class FitniumWaitForConfirmation extends FitniumWait {
	
	protected final static String MSG = "Confirmation not present";
	
	public FitniumWaitForConfirmation ( FitniumFixture fitnium ) {
		super (fitnium);
	}
	
	public void waitWithMessage () {
		super.wait(MSG);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG, millis );
	}

	public boolean until () {
		return (test==FitniumJavascriptAPI.hasConfirmationOccurred(fitnium));
	}
}