package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumTextAPI;

public class FitniumWaitForTextPresent extends FitniumWait {
	
	protected static final String MSG = "Text not present : ";
	private String text;
	
	public FitniumWaitForTextPresent ( FitniumFixture fitnium, String text ) {
		super (fitnium);
		this.text = text;
	}
	
	public void waitWithMessage () {
		super.wait(MSG + text);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + text, millis );
	}

	public boolean until () {
		return (test==FitniumTextAPI.isTextPresent(fitnium,text));
	}
}