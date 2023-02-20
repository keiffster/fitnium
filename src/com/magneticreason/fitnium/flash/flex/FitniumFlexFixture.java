package com.magneticreason.fitnium.flash.flex;

import com.magneticreason.fitnium.flash.FitniumFlashFixture;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.SeleniumException;

public class FitniumFlexFixture extends FitniumFlashFixture {

	protected FlexUISelenium flexApp;
	
	public FitniumFlexFixture () {
		super ();
	}
	
	public void initialiseFlexApplication (String flashObjectId) {
		if ( null == this.getSelenium() ) {
			throw new SeleniumException ( "Selenium must be initialised first" );
		}
		this.flexApp = new FlexUISelenium ( this.getSelenium(), flashObjectId );
	}
	
	public boolean isFlexApplicationInitialised() {
		return (this.flexApp!=null);
	}

	public void waitUntilFlexApplicationIsLoaded () throws Exception {
		flexApp.waitUntilLoaded ();
	}
	
	public boolean isFlexElementVisible ( String id ) {
		return flexApp.isVisible(id);
	}
	
	public void waitUntilFlexElementIsVisible ( String objectID ) throws Exception {
		flexApp.waitUntilVisible(objectID);
	}
	
	public void waitUntilFlexApplicationIsVisibleForSeconds ( String objectID, int seconds ) throws Exception {
		flexApp.waitUntilVisible(objectID, seconds);
	}
	
	public void  clickFlexElement ( String id ) {
		flexApp.click(id);
	}
	
	public void  doubleClickFlexElement ( String id ) {
		flexApp.doubleClick(id);
	}

	public String textOfFlexElement ( String id ) {
		return flexApp.readFrom(id);
	}

	public boolean isFlexElementChecked ( String id ) {
		return flexApp.isChecked( id );
	}
	
	public String flexElementErrorString ( String id ) {
		return this.flashApp.call("getFlexErrorString", id );
	}
	public String flexElementComponentInformation ( String id ) {
		return this.flashApp.call("getFlexComponentInfo", id );
	}
	public int flexElementValueAsInt ( String id ) {
		return Integer.parseInt(this.flashApp.call("getFlexParseInt", id ));
	}
	public String flexElementGlobalPosition ( String id ) {
		return this.flashApp.call("getFlexGlobalPosition", id );
	}
	public String flexElementDate ( String id ) {
		return this.flashApp.call("getFlexDate", id );
	}
	public void setFlexElementDate ( String id, String date ) {
		this.flashApp.call("doFlexDate", id, date );
	}

	public boolean isFlexElementPresent ( String id ) {
		return flashApp.call("getFlexExists", id).equals("true");
	}
	public void waitForFlexElementPresent ( String id, String args ) {
		flashApp.call("doFlexWaitForElementVisible", id);
	}
	public void waitForFlexElement ( String id, String args ) {
		flashApp.call("doFlexWaitForElement", id );
	}
	public boolean isFlexElementEnabled ( String id ) {
		return flashApp.call("getFlexEnabled", id).equals("true");
	}
	
	public String flexElementProperty ( String id ) {
		return flashApp.call("getFlexProperty", id );
	}
	public String flexElementASProperty ( String id ) {
		return flashApp.call("getFlexASProperty", id );
	}
	public void flexElementProperty ( String id, String value ) {
		flashApp.call("doFlexProperty", id, value );
	}
	
	public void clickFlexElement ( String id, String value ) {
		flashApp.call("doFlexClick", id, value );
	}

	public void dragFlexElementTo ( String id, String value ) {
		flashApp.call("doFlexDragTo", id, value );
	}

	public boolean isFlexTextPresent ( String id ) {
		return flashApp.call("getFlexTextPresent" ).equals("true");
	}
	public String flexElementText ( String id ) {
		return flashApp.call("getFlexText" );
	}

	public void setFocusToFlexElement ( String id, String value ) {
		flashApp.call("doFlexSetFocus", id, value );
	}

	/*
	doFlexSelect (id, args) 
	getFlexSelection (id) 
	getFlexSelectionIndex (id) 
	doFlexSelectIndex (id, args) 
	doFlexAddSelectIndex (id, args) 
	getFlexSelectedItemAtIndex (id) 
	getFlexNumSelectedItems (id) 
	doFlexSelectMatchingOnField (id, args) 
	doFlexSelectComboByLabel (id, args) 
	doFlexAddSelectMatchingOnField (id, args) 

	getFlexComboContainsLabel (id) 
	
	doFlexTypeAppend (id, args) 
	doFlexType (id, args) 
	
	
	getFlexStepper (id) 
	doFlexStepper (id, args) 
	
	getFlexRadioButton (id) 
	doFlexRadioButton (id, args) 
	getFlexCheckBoxChecked (id) 
	doFlexCheckBox (id, args) 
	
	getFlexDataGridUIComponentLabel (id) 
	getFlexDataGridRowIndexForFieldValue (id) 
	getFlexDataGridRowCount (id) 
	getFlexDataGridFieldValueForGridRow (id) 
	getFlexDataGridCellText (id) 
	getFlexDataGridCell (id) 
	getDataGridCellText (id) 
	getDataGridUIComponentLabel (id) 
	doFlexSetDataGridCell (id, args) 
	doFlexClickDataGridUIComponent (id, args) 
	doFlexClickDataGridItem (id, args) 

	doFlexMouseUp (id, args) 
	doFlexMouseRollOver (id, args) 
	doFlexMouseRollOut (id, args) 
	doFlexMouseOver (id, args) 
	doFlexMouseMove (id, args) 
	doFlexMouseDown (id, args) 
	doFlexDoubleClick (id, args) 

	getFlexAlertTextPresent (id) 
	getFlexAlertText (id) 
	getFlexAlertPresent (id) 
	doFlexAlertResponse (id, args) 
	doAssertFlexAlertTextPresent (searchStr) 
	doAssertFlexAlertTextNotPresent (searchStr) 
	doVerifyFlexAlertTextPresent (searchStr) 
	doVerifyFlexAlertTextNotPresent (searchStr) 

	doRefreshIDToolTips (id, args) 	
	doFlexRefreshIDToolTips (id, args) 
	doFlexClickMenuBarUIComponent (id, args) 

*/
}
