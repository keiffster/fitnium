package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForSelectedIndexes extends FitniumWait {
	
	protected static final String MSG = "Selected indexes not found : ";
	
	protected String locator;
	protected String[] values;
	
	public FitniumWaitForSelectedIndexes ( FitniumFixture fitnium, String locator, String values ) {
		super (fitnium);
		this.locator = locator;
		this.values = FitniumUtils.stringToArray(values);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + FitniumUtils.createCommaSeperatedList(values) );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + FitniumUtils.createCommaSeperatedList(values) , millis );
	}

	@Override
	public boolean until () {
		String[] indexes = this.fitnium.getSelenium().getSelectedIndexes(locator);
		return (test==(FitniumUtils.arrayContainsArray(indexes, values)));
	}
}
