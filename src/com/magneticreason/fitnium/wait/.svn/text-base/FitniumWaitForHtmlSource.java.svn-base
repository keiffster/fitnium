package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForHtmlSource extends FitniumWait {
	
	protected static final String MSG = "Source not found : ";
	
	protected String source;
	
	public FitniumWaitForHtmlSource ( FitniumFixture fitnium, String source ) {
		super (fitnium);
		this.source = source;
	}

	public void waitWithMessage () {
		super.wait(MSG + source);
	}

	public void waitWithMessage ( long millis ) {
		super.wait(MSG+source, millis );
	}

	@Override
	public boolean until () {
		String curSrc = this.fitnium.getSelenium().getHtmlSource();
		return (test==(curSrc.contains(source)));
	}
}
