package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForSelectedOptions extends FitniumWait {
	
	protected static final String MSG = "Selected options not found : ";
	
	protected String locator;
	protected String[] values;
	
	public FitniumWaitForSelectedOptions ( FitniumFixture fitnium, String locator, String values ) {
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
		String[] options = this.fitnium.getSelenium().getSelectOptions(locator);
		return (test==(FitniumUtils.arrayContainsArray(options, values)));
	}



}
