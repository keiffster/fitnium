package com.magneticreason.fitnium.wait;


import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForCookiePresent extends FitniumWait {
	
	protected static final String MSG = "Cookie not present : ";
	
	protected String name;
	
	public FitniumWaitForCookiePresent ( FitniumFixture fitnium, String name ) {
		super (fitnium);
		this.name = name;
	}

	public void waitWithMessage () {
		super.wait(MSG + name);
	}
	
	public void waitWithMessage ( long millis ) {
		super.wait(MSG+name, millis );
	}

	@Override
	public boolean until () {
		String cookies = this.fitnium.getSelenium().getCookie();
		
		String[] bits = cookies.split(";");
		for ( String b : bits ) {
			
			String[] split = b.split("=");
	
	    	if ( FitniumVariableAPI.isRegularExpression(name)) {
	    		String regex = FitniumVariableAPI.getRegularExpression (name);
	    		return ( test==split[0].matches(regex));
	    	} else {
	    		return ( test==split[0].equals(name));
	    	}	
		}
		return false;
	}
}
