package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForWindowNames extends FitniumWait {
	
	protected static final String MSG = "Window names not present : ";
	
	protected String[] names;
	
	public FitniumWaitForWindowNames ( FitniumFixture fitnium, String names ) {
		super (fitnium);
		this.names = FitniumUtils.stringToArray (names);
	}

	public void waitWithMessage () {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList (names) );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(names), millis );
	}
	
	@Override
	public boolean until () {
		String[] currFields = fitnium.getSelenium().getAllWindowNames ();		
		return (test==FitniumUtils.arrayContainsArray(currFields, names));
	}
}
