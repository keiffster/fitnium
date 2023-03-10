package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;



public class FitniumElementAPI {
	
    /****************************************************************************
     * Elements
     ****************************************************************************/
    
    
    /**
     * Briefly changes the backgroundColor of the specified element yellow. Useful for debugging. 
     * @param loc Locator of frame
     * 
     * <br/><br/>
     * | highlight element | locator | 
     */
    public final static void highlightElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().highlight ( FitniumVariableAPI.replaceAnyVars(loc) );  	
    }

    /**
     * Temporarily sets the "id" attribute of the specified element, so you can locate it in the future using its ID rather than a slow/complicated XPath
     * @param loc - an element locator pointing to an element
     * @param id - a string to be used as the ID of the specified element
     * 
     * <br/><br/>
     * | assign element | location | id | id | 
     */
    public final static void assignElementId ( FitniumFixture fitnium, String loc, String id  ) {
    	fitnium.getSelenium().assignId( FitniumVariableAPI.replaceAnyVars( loc ), FitniumVariableAPI.replaceAnyVars( id ));
    }
    
    /**
     * Check if an element is present or not
     * @param loc Locator of element
     * @return true if element is present
     * 
     * <br/><br/>
     * | is element | locator | present | 
     */
    public final static boolean isElementPresent ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().isElementPresent( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores in a fitnium variable wether a variable is present
     * @param loc Locator of element
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store is element | locator | present in | name |
     */
    public final static void storeIsElementPresentIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumElementAPI.isElementPresent ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) ) );
    }
    
    /**
     * Check is element is visible
     * @param loc Locator of element
     * @return true if element is present
     * 
     * <br/><br/>
     * | is element | locator | visible | 
     */
    public final static boolean isElementVisible ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().isVisible( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores whether an element is visible on the screen
     * @param loc Locator of element 
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store is element | locator | visible in | name | 
     */
    public final static void storeIsElementVisibleIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumElementAPI.isElementVisible ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) ) );
    }
    
    /**
     * Returns the text of an element, i.e the text contained between the <xxx> and </xxx> 
     * @param loc Location The BaseFitniumFixture locator string
     * @return text of element
     * 
     * <br/><br/>
     * | text of element | locator | 
     */
    public final static String textOfElement ( FitniumFixture fitnium, String loc ) {
        return fitnium.getSelenium().getText( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Checks if the text of an element is the specified value
     * @param loc Locator of element
     * @param text Text to check for
     * @return true of false if text is in element
     * 
     * <br/><br/>
     * | text of element | locator | is | text | 
     * @REGEX
     */
    public final static boolean textOfElementIs ( FitniumFixture fitnium, String loc, String text ) {
    	String str = fitnium.getSelenium().getText( FitniumVariableAPI.replaceAnyVars(loc) );
    	if ( FitniumVariableAPI.isRegularExpression(text)) {
    		String regex = FitniumVariableAPI.getRegularExpression (text);
    		return str.matches(regex);
    	} else {
    		return str.equals(text);
    	}
    }
    
    /**
     * Stores the text of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store text of element | locator | in | name | 
     */
    public final static void storeTextOfElementIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumElementAPI.textOfElement(fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Gets the (whitespace-trimmed) value of an input field (or anything else with a value parameter). 
     * For checkbox/radio elements, the value will be "on" or "off" depending on whether the element is checked or not. 
     * @param loc	Locator of element
     * @return value of element
     * 
     * <br/><br/>
     * | value of element | locator | 
     */
    public final static String valueOfElement ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getValue( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Check the value of an element is specified text
     * @param loc Locator of element to get value of
     * @param text Value of element to check
     * @return true or false based on value of element
     * 
     * <br/><br/>
     * | value of element | locator | is | text | 
     * @REGEX
     */
    public final static boolean valueOfElementIs ( FitniumFixture fitnium, String loc, String text ) {
    	String str = FitniumElementAPI.valueOfElement (fitnium, loc);
    	if ( FitniumVariableAPI.isRegularExpression(text)) {
    		String regex = FitniumVariableAPI.getRegularExpression (text);
    		return str.matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
        	return str.equals(FitniumVariableAPI.replaceAnyVars(text));
    	}
    }

    /**
     * Store the value of an element in a fitnium variable
     * @param loc Locator of element to get value of
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store value of element | locator | in | name | 
     */
    public final static void storeValueOfElementIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumElementAPI.valueOfElement(fitnium, FitniumVariableAPI.replaceAnyVars(loc)));
    }
    
    /**
     * Get the relative index of an element to its parent (starting from 0). 
     * The comment node and empty text node will be ignored. 
     * @param loc	Locator of index
     * @return Index of element
     * 
     * <br/><br/>
     * | index of element | locator | 
     */
    public final static int indexOfElement ( FitniumFixture fitnium, String loc ) {
    	Number val = fitnium.getSelenium().getElementIndex (FitniumVariableAPI.replaceAnyVars(loc));
    	
    	if ( null == val ) return -1;
    	
    	return val.intValue();
    }
    
	/**
	 * Checks whether the value of an element is a specific value
	 * @param loc Locator of element
	 * @param text Value of index of element to check
	 * @return true or false based on value of element
     * 
     * <br/><br/>
     * | index of element | locator | is | index |
     * @REGEX
	 */
    public final static boolean indexOfElementIs ( FitniumFixture fitnium, String loc, String index ) {
    	return (FitniumElementAPI.indexOfElement (fitnium, loc) == Integer.parseInt(FitniumVariableAPI.replaceAnyVars(index)));
    }

    /**
     * Store the index of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store index of element | locator | in | name | 
     */
    public final static void storeIndexOfElementIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( FitniumElementAPI.indexOfElement(fitnium, FitniumVariableAPI.replaceAnyVars(loc))));
    }

    /**
     * Returns the height in pixels of the element
     * @param loc Element locator
     * @return Height of element
     * 
     * <br/><br/>
     * | element | locator | height | 
     */
    public final static int elementHeight ( FitniumFixture fitnium, String loc ) {
    	Number val = (Integer)fitnium.getSelenium().getElementHeight( FitniumVariableAPI.replaceAnyVars(loc));
    	
    	return val.intValue();
    }

    /**
     * Checks whether the height of an element is specific value 
     * @param loc Locator of element 
     * @param height Height value to check
     * @return true or false based on heigh value
     * 
     * <br/><br/>
     * | element | locator | height is | value | 
     */
    public final static boolean elementHeightIs ( FitniumFixture fitnium, String loc, String height ) {
    	return (FitniumElementAPI.elementHeight (fitnium, loc) == Integer.parseInt(FitniumVariableAPI.replaceAnyVars(height)));    	
    }

    /**
     * Store the height on an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store element | locator | height in | name | 
     */
    public final static void storeElementHeightIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( FitniumElementAPI.elementHeight(fitnium, FitniumVariableAPI.replaceAnyVars(loc))));
    }
    
    /**
     * Returns the width in pixels of the element
     * @param loc Element locator
     * @return Width of element
     * 
     * <br/><br/>
     * | element | locator | width | 
     */
    public final static int elementWidth ( FitniumFixture fitnium, String loc ) {
    	Number val = (Integer)fitnium.getSelenium().getElementWidth( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	return val.intValue();
    }

    /**
     * Checks if the width of an element is specific value
     * @param loc Locator element 
     * @param height Width of element to check 
     * @return true or false based on width of element
     * 
     * <br/><br/>
     * | element | locator | width is | width | 
     * @REGEX
     */
    public final static boolean elementWidthIs ( FitniumFixture fitnium, String loc, String height ) {
    	return (FitniumElementAPI.elementWidth (fitnium, loc) == Integer.parseInt(FitniumVariableAPI.replaceAnyVars(height)));    	
    }

    /**
     * Store the width of the element in a fitnium variable
     * @param loc Locator of element to get width of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store element | locator | width in | name | 
     */
    public final static void storeElementWidthIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( FitniumElementAPI.elementWidth(fitnium, FitniumVariableAPI.replaceAnyVars(loc))));
    }
    
    /**
     * Get the position, relative to the containing frame of the left hand side of the element
     * @param loc	Locator of element
     * @return left ( or x position ) of element
     * 
     * <br/><br/>
     * | left position of element | locator | 
     */
    public final static int leftPositionOfElement ( FitniumFixture fitnium, String loc ) {
    	Number val = fitnium.getSelenium().getElementPositionLeft( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == val ) return -1;
    	
    	return val.intValue();
    }

    /**
     * Checks the left postion of an element is specific value
     * @param loc Locator of element 
     * @param pos Left position of value to check
     * @return true of false based on position
     * 
     * <br/><br/>
     * | left position of element | locator | is | pos | 
     * @REGEX
     */
    public final static boolean leftPositionOfElementIs ( FitniumFixture fitnium, String loc, String pos ) {
    	return (FitniumElementAPI.leftPositionOfElement (fitnium, loc) == Integer.parseInt(FitniumVariableAPI.replaceAnyVars(pos)));    	
    }

    /**
     * Store the left position of the element in a fitnium variable
     * @param loc Locator of element to get left position of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store left position of element | locator | in | name | 
     */
    public final static void storeLeftPositionOfElementIn ( FitniumFixture fitnium, String loc, String name ) {
       	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( FitniumElementAPI.leftPositionOfElement(fitnium, FitniumVariableAPI.replaceAnyVars(loc))));
    }  
    
    /**
     * Get the position, relative to the containing frame of the top side of the element
     * @param loc	Locator of element
     * @return top ( or y position ) of element
     * 
     * <br/><br/>
     * | top position of element | locator | 
     */
    public final static int topPositionOfElement ( FitniumFixture fitnium, String loc ) {
    	Number val = fitnium.getSelenium().getElementPositionTop( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == val ) return -1;
    	
    	return val.intValue();
    }

    /**
     * Checks the top postion of an element is specific value
     * @param loc Locator of element 
     * @param pos Top position of value to check
     * @return true of false based on position
     * 
     * <br/><br/>
     * | top position of element | locator | is | pos | 
     * @REGEX
     */
    public final static boolean topPositionOfElementIs ( FitniumFixture fitnium, String loc, String pos ) {
    	return (FitniumElementAPI.topPositionOfElement (fitnium, loc) == Integer.parseInt(FitniumVariableAPI.replaceAnyVars(pos)));    	
    }

    /**
     * Store the top position of the element in a fitnium variable
     * @param loc Locator of element to get top position of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store top position of element | locator | in | name | 
     */
    public final static void storeTopPositionOfElementIn ( FitniumFixture fitnium, String loc, String name ) {
       	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Integer.toString( FitniumElementAPI.topPositionOfElement(fitnium, FitniumVariableAPI.replaceAnyVars(loc))));
    }  
    
    /**
     * Returns whether the specified element is enabled or not
     * @param fitnium Fitnium fixture
     * @param loc Element locator
     * @return true or false if element is enabled
     * 
     * <br/><br/>
     * | is element | locator | enabled | 
     */
    public final static boolean isElementEnabled ( FitniumFixture fitnium, String loc ) {    	
    	String eval = fitnium.getSelenium().getEval("this.browserbot.findElement('"+loc+"').disabled");
    	if(eval.equalsIgnoreCase("true")) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
}
