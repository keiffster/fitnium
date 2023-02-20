package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForWhetherFrameMatchesExpression extends FitniumWait {
	
	protected static final String MSG = "This frame never matched expression : ";
	
	protected String locator;
	protected String expression;
	
	public FitniumWaitForWhetherFrameMatchesExpression ( FitniumFixture fitnium, String locator, String expression ) {
		super (fitnium);
		this.locator = locator;
		this.expression = expression;
	}

	public void waitWithMessage () {
		super.wait(MSG + this.locator + " = " + expression);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + this.locator + " = " + this.expression, millis );
	}

	@Override
	public boolean until () {
		return (test==(this.fitnium.getSelenium().getWhetherThisFrameMatchFrameExpression(locator, expression)));
	}
}
