package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumLinkAPI {

	/****************************************************************************
     * Links
     ****************************************************************************/
    
    /**
     * Click a link with the specific text
     * @param text Text of the link to click
     * 
     * <br/><br/>
     * | click link with text | text | 
     */
    public final static void clickLinkWithText ( FitniumFixture fitnium, String text ) {
    	FitniumLinkAPI.clickLink ( fitnium, "link="+FitniumVariableAPI.replaceAnyVars(text) );
    }
    
    /**
     * Click a link identified by the location
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click link | locator | 
     */
    public final static void clickLink ( FitniumFixture fitnium, String loc ) {
        fitnium.getSelenium().click( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Check a link with the specified ID exists
     * @param id ID to check for
	 * @return true of link with id exists 
     * 
     * <br/><br/>
     * | does link with id | id | exist | 
	 */
	public final static boolean doesLinkWithIdExist ( FitniumFixture fitnium, String id ) {
    	return FitniumUtils.arrayContainsString ( fitnium.getSelenium().getAllLinks(), 
    										FitniumVariableAPI.replaceAnyVars(id) );
	}

	/**
	 * Checks if a list of links exist on the screen 
	 * @param ids List of links to check for
	 * @return true or false if all links exist
     * 
     * <br/><br/>
     * | do links with ids | ids | exist | 
	 */
    public final static boolean doLinksWithIdsExist ( FitniumFixture fitnium, String ids ) {
    	String[]arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(ids) );
    	String [] links = fitnium.getSelenium().getAllLinks() ;
    	
    	return FitniumUtils.arrayContainsArray(links, arr);
    }

    /**
     * Stores the ids of all links in a list 
     * @param name Name of variable to store ids
     * 
     * <br/><br/>
     * | store all links in | name | 
     */
    public final static void storeAllLinksIn ( FitniumFixture fitnium, String name ) {
    	String[] links = fitnium.getSelenium().getAllLinks();
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumUtils.createCommaSeperatedList(links) );
    }

}
