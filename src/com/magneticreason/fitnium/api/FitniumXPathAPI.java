package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumXPathAPI {
	
	protected final static String TRUE = "true";
	protected final static String FALSE = "false";

	/**
	 * Change the Xpath library used by Fitnium, useful IE where the default Selenium
	 * Xpath library is very very slow
	 * 
	 * <br/><br/>
	 * | use xpath library | lib |
	 * e.g. for Explorer
	 * | use xpath library | javascript-xpat |
	 */
	public final static void useXpathLibrary ( FitniumFixture fitnium, String lib ) {
    	fitnium.getSelenium().useXpathLibrary(lib); 
	}
	
	/**
	 * Specifies that BaseFitniumFixture should use the native in-browser implementation of XPath
	 * @return nothing
	 * 
	 * <br/><br/>
	 * | allow native xpath | 
     */
    public final static void allowNativeXpath ( FitniumFixture fitnium ) {
    	fitnium.getSelenium().allowNativeXpath( FitniumXPathAPI.TRUE );
    }

    /**
	 * Specifies that BaseFitniumFixture should NOT use the native in-browser implementation of XPath
	 * @return nothing
     *
	 * <br/><br/>
	 * | disallow native xpath |
     */
    public final static void disallowNativeXpath ( FitniumFixture fitnium ) {
    	fitnium.getSelenium().allowNativeXpath( FitniumXPathAPI.FALSE );
    }

    /**
     * Gets the number of nodes matching an xpath statement
     * @param xpath to match
     * @return Number of nodes matching
     * 
     * <br/><br/>
     * | get number of nodes matching xpath | xpath | 
     */
    public final static int getNumberOfNodesMatchingXpath ( FitniumFixture fitnium, String xpath ) {
    	return fitnium.getSelenium().getXpathCount ( xpath ).intValue();
	}

    /**
     * Checks if the number of nodes matching is as expected
     * @param xpath XPath statement to match
     * @param count Number of matching nodes expected
     * @return true if match is the same
     * 
     * <br/><br/>
     * | number of nodes matching xpath | xpath | is | count |
     */
    public final static boolean numberOfNodesMatchingXpathIs ( FitniumFixture fitnium, String xpath, String count ) {
    	int nodes = fitnium.getSelenium().getXpathCount ( xpath ).intValue();
    	return ( nodes == Integer.parseInt( FitniumVariableAPI.replaceAnyVars( count ) ) );
	}

    /**
     * Stores the number of nodes matching xpath statement in the assigned variable
     * @param xpath XPath statment to match
     * @param name Variable to store value in
     * 
     * <br/><br/>
     * | store number of nodes matching xpath | xpath | in | name | 
     */     
    public final static void storeNumberOfNodesMatchingXpathIn ( FitniumFixture fitnium, String xpath, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, Integer.toString(fitnium.getSelenium().getXpathCount ( xpath ).intValue()) );
	}

}
