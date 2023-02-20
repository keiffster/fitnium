package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForSelectedValues extends FitniumWait {
	
	protected static final String MSG = "Values never selected : ";
	
	protected String locator;
	protected String[] values;
	
	public FitniumWaitForSelectedValues ( FitniumFixture fitnium, String locator, String values ) {
		super (fitnium);
		this.locator = locator;
		this.values = FitniumUtils.stringToArray(values);
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + FitniumUtils.createCommaSeperatedList(values));
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + " = " + FitniumUtils.createCommaSeperatedList(values), millis );
	}
	
	@Override
	public boolean until () {
		String[] selects = this.fitnium.getSelenium().getSelectedValues(locator);
		if ( null == selects )
			return test ? false : true;
		return (test==(FitniumUtils.arrayContainsArray(selects, values)));
	}
}
