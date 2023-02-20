package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;

public class FitniumTimeAPI {
	
    /****************************************************************************
     * Timing Functions
     ****************************************************************************/

	public final static String currentTime () {
		return FitniumTimeAPI.formatTime ( System.currentTimeMillis() );
	}
	
	public final static void storeCurrentTimeIn ( FitniumFixture fitnium, String name ) {
		FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Long.toString(System.currentTimeMillis()));
	}
	
	public final static String differenceBetweenAnd ( String time1, String time2 ) {
    	String val1 = FitniumVariableAPI.replaceAnyVars(time1);
    	String val2 = FitniumVariableAPI.replaceAnyVars(time2);
    	
    	return FitniumTimeAPI.formatTime ( Long.parseLong(val1)-Long.parseLong(val2));
	}

	public final static void storeDifferenceBetweenAndIn ( FitniumFixture fitnium, String time1, String time2, String var ) {
    	String val1 = FitniumVariableAPI.replaceAnyVars(time1);
    	String val2 = FitniumVariableAPI.replaceAnyVars(time2);

		FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, var, FitniumTimeAPI.formatTime ( Long.parseLong(val1)-Long.parseLong(val2)));
	}

	protected final static String formatTime ( long millis ) {
		return Long.toString(millis);
	}
	
}
