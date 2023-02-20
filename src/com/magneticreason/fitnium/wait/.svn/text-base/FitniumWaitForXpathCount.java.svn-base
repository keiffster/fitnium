package com.magneticreason.fitnium.wait;

import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForXpathCount extends FitniumWait {
	
	protected static final String MSG = "XPath count not found : ";
	
	protected String xpath;
	protected int count = 0;
	
	public FitniumWaitForXpathCount ( FitniumFixture fitnium, String xpath, String count ) {
		super (fitnium);
		this.xpath = xpath;
		this.count = Integer.parseInt(count);
	}

	public void waitWithMessage () {
		super.wait(MSG + xpath + " = " + count );
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG + xpath + " = " + count, millis );
	}

	@Override
	public boolean until () {
		Number currCount = this.fitnium.getSelenium().getXpathCount(xpath);
		return (test==(currCount.intValue()==count));
	}
}
