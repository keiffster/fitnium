package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumKeyboardAPI {
	
	/****************************************************************************
     * Keyboard
     ****************************************************************************/
    
   
    /**
     * Send the key sequence as a series of key down messages to the element specified by the locator
     * @param sequence Key Sequence to send
     * @param loc Locator of the element
     * 
     * <br/><br/>
     * | press key | key | down in element | locator | 
     */
    public final static void pressKeyDownInElement ( FitniumFixture fitnium, String sequence, String loc ) {
    	fitnium.getSelenium().keyDown( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(sequence) );
    }

    /**
     * Send the key sequence as a series of key up messages to the element specified by the locator
     * @param sequence Key Sequence to send
     * @param loc Locator of the element
     * 
     * <br/><br/>
     * | release key | key | up in element | locator | 
     */
    public final static void releaseKeyUpInElement ( FitniumFixture fitnium, String sequence, String loc ) {
    	fitnium.getSelenium().keyUp( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(sequence) );
    }
 
    /**
     * Send the key sequence as a series of key presses ( up / down ) messages to the element specified by the locator
     * @param sequence Key Sequence to send
     * @param loc Locator of the element
     * 
     * <br/><br/>
     * | press key sequence | sequence | in element | locator |
     */
    public final static void pressKeySequenceInElement ( FitniumFixture fitnium, String sequence, String loc ) {
    	fitnium.getSelenium().keyPress( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(sequence) );
    }

    /**
     * Position the cursor to a specific location in the element
     * @param pos Position to set cursor at
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | set cursor at | postion | in element | locator | 
     */
    public final static void setCursorAtInElement ( FitniumFixture fitnium, String pos, String loc ) {
    	fitnium.getSelenium().setCursorPosition( FitniumVariableAPI.replaceAnyVars(loc),  FitniumVariableAPI.replaceAnyVars(pos) );
    }

    /**
     * Get the cursor position of an element 
     * @param loc Locator of an element
     * @return Position of cursor in element
     * 
     * <br/><br/>
     * | get cursor position in element | locator | 
     */
    public final static int getCursorPositionInElement ( FitniumFixture fitnium, String loc ) {
    	return (fitnium.getSelenium().getCursorPosition( FitniumVariableAPI.replaceAnyVars(loc))).intValue();    	
    }

    /**
     * Store the cursor position of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store cursor position in element | location | in | name | 
     */
    public final static void storeCursorPositionInElementIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( fitnium.getSelenium().getCursorPosition( FitniumVariableAPI.replaceAnyVars(loc)).intValue()));
    }
    
    /**
     * Simulate holding the Alt key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the alt key down | 
     */
    public final static void whileHoldingTheAltKeyDown ( FitniumFixture fitnium ) {
        fitnium.getSelenium().altKeyDown();
    }
    
   /**
    * Simulate releasing the alt key
    * @return nothing
    * 
    * <br/><br/>
    * | release the alt key | 
    */
    public final static void releaseTheAltKey ( FitniumFixture fitnium ) {
        fitnium.getSelenium().altKeyUp();
    }

    /**
     * Simulate holding the Control key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the control key down | 
     */
    public final static void whileHoldingTheControlKeyDown ( FitniumFixture fitnium) {
        fitnium.getSelenium().controlKeyDown();
    }
    
    /**
     * Simulate releasing the control key
     * @return nothing
     * 
     * <br/><br/>
     * | release the control key | 
     */
    public final static void releaseTheControlKey ( FitniumFixture fitnium) {
        fitnium.getSelenium().controlKeyUp();
    }

    /**
     * Simulate holding the Shift key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the shift key down | 
     */
    public final static void whileHoldingTheShiftKeyDown ( FitniumFixture fitnium) {
        fitnium.getSelenium().shiftKeyDown();
    }
    
    /**
     * Simulate releasing the shift key
     * @return nothing
     * 
     * <br/><br/>
     * | release the shift key | 
     */
    public final static void releaseTheShiftKey ( FitniumFixture fitnium) {
        fitnium.getSelenium().shiftKeyUp();
    }

    /**
     * Simulate holding the Meta key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the meta key down | 
     */
    public final static void whileHoldingTheMetaKeyDown ( FitniumFixture fitnium) {
        fitnium.getSelenium().metaKeyDown();
    }
    
    /**
     * Simulate releasing the meta key
     * @return nothing
     * 
     * <br/><br/>
     * | release the meta key | 
     */
    public final static void releaseTheMetaKey ( FitniumFixture fitnium) {
        fitnium.getSelenium().metaKeyUp();
    }
    
}
