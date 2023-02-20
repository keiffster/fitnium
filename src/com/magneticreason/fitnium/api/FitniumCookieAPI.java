package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumCookieAPI {
	
    /****************************************************************************
     * Cookies
     ****************************************************************************/
    

    /**
     * Create a Name Value pair cookie
     * @param name	Name of cookie to create
     * @param value Value of cookie to create
     * 
     * <br/><br/>
     * | create a cookie named | name | with value | value || 
     */
   public final static void createCookieNamedWithValue ( FitniumFixture fitnium, String name, String value ) {
        fitnium.getSelenium().createCookie( FitniumVariableAPI.replaceAnyVars(name)+"="+FitniumVariableAPI.replaceAnyVars(value), "" );
    }

   /**
    * Create a name value pair cookie with a path and maximum age
    * @param name	Name of cookie to create
    * @param value Value of cookie to create
    * @param path	Path of cookie to create
    * @param maxAge Max Age of cookie to create
     * 
     * <br/><br/>
     * | create a cookie named | name | with value | value | and path | path | and max age | age | 
    */
    public final static void createCookieNamedWithValueAndPathAndMaxAge ( FitniumFixture fitnium, String name, String value, String path, String maxAge ) {
        fitnium.getSelenium().createCookie( FitniumVariableAPI.replaceAnyVars(name)+"="+FitniumVariableAPI.replaceAnyVars(value), 
        		FitniumCookieAPI.createCookieOptionString( FitniumVariableAPI.replaceAnyVars(path), FitniumVariableAPI.replaceAnyVars(maxAge) ));
    }
    
    /**
     * Creates a cookie option string, specifically for the createCookie BaseFitniumFixture method
     * 
     * @param path		Name of path to include
     * @param maxAge	Optional maximum age of the cookie
     * @return Cookie option string
     */
    protected final static String createCookieOptionString ( String path, String maxAge ) {
        StringBuffer options = new StringBuffer ();
        boolean addedPath = false;
        if ( null != path && path.length() > 0 ) {
            options.append ( "path=" );
            options.append ( path );
            addedPath = true;
        }
        if ( Integer.parseInt(maxAge) > 0 ) {
            if ( true == addedPath )
                options.append ( ", " );
            options.append ( "max_age=" );
            options.append ( maxAge );
        }
        return options.toString();
    }

    /**
     * Stores the option string of a cookie in a fitnium variable
     * @param path Path of cookie
     * @param maxAge Maximum age of cookie
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * |  store cookie with value | value | and max age | age | option string in | name | 
     */
    public final static void storeCookieWithValueAndMaxAgeOptionStringIn ( FitniumFixture fitnium, String path, String maxAge, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumCookieAPI.createCookieOptionString ( FitniumVariableAPI.replaceAnyVars(path), FitniumVariableAPI.replaceAnyVars(maxAge) ) );
    }
    
    /**
     * Delete a cookie with the specified name
     * @param name Name of cookie to delete
     * 
     * <br/><br/>
     * | delete cookie named | name | 
     * @REGEX
     */
    public final static void deleteCookieNamed ( FitniumFixture fitnium, String name ) {
        fitnium.getSelenium().deleteCookie( FitniumVariableAPI.replaceAnyVars(name), "" );
    }
    
    /**
     * Delete a cookie with the specified name and path
     * @param name Name of cookie to delete
     * @param path Path of cookie to delete
     * 
     * <br/><br/>
     * | delete cookie named  | name | with path | path |
     * @REGEX
     */
    public final static void deleteCookieNamedWithPath ( FitniumFixture fitnium, String name, String path ) {
        fitnium.getSelenium().deleteCookie( FitniumVariableAPI.replaceAnyVars(name), FitniumVariableAPI.replaceAnyVars(path) );
    }

    /**
     * Get the value of all cookies
     * @return Value of cookies
     * 
     * <br/><br/>
     * | value of all cookies | 
     */
    public final static String valueOfAllCookies ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().getCookie();
    }

    /**
     * Check list of all cookies contains passed in values
     * @param List of cookies to check for
     * @return True if all cookies present
     * 
     * <br/><br/>
     * | cookies | cookies | are present | 
     */
    public final static boolean cookiesArePresent ( FitniumFixture fitnium, String list ) {
    	String cookies = fitnium.getSelenium().getCookie();
    	String[] arr1 = FitniumUtils.stringToArray(";", cookies);
    	String[] arr2 = FitniumUtils.stringToArray(list);
    	
    	return FitniumUtils.arrayContainsArray( arr1, arr2 );
    }

    /**
     * Store the value of all cookies in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of all cookies in | name | 
     */
    public final static void storeValueOfAllCookiesIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumCookieAPI.valueOfAllCookies (fitnium) );
    }

    /**
     * Get the value of the cookie
	 * @param name Name of cookie to get value of
     * @return Value of cookie
     * 
     * <br/><br/>
     * | value of cookie named | name | 
     * @REGEX
     */
    public final static String valueOfCookieNamed( FitniumFixture fitnium, String name ) {
    	return fitnium.getSelenium().getCookieByName( FitniumVariableAPI.replaceAnyVars(name) );
    }

    /**
     * Check the value of cookie is as specified
     * @param name Name of cookie to check
     * @param value Value to check
     * @return true or false based on value
     * 
     * <br/><br/>
     * | value of cookie named | name | is | value | 
     * @REGEX
     */
    public final static boolean valueOfCookieNamedIs ( FitniumFixture fitnium, String name, String value ) {
    	if ( FitniumVariableAPI.isRegularExpression(value)) {
    		String regex = FitniumVariableAPI.getRegularExpression (value);
    		return fitnium.getSelenium().getCookieByName(name).matches( FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    	   	return fitnium.getSelenium().getCookieByName(name).equals( FitniumVariableAPI.replaceAnyVars(value));
    	}
    }
    
    /**
     * Store value of cookie in a fitnium variable
     * @param cookieName Name of cookie to store value of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of cookie named | cookie | in | name |
     * @REGEX
     */
    public final static void storeValueOfCookieNamedIn ( FitniumFixture fitnium, String cookieName, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, FitniumCookieAPI.valueOfCookieNamed ( fitnium, FitniumVariableAPI.replaceAnyVars(cookieName) ) );
    }
    
    /**
     * Check if a cookie is present
     * @param name Name of cookie to check for
     * @return True if cookie exists
     * 
     * <br/><br/>
     * | cookie named | name | is present | 
     * @REGEX
     */
    public final static boolean cookieNamedIsPresent ( FitniumFixture fitnium, String name ) {
    	return fitnium.getSelenium().isCookiePresent( FitniumVariableAPI.replaceAnyVars(name) );
    }

    /**
     * Store if a cookie is present in a fitnium variable
     * @param cookie Name of cookie to check existence
     * @param name Name of variable to set the value of
     * 
     * <br/><br/>
     * | store cookie named | cookie | is present in | name | 
     * @REGEX
     */
    public final static void storeCookieNamedIsPresentIn ( FitniumFixture fitnium, String cookie, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name, Boolean.toString(fitnium.getSelenium().isCookiePresent( FitniumVariableAPI.replaceAnyVars(cookie))));
    }

    /**
     * Deletes all visible cookies
     * 
     * <br/><br/>
     * | delete all visible cookies |
     */
    public final static void deleteAllVisibleCookies ( FitniumFixture fitnium ) {
    	fitnium.getSelenium().deleteAllVisibleCookies();
    }
}
