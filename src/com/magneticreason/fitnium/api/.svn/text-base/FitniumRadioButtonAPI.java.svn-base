package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumRadioButtonAPI {
	
    /****************************************************************************
     * Radio Buttons
     ****************************************************************************

    /**
     * Click a radio button
     * @param loc Locator of element
     *
     * <br/><br/>
     * | set radio button | locator | to state | checked|unchecked |
    */
    public final static void setRadioButtonToState ( FitniumFixture fitnium, String loc, String state ) {
        String actualState = FitniumVariableAPI.replaceAnyVars(state);
        if ( actualState.equalsIgnoreCase("checked"))
            fitnium.getSelenium().check( FitniumVariableAPI.replaceAnyVars(loc) );
        else if ( actualState.equalsIgnoreCase("unchecked"))
            fitnium.getSelenium().uncheck( FitniumVariableAPI.replaceAnyVars(loc) );
        else
           System.err.println ( "Unknown check box state : " + state );
    }

    /**
     * Click a radio button
     * @param loc Locator of element
     *
     * <br/><br/>
     * | set radio button | locator | to click | clicked|unclicked |
    */
    public final static void setRadioButtonToClick ( FitniumFixture fitnium, String loc, String state ) {
        String actualState = FitniumVariableAPI.replaceAnyVars(state);
        String locator = FitniumVariableAPI.replaceAnyVars(loc);
        if ( actualState.equalsIgnoreCase("clicked")) {
            if ( !fitnium.getSelenium().isChecked ( locator ) ) {
                fitnium.getSelenium().click ( locator );
            }
        } else if ( actualState.equalsIgnoreCase("unclicked")) {
            if ( fitnium.getSelenium().isChecked ( locator ) ) {
                fitnium.getSelenium().click ( locator );
            }
        } else {
           System.err.println ( "Unknown check box click state : " + state );
        }
    }

    /**
     * Click a radio button element
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click the radio button | locator | 
     */
    public final static void clickRadioButton ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().check( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Unclick a radio button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | unclick the radio button | locator | 
     */
    public final static void unclickRadioButton ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().uncheck(FitniumVariableAPI.replaceAnyVars(loc));
    }

    /**
     * Check if a radio button is checked/clicked or not
     * @param loc Locator of element
     * @return true if radio button is checked
     * 
     * <br/><br/>
     * | is radio button | locator | checked | 
     */
     public final static boolean isRadioButtonChecked ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().isChecked( FitniumVariableAPI.replaceAnyVars(loc) );
     }
 
    /**
     * Stores if radio button is check in a fitnium variable
     * @param loc Locator of radio button to check
     * @param name Name of variable to set value
     * 
     * <br/><br/>
     * | store is radio button | locator | button checked in | name | 
     */
     public final static void storeIsRadioButtonCheckedIn ( FitniumFixture fitnium, String loc, String name ) {
     	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumRadioButtonAPI.isRadioButtonChecked ( fitnium, loc ) ) );
     }
     

}
