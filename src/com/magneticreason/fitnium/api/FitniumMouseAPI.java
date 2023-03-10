package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;

public class FitniumMouseAPI {
  
	/****************************************************************************
     * Mouse
     ****************************************************************************/
    

    /**
     * Click an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | click element | locator | 
     */
    public final static void clickElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().click( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Click in an element at a specific location
     * @param loc Locator of element 
	 * @param x X position in pixels
	 * @param y Y position in pixels
     * 
     * <br/><br/>
     * | click element | locator | at | x | and | y | 
     */
	public final static void clickElementAtAnd ( FitniumFixture fitnium, String loc, String x, String y ) {
    	fitnium.getSelenium().clickAt( FitniumVariableAPI.replaceAnyVars(loc), 
    						   FitniumUtils.createCoordString(FitniumVariableAPI.replaceAnyVars(x), 
    					       FitniumVariableAPI.replaceAnyVars(y)) );
    }
    
    /**
     * Double click an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | double click element | locator | 
     */
    public final static void doubleClickElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().doubleClick( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Double click an element at a specific location
     * @param loc Locator of element 
	 * @param x X position in pixels
	 * @param y Y position in pixels
     * 
     * <br/><br/>
     * | double click element | locator | at | x | and | y | 
     */
    public final static void doubleClickElementAtAnd ( FitniumFixture fitnium, String loc, String x, String y ) {
    	fitnium.getSelenium().doubleClickAt( FitniumVariableAPI.replaceAnyVars(loc), 
    								 FitniumUtils.createCoordString( FitniumVariableAPI.replaceAnyVars(x), 
    								 FitniumVariableAPI.replaceAnyVars(y) ) );
    }

    /**
     * Simulate clicking the mouse down on an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | click mouse down on element | locator | 
     */
    public final static void clickMouseDownOnElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().mouseDown( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Simulate clicking the mouse down on an element at a specific x and y position
     * @param loc Locator of element 
	 * @param x X position in pixels
	 * @param y Y position in pixels
     * 
     * <br/><br/>
     * | click mouse down on element | locator | at | x | and | y | 
     */
    public final static void clickMouseDownOnElementAtAnd ( FitniumFixture fitnium, String loc, String x, String y ) {
    	fitnium.getSelenium().mouseDownAt( FitniumVariableAPI.replaceAnyVars(loc), 
    							   FitniumUtils.createCoordString(FitniumVariableAPI.replaceAnyVars(x), 
    							   FitniumVariableAPI.replaceAnyVars(y)) );
    }

    /**
     * Release the mouse button on an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | release mouse up on element | locator | 
     */
    public final static void releaseMouseUpOnElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().mouseUp( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Release the mouse button on an element at a specific x and y location
     * @param loc Locator of element 
	 * @param x X position in pixels
	 * @param y Y position in pixels
     * 
     * <br/><br/>
     * | release mouse up on element | locator | at | x | and | y | 
     */
    public final static void releaseMouseUpOnElementAtAnd ( FitniumFixture fitnium, String loc, String x, String y ) {
    	fitnium.getSelenium().mouseUpAt( FitniumVariableAPI.replaceAnyVars(loc), FitniumUtils.createCoordString(FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y)) );
    }

    /**
     * Move the mouse in an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | move mouse in element | locator | 
     */
    public final static void moveMouseInElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().mouseMove( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Move the mouse in an element to a specific x and y location
     * @param loc Locator of element 
	 * @param x X position in pixels
	 * @param y Y position in pixels
     * 
     * <br/><br/>
     * | move mouse in element | locator | to | x | and | y | 
     */
    public final static void moveMouseInElementToAnd ( FitniumFixture fitnium, String loc, String x, String y  ) {
    	fitnium.getSelenium().mouseMoveAt( FitniumVariableAPI.replaceAnyVars(loc), FitniumUtils.createCoordString(FitniumVariableAPI.replaceAnyVars(x), FitniumVariableAPI.replaceAnyVars(y)) );
    }
    
    /**
     * Simulates a user moving the mouse pointer away from the specified element.
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | move mouse out of element | locator | 
     */
    public final static void moveMouseOutOfElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().mouseOut( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Simulates a user hovering a mouse over the specified element. 
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | hover mouse over element | locator | 
     */
    public final static void hoverMouseOverElement ( FitniumFixture fitnium, String loc ) {
    	fitnium.getSelenium().mouseOver ( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Drag an element x & y pixels from its current location
     * @param loc Locator of element to drag
     * @param xOffset Offset from 0 to drag in an horizontal direction
     * @param yOffset Offset from 0 to drag in a vertical direction ( "200", "+35", "-12" etc )
     * 
     * <br/><br/>
     * | drag element | locator | horizontally | x | pixels and vertically | y | pixels | 
     */
    public final static void dragElementHorizontallyPixelsAndVerticallyPixels ( FitniumFixture fitnium, String loc, String xOffset, String yOffset ) {
    	fitnium.getSelenium().dragAndDrop( FitniumVariableAPI.replaceAnyVars(loc), FitniumUtils.getDragAndDropString  ( xOffset, yOffset ) );
    }
    
    /**
     * Drag an element onto another element
     * @param locDraggee Locator of element to drag
     * @param locTarget Element to drop dragee onto
     * 
     * <br/><br/>
     * | drag element | locator | to element | target | 
     */
    public final static void dragElementToElement ( FitniumFixture fitnium, String locDraggee, String locTarget ) {
    	fitnium.getSelenium().dragAndDropToObject( FitniumVariableAPI.replaceAnyVars(locDraggee), FitniumVariableAPI.replaceAnyVars(locTarget) );
    }

    public final static void rightClickOnElement ( FitniumFixture fitnium, String loc ) {
        fitnium.getSelenium().contextMenu( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    public final static void showContextMenuForElement  ( FitniumFixture fitnium, String loc ) {
        fitnium.getSelenium().contextMenu( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    public final static void rightClickOnElementAt ( FitniumFixture fitnium, String elLoc, String loc ) {
        fitnium.getSelenium().contextMenuAt( FitniumVariableAPI.replaceAnyVars(elLoc), FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    public final static void showContextMenuForElementAt ( FitniumFixture fitnium, String elLoc, String loc ) {
        fitnium.getSelenium().contextMenuAt( FitniumVariableAPI.replaceAnyVars(elLoc), FitniumVariableAPI.replaceAnyVars(loc) );
    }

}
