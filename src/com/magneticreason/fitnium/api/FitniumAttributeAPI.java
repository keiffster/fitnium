package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumAttributeAPI {

	/****************************************************************************
     * Attributes
     ****************************************************************************/
    
   
    /**
     * Check for an atttribute existing with specified name in any window open in browser
     * @return true if attribute exists
     * 
     * <br/><br/>
     * | does attribute with name | name | exist in any window | 
     * @REGEX
     */
    public final static boolean doesAttributeWithNameExistInAnyWindow ( FitniumFixture fitnium, String name ) {
    	String[] names = fitnium.getSelenium().getAttributeFromAllWindows ( FitniumVariableAPI.replaceAnyVars(name) );

    	if ( null != names && names.length > 0 )
    		return true;
    	
    	return false;
    }    

    /**
     * Check for an atttribute existing with specified name in any window open in browser
     * @return true if attribute exists
     * 
     * <br/><br/>
     * | does attribute with name | name | and value | value | exist in any window | 
     * @REGEX
     * @REGEX
     */
    public final static boolean doesAttributeWithNameAndValueExistInAnyWindow ( FitniumFixture fitnium, String name, String value ) {
    	String[] names = fitnium.getSelenium().getAttributeFromAllWindows ( FitniumVariableAPI.replaceAnyVars(name) );

    	if ( null == names || names.length == 0 )
    		return false;
    	
    	String str = FitniumVariableAPI.replaceAnyVars(value);
    	for ( String val : names ) {
        	if ( FitniumVariableAPI.isRegularExpression(str)) {
        		String regex = FitniumVariableAPI.getRegularExpression (str);
        		if ( val.matches(regex))
        			return true;
        	} else {
        		if ( val.equals( str ))
        			return true;
        	}
    	}
    	

    	
    	return false;
    }    

    /**
     * Stores the value of every instance of an attribute of a window as a list in a fitnium variable
     * @param attr Name of attribute to store
     * @param name Name of variable to store value in
     * 
     * <br/><br/>
     * | store attribute | attr | froom all windows in | name | 
     */
    public final static void storeAttributeFromAllWindowsIn ( FitniumFixture fitnium, String attr, String name  ) {
    	String[]attrs = fitnium.getSelenium().getAttributeFromAllWindows ( FitniumVariableAPI.replaceAnyVars(attr) );
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, FitniumUtils.createCommaSeperatedList( attrs ) );
    }
    
    /**
     * Check the value of an attribute is as specific
     * @param loc Locator of element
     * @param value Value to check for
     * @return true if value is as specified
     * 
     * <br/><br/>
     * | value of attribute | locator | is | value | 
     */
    public final static boolean valueOfAttributeIs ( FitniumFixture fitnium, String loc, String value ) {
    	if ( FitniumVariableAPI.isRegularExpression(value)) {
    		String regex = FitniumVariableAPI.getRegularExpression (value);
    		return fitnium.getSelenium().getAttribute( FitniumVariableAPI.replaceAnyVars(loc) ).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return fitnium.getSelenium().getAttribute( FitniumVariableAPI.replaceAnyVars(loc) ).equals(FitniumVariableAPI.replaceAnyVars(value));
    	}
    }

    /**
     * Gets the value of an attribute
     * @param loc Locator of attribute to get value of
     * @return Value of attribute
      * 
     * <br/><br/>
     * | attribute | name | value | 
    */
    public final static String attributeValue ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getAttribute(FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores value of an attribute in a fitnium variable
     * @param loc Locator of attribute 
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store attribute | attr | value in | name | 
     */
    public final static void storeAttributeValueIn ( FitniumFixture fitnium, String loc, String name  ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, FitniumAttributeAPI.attributeValue( fitnium, loc ) );
    }
    
    

}
