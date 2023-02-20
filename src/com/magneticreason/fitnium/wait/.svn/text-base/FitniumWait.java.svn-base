package com.magneticreason.fitnium.wait;

import com.magneticreason.fitnium.FitniumFixture;
import com.thoughtworks.selenium.Wait;

public abstract class FitniumWait extends Wait {
	
	protected FitniumFixture fitnium;
	protected boolean test = true;
	
	public FitniumWait ( FitniumFixture fitnium ) {
		this.fitnium = fitnium;
	}

	public FitniumWait ( FitniumFixture fitnium, boolean test ) {
		this.fitnium = fitnium;
		this.test = test;
	}
	
	public abstract void waitWithMessage ();
	
}