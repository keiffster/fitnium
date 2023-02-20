package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumInputFieldAPI {
	
    /****************************************************************************
     * Edit Fields
     ****************************************************************************/
    

    /**
     * Checks to see if an edit field is in fact editable
     * @param loc Locator for edit field
     * @return true if edit field is editable
     * 
     * <br/><br/>
     * | input field | locator | is editable | 
     */
    public final static boolean inputFieldIsEditable ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().isEditable( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Store if an input field is editable in a fitnium variable
     * @param loc Locator of input field to check
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store is input field | locator | editable in | name | 
     */
    public final static void storeInputFieldIsEditableIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, Boolean.toString( FitniumInputFieldAPI.inputFieldIsEditable(fitnium, loc) ));
    }
    
    /**
     * Enters the supplied text into the specified field
	 * @param text Text to enter into field
     * @param loc Locator for edit field
     * 
     * <br/><br/>
     * | enter | text | in input field | locator | 
     */
    public final static void enterInInputField ( FitniumFixture fitnium, String text, String loc ) {
        fitnium.getSelenium().type( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(text) );
    }

    /**
     * Simulates keystroke events on the specified element, as though you typed the value key-by-key. 
     * @param text - the value to type
     * @param loc - an element locator
     * 
     * <br/><br/>
     * | type | text | in input field | locator | 
     */
    public final static void typeInInputField ( FitniumFixture fitnium, String text, String loc ) {
        fitnium.getSelenium().typeKeys( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(text) );
    }
    
    /**
     * Check that the input field with the specified Id exists
     * @param id ID to check for
     * @return true if input field exists
     * 
     * <br/><br/>
     * | does input field with id | id | exist | 
     * @REGEX
     */
    public final static boolean doesInputFieldWithIdExist ( FitniumFixture fitnium, String id ) {
    	return FitniumUtils.arrayContainsString ( fitnium.getSelenium().getAllFields(), 
    										FitniumVariableAPI.replaceAnyVars(id) );
    }

    /**
     * Checks to see if a list of input fields exist
     * @param ids List of input field ids to check existence of
     * @return true of false if all fields exist
     * 
     * <br/><br/>
     * | do input fields with ids | ids | exist | 
     * @REGEX
     */
    public final static boolean doInputFieldsWithIdsExist ( FitniumFixture fitnium, String ids ) {
    	String[]arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(ids) );
        return FitniumUtils.arrayContainsArray ( fitnium.getSelenium().getAllFields(), arr );
    }

    /**
     * Stores all ids of input fields in fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store all fields in | name |  
     */
    public final static void storeAllFieldsIn ( FitniumFixture fitnium, String name ) {
    	String[] ids = fitnium.getSelenium().getAllFields();
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, FitniumUtils.createCommaSeperatedList(ids) );
    }
    

}
