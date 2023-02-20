package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumFormAPI {
	 
    /**
     * Submit the specified form, same as clicking the submit button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | submit form | 
     */
    public final static void submitForm ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().submit( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Submit the specified form, same as clicking the submit button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click submit | 
     */
    public final static void clickSubmit ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().submit( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    

}
