package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitForBodyText extends FitniumWait {
	
	protected static final String MSG = "Body text not found : ";
	
	protected String text;
	
	public FitniumWaitForBodyText ( FitniumFixture fitnium, String text) {
		super (fitnium);
		this.text = text;
	}

	public void waitWithMessage () {
		super.wait(MSG + text);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG+test, millis );
	}
	
	@Override
	public boolean until () {
		String html = this.fitnium.getSelenium().getBodyText();
		return (test==(html.contains(text)));		
	}

}
