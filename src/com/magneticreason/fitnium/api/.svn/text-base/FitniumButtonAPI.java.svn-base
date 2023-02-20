package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumButtonAPI {
	
    /****************************************************************************
     * Buttons
     ****************************************************************************/
    

    /**
     * Click a button
     * @param loc Locator of element to click
     * <br/><br/>
     * | click button | locator | 
     */
    public final static void clickButton ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().click( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Check a button exists with specified Id
     * @return true if button exists
     * 
     * <br/><br/>
     * | does button with id | id | exist |
     * @REGEX
     */
    public final static boolean doesButtonWithIdExist ( FitniumFixture fitnium, String id ) {
    	String[] ids = fitnium.getSelenium().getAllButtons();
    	
    	return FitniumUtils.arrayContainsString ( ids, FitniumVariableAPI.replaceAnyVars(id) );
    }
    
    /**
     * Checks if buttons with list of ids exist
     * @param ids List of buttons to check existence of
     * @return true or false if buttons exist
     * 
     * <br/><br/>
     * | do buttons with ids | ids | exist | 
     * @REGEX
     */
    public final static boolean doButtonsWithIdsExist ( FitniumFixture fitnium, String ids ) {
    	String[] arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(ids) );
    	String[] butts = fitnium.getSelenium().getAllButtons();
    	return FitniumUtils.arrayContainsArray (butts, arr);
    }

    /**
     * Get ids of all buttons on screen in fitnium variable
     * @param name
     * 
     * <br/><br/>
     * | store all buttons in | name | 
    */
    public final static void storeAllButtonsIn ( FitniumFixture fitnium, String name ) {
    	String[] ids = fitnium.getSelenium().getAllButtons();
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, FitniumUtils.createCommaSeperatedList(ids) );
    }

    

}
