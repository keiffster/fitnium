package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumScreenCaptureAPI {

	/**
     * Capture the current screen to a file
     * @param filename Name of file to save screen capture to
     * 
     * <br/><br/>
     * | capture screen to file | filename | 
     */
    public final static void captureScreenToFile ( FitniumFixture fitnium, String filename ) {
    	fitnium.getSelenium().captureScreenshot( FitniumVariableAPI.replaceAnyVars(filename) );
    }

    /**
     * Capture the current screen to a string
     * @return The screen shot as as a base 64 encoded string
     * 
     * <br/><br/>
     * | capture screen as string | 
     */
    public final static String captureScreenAsString ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().captureScreenshotToString();
    }

    /**
     * Stores the screen in a fitnium variable
     * @param name Name of variable to hold screen
     * 
     * <br/><br/>
     * | store screen as string in | name | 
     */
    public final static void storeScreenAsStringIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumScreenCaptureAPI.captureScreenAsString (fitnium) );
    }

    /**
     * Captures the entire html representation of the screen
     * @return html source of screen
     * 
     * <br/><br/>
     * | capture html source |
     */
    public final static String captureHtmlSource ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().getHtmlSource();
    }
    
    /**
     * Stores the entire html representation of the screen in a fitnium variable
     * @param name Name of variable to store source in
     * 
     * <br/><br/>
     * | store html source in | name |
     */
     public final static void storeHtmlSourceIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, fitnium.getSelenium().getHtmlSource() );
    }    

}
