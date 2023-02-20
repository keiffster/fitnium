package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumWaitForWindowIds extends FitniumWait {
	
	protected static final String MSG = "Window Ids not present : ";
	
	protected String[] ids;
	
	public FitniumWaitForWindowIds ( FitniumFixture fitnium, String ids ) {
		super (fitnium);
		this.ids = FitniumUtils.stringToArray (ids);
	}

	public void waitWithMessage () {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(ids));
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + FitniumUtils.createCommaSeperatedList(ids), millis );
	}
	
	@Override
	public boolean until () {
		String[] currFields = fitnium.getSelenium().getAllWindowIds();	
		
		return (test==FitniumUtils.arrayContainsArray(currFields, ids));
	}

}
