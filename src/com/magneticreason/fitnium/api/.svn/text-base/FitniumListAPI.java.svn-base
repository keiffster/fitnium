package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.utils.FitniumUtils;



public class FitniumListAPI {
	
    /****************************************************************************
     * Single Select List
     ****************************************************************************

    /**
     * Select a single option in a list
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     * 
     * <br/><br/>
     * | select option | option | in list | locator | 
	 */
    public final static void selectOptionInList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().select( FitniumVariableAPI.replaceAnyVars(elementloc), FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void selectLabelInList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().select( FitniumVariableAPI.replaceAnyVars(elementloc), "label="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void selectValueInList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().select( FitniumVariableAPI.replaceAnyVars(elementloc), "value="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void selectIdInList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().select( FitniumVariableAPI.replaceAnyVars(elementloc), "id="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void selectIndexInList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().select( FitniumVariableAPI.replaceAnyVars(elementloc), "index="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }

    /**
     * Check to see if a list has anything selected
     * @param loc Locator of element to check
     * 
     * <br/><br/>
     * | does list | locator | have selection | 
	 */
    public final static boolean doesListHaveSelection ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().isSomethingSelected( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores whether a list has an item selected in a fitnium variable
     * @param loc Locator of list to check
     * @param name Name of variable to hold value
     * 
     * <br/><br/>
     * | store does list | list | have selection in | 
     */
    public final static void storeDoesListHaveSelectionIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, Boolean.toString( FitniumListAPI.doesListHaveSelection ( fitnium, loc ) ) );
    }

    /**
     * Get the ID of the option selected in the list
     * @param loc Locator of list element
     * 
     * <br/><br/>
     * | list | list | selected item id | 
     */
    public final static String listSelectedItemId ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getSelectedId( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores the id of the selected item in the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | selected item id in | name | 
     */
    public final static void storeListSelectedItemIdIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemId ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Checks to see if the item selected has the ID as specified
     * @param loc Locator of list element
     * @param id Id to check for
     * 
     * <br/><br/>
     * | is list | locator | selected item id | id | | 
     */
    public final static boolean listSelectedItemIdIs ( FitniumFixture fitnium, String loc, String id ) {
    	if ( FitniumVariableAPI.isRegularExpression(id)) {
    		String regex = FitniumVariableAPI.getRegularExpression (id);
    		return FitniumListAPI.listSelectedItemId ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return FitniumListAPI.listSelectedItemId ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).equals(FitniumVariableAPI.replaceAnyVars(id));
    	}
    }

   /**
     * Gets the index of the option selected in the list 
     * @param loc Locator of the list element
     * 
     * <br/><br/>
     * | list | locator | selected item index | 
     */
    public final static String listSelectedItemIndex ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getSelectedIndex( FitniumVariableAPI.replaceAnyVars(loc) );
    }
    
    /**
     * Stores the index of the selected item in the list
     * @param loc Locator of the list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item index in | name || 
     */
    public final static void storeListSelectedItemIndexIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemIndex ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Checks to see if the index of the option selected is as specified
     * @param loc Locator of list element
     * @param index Index to check  for
     * 
     * <br/><br/>
     * | is list | locator | selected item index | index | 
     */
    public final static boolean listSelectedItemIndexIs ( FitniumFixture fitnium, String loc, String index ) {
    	if ( FitniumVariableAPI.isRegularExpression(index)) {
    		String regex = FitniumVariableAPI.getRegularExpression (index);
    		return FitniumListAPI.listSelectedItemIndex ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return FitniumListAPI.listSelectedItemIndex ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).equals(FitniumVariableAPI.replaceAnyVars(index));
    	}
    }

    /**
     * Get the label of the selected option in the list
     * @param loc locator of list element
     * 
     * <br/><br/>
     * | list | locator | selected item label | 
     */
    public final static String listSelectedItemLabel ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getSelectedLabel( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Stores the label of the selected item in the list in a fitnium variable
     * @param loc Locator of list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item label in | name | 
    */
    public final static void storeListSelectedItemLabelIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemLabel ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }
    
    /**
     * Checks if the label of the selected option in the list is as specified
     * @param loc locator of list element
     * @return true if Label is correct
     * 
     * <br/><br/>
     * | list | locator | selected item label is | label | 
     */
    public final static boolean listSelectedItemLabelIs ( FitniumFixture fitnium, String loc, String label ) {
    	if ( FitniumVariableAPI.isRegularExpression(label)) {
    		String regex = FitniumVariableAPI.getRegularExpression (label);
    		return FitniumListAPI.listSelectedItemLabel ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return FitniumListAPI.listSelectedItemLabel ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).equals(FitniumVariableAPI.replaceAnyVars(label));
    	}
    }

    /**
     * Gets the value of the selected list item option
     * @param loc locator of list element
     * 
     * <br/><br/>
     * | list | locator | selected item value | 
     */
    public final static String listSelectedItemValue ( FitniumFixture fitnium, String loc ) {
    	return fitnium.getSelenium().getSelectedValue( FitniumVariableAPI.replaceAnyVars(loc) );
    }

    /**
     * Store the value of the selected item of the list in a fitnium variable
     * @param loc Locator of the list
     * @param name Name of variable to set value in 
     * 
     * <br/><br/>
     * | store list | locator | selected item value in | name | 
     */
    public final static void storeListSelectedItemValueIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemValue ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Checks if the selected option has the value as specified
     * @param loc locator of list element
     * @param value Value of option to check
     * @return True if value is as specified
     * 
     * <br/><br/>
     * | is list | locator | selected item value | value | 
     */
    public final static boolean listSelectedItemValueIs ( FitniumFixture fitnium, String loc, String value ) {
    	if ( FitniumVariableAPI.isRegularExpression(value)) {
    		String regex = FitniumVariableAPI.getRegularExpression (value);
    		return FitniumListAPI.listSelectedItemValue ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).matches(FitniumVariableAPI.replaceAnyVars(regex));
    	} else {
    		return FitniumListAPI.listSelectedItemValue ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ).equals(FitniumVariableAPI.replaceAnyVars(value));
    	}
    }

    
    /****************************************************************************
     * Multi Select List / Combos
     ****************************************************************************/
    
    
    /**
     * Add to the selections of a multi select list. Clicks another option, and keeps all others selected at the same time
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     * 
     * <br/><br/>
     * | add selection option | option | to list | locator | 
	 */
    public final static void addSelectionOptionToList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().addSelection( FitniumVariableAPI.replaceAnyVars(elementloc), FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void addSelectionLabelToList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().addSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "label="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void addSelectionValueToList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().addSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "value="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void addSelectionIdToList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().addSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "id="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void addSelectionIndexToList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().addSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "index="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    
    /**
     * Remove an option from the current set of those selected in the list
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     * 
     * <br/><br/>
     * | remove selection | selection | option from list | locator | 
	 */
    public final static void removeSelectionOptionFromList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().removeSelection( FitniumVariableAPI.replaceAnyVars(elementloc), FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void removeSelectionLabelFromList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().removeSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "label="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void removeSelectionValueFromList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().removeSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "value="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void removeSelectionIdFromList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().removeSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "id="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }
    public final static void removeSelectionIndexFromList ( FitniumFixture fitnium, String optionloc, String elementloc ) {
    	fitnium.getSelenium().removeSelection( FitniumVariableAPI.replaceAnyVars(elementloc), "index="+FitniumVariableAPI.replaceAnyVars(optionloc) );
    }

    /**
     * Get a comma seperated list ids of the selected options
     * @param loc locator of list element
     * @return CSL of selected IDs
     * 
     * <br/><br/>
     * | list | locator | selected items ids | 
     */
    public final static String listSelectedItemIds ( FitniumFixture fitnium, String loc ) {
    	String[] arr = fitnium.getSelenium().getSelectedIds( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	return FitniumUtils.createCommaSeperatedList ( arr );
    }

    /**
     * Store the selected item ids of the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | selected item ids in | name | 
     */
    public final static void storeListSelectedItemIdsIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemIds ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }
   
    /**
     * Check if the specified ids match to the set of selected options in the list
     * @param loc locator of list element
     * @param ids CSL of ids to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected items ids are | ids | 
     */
    public final static boolean listSelectedItemIdsAre ( FitniumFixture fitnium, String loc, String ids ) {
    	String[] arr = fitnium.getSelenium().getSelectedIds( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == arr || arr.length == 0 ) return false;
    	
    	String[] arrToCheck = FitniumUtils.stringToArray( FitniumVariableAPI.replaceAnyVars(ids) );
    	 
    	return FitniumUtils.compareTwoStringArrays ( arr, arrToCheck );
    }

    /**
     * Get a comma seperated list indexes of the selected options
     * @param loc locator of list element
     * @return CSL of selected indexes
     * 
     * <br/><br/>
     * | list | locator | selected item indexes || 
     */
    public final static String listSelectedItemIndexes ( FitniumFixture fitnium, String loc ) {
    	String[] arr = fitnium.getSelenium().getSelectedIndexes( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	return FitniumUtils.createCommaSeperatedList ( arr );
    }

    /**
     * Stores the indexes of the selected items in the list in a fitnium variable
     * @param loc Locator of list
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item indexes in | name | 
     */
    public final static void storeListSelectedItemIndexesIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemIndexes ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }
    
    /**
     * Check if the specified indexes match to the set of selected options in the list
     * @param loc locator of list element
     * @param indexes CSL of indexes to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item indexes are | indexes | 
     */
    public final static boolean listSelectedItemIndexesAre ( FitniumFixture fitnium, String loc, String indexes ) {
    	String[] arr = fitnium.getSelenium().getSelectedIndexes( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == arr || arr.length == 0 ) return false;
    	
    	String[] arrToCheck = FitniumUtils.stringToArray( FitniumVariableAPI.replaceAnyVars(indexes) );
    	 
    	return FitniumUtils.compareTwoStringArrays ( arr, arrToCheck );
    }
    
    /**
     * Get a comma seperated list labels of the selected options
     * @param loc locator of list element
     * @return CSL of selected labels
     * 
     * <br/><br/>
     * | list | locator | selected item labels | 
     */
    public final static String listSelectedItemLabels ( FitniumFixture fitnium, String loc ) {
    	String[] arr = fitnium.getSelenium().getSelectedLabels( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	return FitniumUtils.createCommaSeperatedList ( arr );
    }

    /**
     * Store the labels of the selected items in a fitnium variable
     * @param loc Locator of list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item labels in | name | 
     */
    public final static void storeListSelectedItemLabelsIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemLabels ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Check if the specified labels match to the set of selected options in the list
     * @param loc locator of list element
     * @param labels CSL of labels to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item labels are | labels | 
     */
    public final static boolean listSelectedItemLabelsAre ( FitniumFixture fitnium, String loc, String labels ) {
    	String[] arr = fitnium.getSelenium().getSelectedLabels( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == arr || arr.length == 0 ) return false;
    	
    	String[] arrToCheck = FitniumUtils.stringToArray( FitniumVariableAPI.replaceAnyVars(labels) );
    	 
    	return FitniumUtils.compareTwoStringArrays ( arr, arrToCheck );
    }
    
    /**
     * Get a comma seperated list values of the selected options
     * @param loc locator of list element
     * @return CSL of selected values
     * 
     * <br/><br/>
     * | list | locator | selected items values | 
     */
    public final static String listSelectedItemValues ( FitniumFixture fitnium, String loc ) {
    	String[] arr = fitnium.getSelenium().getSelectedValues( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	return FitniumUtils.createCommaSeperatedList ( arr );
    }

    /**
     * Store the values of the list selected items in a fitnium variable
     * @param loc Locator of list to get values
     * @param name Name of variable to set value in 
     * 
     * <br/><br/>
     * | store list | locator | selected item values in | name | 
     */
    public final static void storeListSelectedItemValuesIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listSelectedItemValues ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Check if the specified values match to the set of selected options in the list
     * @param loc locator of list element
     * @param values CSL of values to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item values are | values | 
     */
    public final static boolean listSelectedItemValuesAre ( FitniumFixture fitnium, String loc, String values ) {
    	String[] arr = fitnium.getSelenium().getSelectedValues( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == arr || arr.length == 0 ) return false;
    	
    	String[] arrToCheck = FitniumUtils.stringToArray( FitniumVariableAPI.replaceAnyVars(values) );
    	 
    	return FitniumUtils.compareTwoStringArrays ( arr, arrToCheck );
    }
    
    /**
     * Get a command seperated list of the options in a List element
     * @param loc	Locator for the list
     * @return Comma Seperated list of options
     * 
     * <br/><br/>
     * | list | locator | options | 
     */
    public final static String listOptions ( FitniumFixture fitnium, String loc ) {
    	return FitniumUtils.createCommaSeperatedList ( fitnium.getSelenium().getSelectOptions ( FitniumVariableAPI.replaceAnyVars(loc)));
    }

    /**
     * Store the options from the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | options in | name | 
     */
    public final static void storeListOptionsIn ( FitniumFixture fitnium, String loc, String name ) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( fitnium, name, FitniumListAPI.listOptions ( fitnium, FitniumVariableAPI.replaceAnyVars(loc) ) );
    }

    /**
     * Check a list elements contains all the elements contained in the comma seperated
     * list. The check does NOT check order
     * @param loc	Locator for list
     * @param options Comma seperated list of options to check for 
     * @return true if all elements ( and only those elements ) in the csl are in the list element
     * 
     * <br/><br/>
     * | does list | locator | contain options | options | 
     */
    public final static boolean doesListContainOptions ( FitniumFixture fitnium, String loc, String options ) {
    	String[] arr = fitnium.getSelenium().getSelectOptions( FitniumVariableAPI.replaceAnyVars(loc) );
    	
    	if ( null == arr || arr.length == 0 ) return false;
    	
    	String[] arrToCheck = FitniumUtils.stringToArray( FitniumVariableAPI.replaceAnyVars(options) );
    	 
    	return FitniumUtils.compareTwoStringArrays ( arr, arrToCheck );
    }
        
    /**
     * Unselects all of the selected options in a multi-select element.
     * @param loc	Locator for list
     * 
     * <br/><br/>
     * | remove all selections from list | locator | 
     */
    public final static void removeAllSelectionsFromList ( FitniumFixture fitnium, String loc) {
    	fitnium.getSelenium().removeAllSelections(FitniumVariableAPI.replaceAnyVars(loc));
    }
    
    

}
