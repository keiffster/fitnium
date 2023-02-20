package com.magneticreason.fitnium.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.magneticreason.fitnium.FitniumFixture;

public class FitniumTextAPI {
	 
   /**
    * Check is text present on the screen
    * @param pattern Pattern of text to search for on screen
     * 
     * <br/><br/>
     * | is text | pattern | present | 
    */
    public final static boolean isTextPresent ( FitniumFixture fitnium, String pattern ){
    	if ( FitniumVariableAPI.isRegularExpression(pattern)) {
        	String body = fitnium.getSelenium().getBodyText();
    		String regex = FitniumVariableAPI.getRegularExpression (pattern);
    		Pattern p = Pattern.compile(regex);
    		Matcher m = p.matcher(body);
    		return m.find();
    	} else {
    		return fitnium.getSelenium().isTextPresent( FitniumVariableAPI.replaceAnyVars(pattern) );
    	}
    }

    /**
     * Stores true or false if text pattern is present on screen 
     * @param pattern Pattern to look for
     * @param name Variable to store value in 
      * 
     * <br/><br/>
     * | store is text | text | present in | name | 
     * @REGEX
    */
    public final static void storeIsTextPresentIn ( FitniumFixture fitnium, String pattern, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumTextAPI.isTextPresent( fitnium, FitniumVariableAPI.replaceAnyVars(pattern)) ) );
    }

    /**
     * Stores the content of the HTML body in a fitnium variable
     * @param name Name of variable to store body in 
     * 
     * <br/><br/>
     * | store body text in | name | 
     */
    public final static void storeBodyTextIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, fitnium.getSelenium().getBodyText() );
    }
    
    /**
     * Checks if body text is 
     * @param pattern Text to check for
     * @return true of false if text is available
     * 
     * <br/><br/>
     * | body text  is| text |
     */
    public final static boolean bodyTextIs ( FitniumFixture fitnium, String pattern ) {
    	if ( FitniumVariableAPI.isRegularExpression(pattern)) {
    		return fitnium.getSelenium().getBodyText().matches(FitniumVariableAPI.replaceAnyVars(pattern));
    	} else {
        	return fitnium.getSelenium().getBodyText().equals(FitniumVariableAPI.replaceAnyVars(pattern));
    	}
    }
	    
}
