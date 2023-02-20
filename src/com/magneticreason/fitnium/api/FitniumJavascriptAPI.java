package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumJavascriptAPI {
	
    /**
     * Check to see if the last action to occur was a Javascript Alert window 
     * @return true if Alert occurred
     * 
     * <br/><br/>
     * | has alert occurred | 
     */
    public final static boolean hasAlertOccurred ( FitniumFixture fitnium ) {
    	return fitnium.getSelenium().isAlertPresent();
    }

    /**
     * Stores if an alert has occured in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store has alert occurred in | name | 
     */
    public final static void storeHasAlertOccuredIn ( FitniumFixture fitnium , String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumJavascriptAPI.hasAlertOccurred (fitnium)) );
    }
    
    /**
     * Check to see if the last action to occur was a Javascript Prompt window 
     * @return true if Prompt occurred
     * 
     * <br/><br/>
     * | has prompted occurred | 
     */
    public final static boolean hasPromptOccurred (FitniumFixture fitnium ) {
    	return fitnium.getSelenium().isPromptPresent();
    }

    /**
     * Stores if a prompt has occured in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store has prompt occurred in | name | 
     */
    public final static void storeHasPromptOccuredIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumJavascriptAPI.hasPromptOccurred (fitnium)) );
    }
    
    /**
     * Check to see if the last action to occur was a Javascript Confirmation window 
     * @return true if Confirmation occurred
     * 
     * <br/><br/>
     * | has confirmation occurred | 
     */
    public final static boolean hasConfirmationOccurred (FitniumFixture fitnium ) {
    	return fitnium.getSelenium().isConfirmationPresent();
    }

    /**
     * Stores if an confirmation has occured in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store has confirmation occurred in | name | 
     */
    public final static void storeHasConfirmationOccuredIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumJavascriptAPI.hasConfirmationOccurred (fitnium)) );
    }

    /**
     * On the next javascript Prompt window, answer with the specified text
     * @param answer Text to respond to window with
     * 
     * <br/><br/>
     * |  answer | answer | on next prompt | 
     */
    public final static void answerOnNextPrompt ( FitniumFixture fitnium, String answer ) {
        fitnium.getSelenium().answerOnNextPrompt( FitniumVariableAPI.replaceAnyVars(answer) );
    }

    /**
     * On the next confirmation window choose cancel, otherwise the default would be to choose OK
	 * @return nothing
     *
     * <br/><br/>
     * | choose cancel on next confirmation | 
     */
    public final static void chooseCancelOnNextConfirmation(FitniumFixture fitnium) {
        fitnium.getSelenium().chooseCancelOnNextConfirmation();
    }

    /**
     * On the next confirmation window choose ok
	 * @return nothing
     *
     * <br/><br/>
     * | choose ok on next confirmation | 
     */
    public final static void chooseOkOnNextConfirmation(FitniumFixture fitnium) {
        fitnium.getSelenium().chooseOkOnNextConfirmation();
    }

    /**
     * Gets the alert dialog message
     * @return Alert dialog message
     * 
     * <br/><br/>
     * | alert dialog message | 
     */
    public final static String alertDialogMessage (FitniumFixture fitnium) {
    	return fitnium.getSelenium().getAlert();
    }

    /**
     * Checks if the alert message is as specified
     * @param message Text of message to check 
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | alert dialog message is | message | 
     */
    public final static boolean alertDialogMessageIs ( FitniumFixture fitnium, String message ) {
    	if ( FitniumVariableAPI.isRegularExpression(message)) {
    		String regex = FitniumVariableAPI.getRegularExpression (message);
    		return FitniumJavascriptAPI.alertDialogMessage(fitnium).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
        	return FitniumJavascriptAPI.alertDialogMessage(fitnium).equals ( FitniumVariableAPI.replaceAnyVars(message));
    	}
    }

    /**
     * Stores alert message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store alert dialog message in | name | 
     */
    public final static void storeAlertDialogMessageIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumJavascriptAPI.alertDialogMessage (fitnium) );
    }

    /**
     * Gets the confirmation message
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | confirmation dialog message is | message | 
     */
    public final static String confirmationDialogMessage (FitniumFixture fitnium) {
    	return fitnium.getSelenium().getConfirmation();
    }
    
    /**
     * Stores confirmation message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store confirmation dialog message in | name | 
     */
    public final static void storeConfirmationDialogMessageIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumJavascriptAPI.confirmationDialogMessage (fitnium) );
    }
    
    /**
     * Gets the prompt message
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | prompt dialog message is | message | 
     */
    public final static String promptDialogMessage (FitniumFixture fitnium) {
    	return fitnium.getSelenium().getPrompt();
    }

    /**
     * Stores prompt message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store prompt dialog message in | name | 
     */
    public final static void storePromptDialogMessageIn ( FitniumFixture fitnium, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumJavascriptAPI.promptDialogMessage (fitnium) );
    }
    
    /**
     * Closes the most recent alert dialog
	 * @return nothing
     *
     * <br/><br/>
     * | close alert dialog | 
     */
    public final static void closeAlertDialog (FitniumFixture fitnium) {
    	fitnium.getSelenium().getAlert();
    }
    
    /**
     * Close the most recent confirmation dialog
	 * @return nothing
     *
     * <br/><br/>
     * | close confirmation dialog | 
    */
    public final static void closeConfirmationDialog (FitniumFixture fitnium) {
    	fitnium.getSelenium().getConfirmation();
    }
    
    /**
     * Closes the most recent prompt dialog
	 * @return nothing
     *
     * <br/><br/>
     * | close prompt dialog | 
     */
    public final static void closePromptDialog (FitniumFixture fitnium) {
    	fitnium.getSelenium().getPrompt();
    }
    
    /**
     * Check the last Confirmation dialog has the following message
     * @param message Text to check for
     * @return true if dialog had the specified message
     * 
     * <br/><br/>
     * | confirmation dialog message is | message | 
     */
    public final static boolean confirmationDialogMessageIs ( FitniumFixture fitnium, String message ) {
    	if ( FitniumVariableAPI.isRegularExpression(message)) {
    		String regex = FitniumVariableAPI.getRegularExpression (message);
    		return FitniumJavascriptAPI.confirmationDialogMessage(fitnium).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
        	return FitniumJavascriptAPI.confirmationDialogMessage(fitnium).equals ( FitniumVariableAPI.replaceAnyVars(message));
    	}
    }

    /**
     * Check the last Question dialog has the following message
     * @param message Text to check for
     * @return true if dialog had the specified message
     * 
     * <br/><br/>
     * | prompt dialog message is | message | 
     */
    public final static boolean promptDialogMessageIs ( FitniumFixture fitnium, String message ) {
    	if ( FitniumVariableAPI.isRegularExpression(message)) {
    		String regex = FitniumVariableAPI.getRegularExpression (message);
    		return FitniumJavascriptAPI.promptDialogMessage(fitnium).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
        	return FitniumJavascriptAPI.promptDialogMessage(fitnium).equals ( FitniumVariableAPI.replaceAnyVars(message));
    	}
    }

    /**
     * Return the value of evaluating the specified javascript snippet
     * @return Value of executed javascript
     * 
     * <br/><br/>
     * | value of javascript | script | 
     */
    public final static String valueOfJavascript ( FitniumFixture fitnium, String script ) {
    	return fitnium.getSelenium().getEval ( script );
    }

    /**
     * Stores value of a javascript evaluation in a fitnium variable
     * @param script Script to evaluate
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of javascript | script | in | name | 
     */
    public final static void storeValueOfJavascriptIn ( FitniumFixture fitnium, String script, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumJavascriptAPI.valueOfJavascript ( fitnium, script ) );
    }
    
    /**
     * Fire a javascript event against an element
     * @param eventName Name of event to fire. Note the element needs to support the event i.e OnMouseDown ()
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | fire event | name | against element | locator | 
     */
    public final static void fireEventAgainstElement ( FitniumFixture fitnium, String eventName, String loc ) {
    	fitnium.getSelenium().fireEvent( loc, FitniumVariableAPI.replaceAnyVars(eventName) );
    }

}
