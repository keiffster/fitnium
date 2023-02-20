package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumWindowAPI {
  
	/**
     * Open the specified URL in a new windown and assign the specified ID to the window
     * @param url URL to open
     * @param id ID to assign to window
     * 
     * <br/><br/>
     * | open url | url | in window and assign id | id || 
     */
    public final static void openUrlInWindowAndAssignId ( FitniumFixture fitnium, String url, String id ) {
    	fitnium.getSelenium().openWindow( FitniumVariableAPI.replaceAnyVars(url), FitniumVariableAPI.replaceAnyVars(id) );
    }
    
    /**
     * Set the focus to the currently selected window
     * @return nothing
     * 
     * <br/><br/>
     * | set focus to currently selected window | 
     */
    public final static void setFocusToCurrentlySelectedWindow ( FitniumFixture fitnium ) {
    	fitnium.getSelenium().windowFocus( );
    }
    
    /**
     * Maximize the currently selected window
     * @return nothing
     * 
     * <br/><br/>
     * | maximise currently selected window | 
     */
    public final static void maximiseCurrentlySelectedWindow ( FitniumFixture fitnium ) {
    	fitnium.getSelenium().windowMaximize( );
    }

    /**
     * Select the frame specified by the locator
     * @param loc Locator of frame
     * 
     * <br/><br/>
     * | select frame | locator | 
     */
    public final static void selectFrame ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().selectFrame( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Select the window with the specified ID
     * @param id ID of window to select
     * 
     * <br/><br/>
     * | select window with id | id | 
     */
    public final static void selectWindowWithId( FitniumFixture fitnium, String id ) {
    	fitnium.getSelenium().selectWindow( FitniumVariableAPI.replaceAnyVars(id) );
    }
    
    /**
     * Select the window with the specified title
     * @param title Title of window to select
     * 
     * <br/><br/>
     * | select window with title | title | 
     */
    public final static void selectWindowWithTitle ( FitniumFixture fitnium, String title ) {
    	fitnium.getSelenium().selectWindow ( "title="+FitniumVariableAPI.replaceAnyVars(title) );
    }
    
    /**
     * Select the window with the specified Name
     * @param name Name of window to select
     * 
     * <br/><br/>
     * | select window with name | 
     */
    public final static void selectWindowWithName ( FitniumFixture fitnium, String name ) {
    	fitnium.getSelenium().selectWindow ( "name="+FitniumVariableAPI.replaceAnyVars(name) );   	
    }
    
    /**
     * Select the window with the associated javascript variable
     * @param var Variable of associated window to select
     * 
     * <br/><br/>
     * | select window associated with variable | var | 
     */
    public final static void selectWindowAssociatedWithVariable ( FitniumFixture fitnium, String var ) {
       	fitnium.getSelenium().selectWindow ( "var="+FitniumVariableAPI.replaceAnyVars(var) );    	
    }
    
    /**
     * Checks to see if a window with the specified ID exits
     * @param id ID to check for
     * @return true if window exists with specified id
     * 
     * <br/><br/>
     * | does window with id | id | exist | 
     */
    public final static boolean doesWindowWithIdExist ( FitniumFixture fitnium, String id ) {
    	return FitniumUtils.arrayContainsString ( fitnium.getSelenium().getAllWindowIds(), FitniumVariableAPI.replaceAnyVars(id) );
    }

    /**
     * Do windows with the ids in the list exist
     * @param ids List of ids of windows to search for
     * @return true or false if all windows exist 
     * 
     * <br/><br/>
     * | do windows with ids | ids | exist | 
     */
    public final static boolean doWindowsWithIdsExist ( FitniumFixture fitnium, String ids ) {
    	String[]arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(ids) );
    	return FitniumUtils.compareTwoStringArrays(arr, fitnium.getSelenium().getAllWindowIds());
    }

    /**
     * Stores all window ids in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store all window ids in | name | 
     */
    public final static void storeAllWindowIdsIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumUtils.createCommaSeperatedList(fitnium.getSelenium().getAllWindowIds()) );
    }
    
    /**
     * Checks to see if a window with the specified name exits
     * @param name Name to check for
     * @return true if window exists with specified name
     * 
     * <br/><br/>
     * | dows window with name | name | exist | 
     */
    public final static boolean doesWindowWithNameExist ( FitniumFixture fitnium, String name ) {
    	String[] names = fitnium.getSelenium().getAllWindowNames();
    	
    	return FitniumUtils.arrayContainsString ( names, FitniumVariableAPI.replaceAnyVars(name) );
    }

    /**
     * Checks if windows with names exist 
     * @param names List of names to search for
     * @return true or false if all windows exist
     * 
     * <br/><br/>
     * | do windows with names | names | exist | 
     */
    public final static boolean doWindowsWithNamesExist ( FitniumFixture fitnium, String names ) {
    	String[]arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(names) );
    	return FitniumUtils.compareTwoStringArrays(arr, fitnium.getSelenium().getAllWindowNames());
    }

    /**
     * Stores the names of all windows in a fitnium variable
     * @param name Name of variable to set value
     * 
     * <br/><br/>
     * | store all window names in | names | 
     */
    public final static void storeAllWindowNamesIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumUtils.createCommaSeperatedList(fitnium.getSelenium().getAllWindowNames()) );
    }
       
    /**
     * Checks to see if a window with the specified title exits
     * @param title Title to check for
     * @return true if window exists with specified title
     * 
     * <br/><br/>
     * | does window with title | title | exist | 
     */
    public final static boolean doesWindowWithTitleExist ( FitniumFixture fitnium, String title ) {
    	String[] titles = fitnium.getSelenium().getAllWindowTitles();
    	
    	return FitniumUtils.arrayContainsString ( titles, FitniumVariableAPI.replaceAnyVars(title) );
    }

    /**
     * Checks if windows with all titles exist 
     * @param titles
     * @return true or false if all windows exist
     * 
     * <br/><br/>
     * | do windows with titles | titles | exist | 
     */
    public final static boolean doWindowsWithTitlesExist ( FitniumFixture fitnium, String titles ) {
    	String[]arr = FitniumUtils.stringToArray ( FitniumVariableAPI.replaceAnyVars(titles) );
    	return FitniumUtils.compareTwoStringArrays(arr, fitnium.getSelenium().getAllWindowTitles());
    }

    /**
     * Stores all windows titles in a fitnium variable
     * @param name Name of variable to set
      * 
     * <br/><br/>
     * | store all window titles in | name | 
    */
    public final static void storeAllWindowTitlesIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumUtils.createCommaSeperatedList(fitnium.getSelenium().getAllWindowTitles()) );
    }

    /**
     * Returns the title of the current page
     * @return title of current page
     * 
     * <br/><br/>
     * | title of current page | 
     */
    public final static String titleOfCurrentPage ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().getTitle();
    }

    /**
     * Stores the title of the current page in a fitnium variable 
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store title of current page in | name | 
     */
    public final static void storeTitleOfCurrentPageIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, fitnium.getSelenium().getTitle() );
    }
    
    /**
     * Checks the value of the title of the current page
     * @param text Text to check the title against
     * @return true of text matches title of current page
     * 
     * <br/><br/>
     * | title of current page is | title | 
     */
    public final static boolean titleOfCurrentPageIs ( FitniumFixture fitnium, String text ) {
    	String title = fitnium.getSelenium().getTitle();
    	if ( FitniumVariableAPI.isRegularExpression(text)) {
    		String regex=FitniumVariableAPI.getRegularExpression(text);
        	return title.matches( FitniumVariableAPI.replaceAnyVars(regex) );
    	} else {
        	return title.equals( FitniumVariableAPI.replaceAnyVars(text) );
    	}
    	
    }
    
    /**
     * Get the absolute URL of the current page
     * @return absolute URL of current page
     * 
     * <br/><br/>
     * | absolute url of current page | 
     */
    public final static String absoluteUrlOfCurrentPage ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().getLocation();
    }

    /**
     * Stores the absolute url of current page in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store absolute url of current page in | name | 
     */
    public final static void storeAbsoluteUrlOfCurrentPageIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumWindowAPI.absoluteUrlOfCurrentPage (fitnium) );
    }

    /**
     * Checks that the absolute URL of the current page is as specified
     * @param url URL to check for
     * @return true of URL specified is the absolute of the current page
     * 
     * <br/><br/>
     * | absolute url of current page is | url | 
     */
    public final static boolean absoluteUrlOfCurrentPageIs ( FitniumFixture fitnium, String url ) {
    	if ( FitniumVariableAPI.isRegularExpression(url)) {
    		String regex=FitniumVariableAPI.getRegularExpression(url);
    		return fitnium.getSelenium().getLocation().matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
        	return fitnium.getSelenium().getLocation().equals(FitniumVariableAPI.replaceAnyVars(url));
    	}
    }

}
