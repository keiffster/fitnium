package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumTableAPI {

	/**
     * Get the text from the table cell and coordinates x and y
     * @param x	X Position
     * @param y Y Position
     * @param loc Table locator
     * @return Text at coordinates
     * 
     * <br/><br/>
     * | text from cell at | x | and | y | in table | locator | 
     */
    public final static String textFromCellAtAndInTable ( FitniumFixture fitnium, String x, String y, String loc ) {
    	return fitnium.getSelenium().getTable( FitniumUtils.createCellLocatorString ( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y)  ) );
    }

    /**
     * Checks if the text in a specific table cell is a specific value
     * @param x Cell column
     * @param y Cell Row
     * @param loc Locator of table
     * @param text Text to check for
     * @return true or false depending on value
     * 
     * <br/><br/>
     * | text from cell at | x | and | y | in table | locator | is | text |
     */
    public final static boolean textFromCellAtAndInTableIs ( FitniumFixture fitnium, String x, String y, String loc, String text ) {
    	if ( FitniumVariableAPI.isRegularExpression(text)) {
    		String regex = FitniumVariableAPI.getRegularExpression (text);
    		return fitnium.getSelenium().getTable( FitniumUtils.createCellLocatorString ( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y))).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return fitnium.getSelenium().getTable( FitniumUtils.createCellLocatorString ( FitniumVariableAPI.replaceAnyVars(loc), FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y))).equals(FitniumVariableAPI.replaceAnyVars(text));
    	}
    }

    /**
     * Stores the value of a cell in a table in a fitnium variable
     * @param x Cell column
     * @param y Cell Row
     * @param loc Locator of table
     * @param name Name of variable to store value in
     * 
     * <br/><br/>
     * | store text from cell at | x | and | y | in table | locator | in | name |
     */
    public final static void storeTextFromCellAtAndInTableIn ( FitniumFixture fitnium, String x, String y, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumTableAPI.textFromCellAtAndInTable ( fitnium, FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y), FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    

}
