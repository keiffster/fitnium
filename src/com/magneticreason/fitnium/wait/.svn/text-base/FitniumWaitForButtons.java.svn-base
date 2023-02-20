package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumButtonAPI;

public class FitniumWaitForButtons extends FitniumWait {
	
	protected static final String MSG = "Some ( or all ) buttons not present : ";
	
	protected String ids;
	
	public FitniumWaitForButtons ( FitniumFixture fitnium, String ids ) {
		super (fitnium);
		this.ids = ids;
	}
	
	public void waitWithMessage () {
		super.wait(MSG + ids );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG, millis );
	}
	
	public boolean until () {
		return (test==FitniumButtonAPI.doButtonsWithIdsExist(fitnium, this.ids));
	}
}
