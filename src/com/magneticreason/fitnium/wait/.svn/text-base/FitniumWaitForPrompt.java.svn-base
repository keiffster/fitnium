package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumJavascriptAPI;

public class FitniumWaitForPrompt extends FitniumWait {
	
	protected final static String MSG = "Prompt not present";

	public FitniumWaitForPrompt ( FitniumFixture fitnium ) {
		super (fitnium);
	}
	
	public void waitWithMessage () {
		super.wait(MSG);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG, millis );
	}

	public boolean until () {
		return (test==FitniumJavascriptAPI.hasPromptOccurred (fitnium));
	}
}