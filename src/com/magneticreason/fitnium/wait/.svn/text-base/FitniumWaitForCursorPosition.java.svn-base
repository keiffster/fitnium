package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForCursorPosition extends FitniumWait {
	
	protected static final String MSG = "Cursor not at position : ";
	
	protected int pos;
	protected String locator;
	
	public FitniumWaitForCursorPosition ( FitniumFixture fitnium, String locator, String pos ) {
		super (fitnium);
		this.pos = Integer.parseInt(pos);
		this.locator = locator;
	}

	public void waitWithMessage () {
		super.wait(MSG + locator + " = " + pos );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + locator + " = " + pos, millis );
	}
	
	@Override
	public boolean until () {
		Number currpos = this.fitnium.getSelenium().getCursorPosition(locator);
		return (test==(pos==currpos.intValue()));
	}
}
