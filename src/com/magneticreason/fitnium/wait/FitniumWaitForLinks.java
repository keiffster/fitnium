package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForLinks extends FitniumWait {
	
	protected static final String MSG = "Links not present : ";
	
	protected String[] links;
	
	public FitniumWaitForLinks ( FitniumFixture fitnium, String links ) {
		super (fitnium);
		this.links = FitniumUtils.stringToArray (links);
	}

	public void waitWithMessage () {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList (links) );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList (links), millis );
	}

	@Override
	public boolean until () {
		String[] currLinks = fitnium.getSelenium().getAllLinks();
		
		return (test==FitniumUtils.arrayContainsArray(currLinks, links));
	}

}
