package com.magneticreason.fitnium.wait;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForWindowTitles extends FitniumWait {
	
	protected static final String MSG = "Window titles not present : ";
	
	protected String[] titles;
	
	public FitniumWaitForWindowTitles ( FitniumFixture fitnium, String titles ) {
		super (fitnium);
		this.titles = FitniumUtils.stringToArray (titles);
	}

	public void waitWithMessage () {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(titles));
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(titles), millis );
	}
	
	@Override
	public boolean until () {
		String[] currFields = fitnium.getSelenium().getAllWindowTitles();		
		return (test==FitniumUtils.arrayContainsArray(currFields, titles));
	}


}
