package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumSeleniumAPI {
	
    /**
     * Tell BaseFitniumFixture to start at a specific URL
     * @param url URL to start processing at
     * 
     * <br/><br/>
     * |  starting at url | url | 
     */
    public final static void startingAtURL ( FitniumFixture fitnium, String url ) {
        fitnium.getSelenium().open ( FitniumVariableAPI.replaceAnyVars( url ));
    }

   /**
    * Tell BaseFitniumFixture which speed to run at
    * @param speed Speed in milliseconds between each command
     * 
     * <br/><br/>
     * | set speed to | millis | milliseconds | 
    */
    public final static void setSpeedToMilliseconds ( FitniumFixture fitnium, String speed ) {
    	long millis = Long.parseLong( FitniumVariableAPI.replaceAnyVars( speed ));
        fitnium.getSelenium().setSpeed( Long.toString( millis ) );
    }

    /**
     * Tell BaseFitniumFixture which speed to run at
     * @param speed Speed in seconds between each command
     * 
     * <br/><br/>
     * | set speed to | secs | seconds | 
     */
     public final static void setSpeedToSeconds  ( FitniumFixture fitnium, String speed ) {
     	long secs = Long.parseLong( FitniumVariableAPI.replaceAnyVars( speed ));
        fitnium.getSelenium().setSpeed( Long.toString( secs * 1000L ) );
     }

     /**
     * Tell BaseFitniumFixture how to long to wait before timeout
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | set timeout to | millis | milliseconds | 
     */
    public final static void setTimeoutToMilliseconds ( FitniumFixture fitnium, String timeout ) {
    	long millis = Long.parseLong( FitniumVariableAPI.replaceAnyVars( timeout ));
        fitnium.getSelenium().setTimeout( Long.toString( millis ) );
    }

    /**
     * Tell BaseFitniumFixture how to long to wait before timeout
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | set timeout to | secs | seconds | 
     */
    public final static void setTimeoutToSeconds ( FitniumFixture fitnium, String timeout ) {
    	long secs = Long.parseLong( FitniumVariableAPI.replaceAnyVars( timeout ));
        fitnium.getSelenium().setTimeout( Long.toString( secs * 1000L ) );
    }

    /**
     * Defines a new function for BaseFitniumFixture to locate elements on the page
     * @param strategyName - the name of the strategy to define; this should use only letters [a-zA-Z] with no spaces or other punctuation.
     * @param functionDefinition - a string defining the body of a function in JavaScript. For example: return inDocument.getElementById(locator);
     * 
     * <br/><br/>
     * | add to location strategy | strategy | function | func |
     */
    public final static void addToLocationStrategyFunction( FitniumFixture fitnium, String strategyName, String functionDefinition) {
    	fitnium.getSelenium().addLocationStrategy( FitniumVariableAPI.replaceAnyVars( strategyName ), 
    									   FitniumVariableAPI.replaceAnyVars( functionDefinition ));
    }
    

}
