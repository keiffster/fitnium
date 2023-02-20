package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumDebugAPI {

	/****************************************************************************
     * Debug
     ****************************************************************************/
    
    /**
     * Write out a debug message to the console
     * @return nothing
     *
     * <br/><br/>
     * | write to debug | message | 
     */
    public final static void writeToDebug ( FitniumFixture fitnium, String msg ) {
    	msg = FitniumVariableAPI.replaceAnyVars( msg );
        fitnium.getSelenium().setContext( msg );
    }

    /**
     * Sets the threshold for browser-side logging messages; log messages beneath this threshold will be discarded. Valid logLevel strings are: "debug", "info", "warn", "error" or "off".
     * @param level - one of the following: "debug", "info", "warn", "error" or "off"
     * 
     * <br/><br/>
     * | set browser log level | debug info warn error off | 
     */
    public final static void setBrowserLogLevel ( FitniumFixture fitnium, String level ) {
    	fitnium.getSelenium().setBrowserLogLevel( FitniumVariableAPI.replaceAnyVars(level ) );
    }

}
