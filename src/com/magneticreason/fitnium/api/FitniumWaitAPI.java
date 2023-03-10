package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.wait.FitniumWaitForAlert;
import com.magneticreason.fitnium.wait.FitniumWaitForAttribute;
import com.magneticreason.fitnium.wait.FitniumWaitForAttributeFromAllWindows;
import com.magneticreason.fitnium.wait.FitniumWaitForBodyText;
import com.magneticreason.fitnium.wait.FitniumWaitForButtons;
import com.magneticreason.fitnium.wait.FitniumWaitForChecked;
import com.magneticreason.fitnium.wait.FitniumWaitForConfirmation;
import com.magneticreason.fitnium.wait.FitniumWaitForCookiePresent;
import com.magneticreason.fitnium.wait.FitniumWaitForCursorPosition;
import com.magneticreason.fitnium.wait.FitniumWaitForEditable;
import com.magneticreason.fitnium.wait.FitniumWaitForElement;
import com.magneticreason.fitnium.wait.FitniumWaitForElementHeight;
import com.magneticreason.fitnium.wait.FitniumWaitForElementIndex;
import com.magneticreason.fitnium.wait.FitniumWaitForElementPositionLeft;
import com.magneticreason.fitnium.wait.FitniumWaitForElementPositionTop;
import com.magneticreason.fitnium.wait.FitniumWaitForElementPresent;
import com.magneticreason.fitnium.wait.FitniumWaitForElementVisible;
import com.magneticreason.fitnium.wait.FitniumWaitForElementWidth;
import com.magneticreason.fitnium.wait.FitniumWaitForEval;
import com.magneticreason.fitnium.wait.FitniumWaitForFields;
import com.magneticreason.fitnium.wait.FitniumWaitForHtmlSource;
import com.magneticreason.fitnium.wait.FitniumWaitForLinks;
import com.magneticreason.fitnium.wait.FitniumWaitForLocation;
import com.magneticreason.fitnium.wait.FitniumWaitForOrdered;
import com.magneticreason.fitnium.wait.FitniumWaitForPrompt;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedOptions;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedId;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedIds;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedIndex;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedIndexes;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedLabel;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedLabels;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedValue;
import com.magneticreason.fitnium.wait.FitniumWaitForSelectedValues;
import com.magneticreason.fitnium.wait.FitniumWaitForSomethingSelected;
import com.magneticreason.fitnium.wait.FitniumWaitForTable;
import com.magneticreason.fitnium.wait.FitniumWaitForTextPresent;
import com.magneticreason.fitnium.wait.FitniumWaitForTitle;
import com.magneticreason.fitnium.wait.FitniumWaitForValue;
import com.magneticreason.fitnium.wait.FitniumWaitForWhetherFrameMatchesExpression;
import com.magneticreason.fitnium.wait.FitniumWaitForWhetherWindowMatchesExpression;
import com.magneticreason.fitnium.wait.FitniumWaitForWindowIds;
import com.magneticreason.fitnium.wait.FitniumWaitForWindowNames;
import com.magneticreason.fitnium.wait.FitniumWaitForWindowTitles;
import com.magneticreason.fitnium.wait.FitniumWaitForXpathCount;

import com.magneticreason.fitnium.FitniumFixture;

public class FitniumWaitAPI {

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator |
     */
    public final static void waitForElement(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElement wait = new FitniumWaitForElement(fitnium, str);
        wait.wait("Element not found : " + str);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | for | time | seconds |
     */
    public final static void waitForElementForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElement wait = new FitniumWaitForElement(fitnium, str);
        wait.waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be visible |
     */
    public final static void waitForElementToBeVisible(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementVisible wait = new FitniumWaitForElementVisible(fitnium, str);
        wait.waitWithMessage();
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be not visible |
     */
    public final static void waitForElementToBeNotVisible(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementVisible wait = new FitniumWaitForElementVisible(fitnium, str, false);
        wait.waitWithMessage();
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be visible for | sec | seconds | 
     */
    public final static void waitForElementToBeVisibleForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementVisible wait = new FitniumWaitForElementVisible(fitnium, str);
        wait.waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be not visible for | sec | seconds |
     */
    public final static void waitForElementToBeNotVisibleForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementVisible wait = new FitniumWaitForElementVisible(fitnium, str, false);
        wait.waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    
    /**
     * Waits for an element to be present on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be present |
     */
    public final static void waitForElementToBePresent(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementPresent wait = new FitniumWaitForElementPresent(fitnium, str);
        wait.waitWithMessage();
    }

    /**
     * Waits for an element to not be present on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be not present |
     */
    public final static void waitForElementToBeNotPresent(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        FitniumWaitForElementPresent wait = new FitniumWaitForElementPresent(fitnium, str, false);
        wait.waitWithMessage();
    }

    
    /**
     * Waits for all buttons to be present
     * @param buttons Comma Sep list of buttons to wait for
     * 
     * <br/><br/>
     * | wait for all buttons | buttons | present |
     */
    public final static void waitForButtonsToBePresent(FitniumFixture fitnium, String text) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForButtons(fitnium, str).waitWithMessage();
    }

    /**
     * Waits for all buttons to be present
     * @param buttons Comma Sep list of buttons to wait for
     * 
     * <br/><br/>
     * | wait for all buttons | buttons | present for | sec | seconds | 
     */
    public final static void waitForButtonsToBePresentForSeconds(FitniumFixture fitnium, String text, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForButtons(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Waits for an Alert box to appear
     * @return nothing
     *
     * <br/><br/>
     * | wait for alert to occur |
     */
    public final static void waitForAlertToOccur(FitniumFixture fitnium) {
        new FitniumWaitForAlert(fitnium).waitWithMessage();
    }

    /**
     * Waits for an Alert box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for alert to occur for | sec | seconds | 
     */
    public final static void waitForAlertToOccurForSeconds(FitniumFixture fitnium, String secs) {
        new FitniumWaitForAlert(fitnium).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Waits for an Prompt box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for prompt to occur |
     */
    public final static void waitForPromptToOccur(FitniumFixture fitnium) {
        new FitniumWaitForPrompt(fitnium).waitWithMessage();
    }

    /**
     * Waits for an Prompt box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for prompt to occur  for | sec | seconds | 
     */
    public final static void waitForPromptToOccurForSeconds(FitniumFixture fitnium, String secs) {
        new FitniumWaitForPrompt(fitnium).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Waits for an Confirmation box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for confirmation to occur |
     */
    public final static void waitForConfirmationToOccur(FitniumFixture fitnium) {
        new FitniumWaitForConfirmation(fitnium).waitWithMessage();
    }

    /**
     * Waits for an Confirmation box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for confirmation to occur  for | sec | seconds | 
     */
    public final static void waitForConfirmationToOccurForSeconds(FitniumFixture fitnium, String secs) {
        new FitniumWaitForConfirmation(fitnium).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the provided fields to be present
     * @param fields Fields to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for fields | fields | to be present |
     */
    public final static void waitForFieldsToBePresent(FitniumFixture fitnium, String fields) {
        String str = FitniumVariableAPI.replaceAnyVars(fields);
        new FitniumWaitForFields(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for the provided fields to be present
     * @param fields Fields to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for fields | fields | to be present  for | sec | seconds | 
     */
    public final static void waitForFieldsToBePresentForSeconds(FitniumFixture fitnium, String fields, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(fields);
        new FitniumWaitForFields(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the provided links to be present
     * @param links Links to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for links | links | to be present |
     */
    public final static void waitForLinksToBePresent(FitniumFixture fitnium, String links) {
        String str = FitniumVariableAPI.replaceAnyVars(links);
        new FitniumWaitForLinks(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for the provided links to be present
     * @param links Links to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for links | links | to be present  for | sec | seconds | 
     */
    public final static void waitForLinksToBePresentForSeconds(FitniumFixture fitnium, String links, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(links);
        new FitniumWaitForLinks(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the windows with window ids to be present
     * @param ids IDs to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window ids | ids | to be present |
     */
    public final static void waitForWindowIdsToBePresent(FitniumFixture fitnium, String ids) {
        String str = FitniumVariableAPI.replaceAnyVars(ids);
        new FitniumWaitForWindowIds(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for the windows with window ids to be present
     * @param ids IDs to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window ids | ids | to be present  for | sec | seconds | 
     */
    public final static void waitForWindowIdsToBePresentForSeconds(FitniumFixture fitnium, String ids, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(ids);
        new FitniumWaitForWindowIds(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the windows with window names to be present
     * @param names Names to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window names | names | to be present |
     */
    public final static void waitForWindowNamesToBePresent(FitniumFixture fitnium, String names) {
        String str = FitniumVariableAPI.replaceAnyVars(names);
        new FitniumWaitForWindowNames(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for the windows with window names to be present
     * @param names Names to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window names | names | to be present  for | sec | seconds | 
     */
    public final static void waitForWindowNamesToBePresentForSeconds(FitniumFixture fitnium, String names, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(names);
        new FitniumWaitForWindowNames(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the windows with window titles to be present
     * @param titles Titless to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window titles | titles | to be present |
     */
    public final static void waitForWindowTitlesToBePresent(FitniumFixture fitnium, String titles) {
        String str = FitniumVariableAPI.replaceAnyVars(titles);
        new FitniumWaitForWindowTitles(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for the windows with window titles to be present
     * @param titles Titless to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window titles | titles | to be present  for | sec | seconds | 
     */
    public final static void waitForWindowTitlesToBePresentForSeconds(FitniumFixture fitnium, String titles, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(titles);
        new FitniumWaitForWindowTitles(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for provided attribute to be present in current window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | to be present |
     */
    public final static void waitForAttributeToBePresent(FitniumFixture fitnium, String name) {
        new FitniumWaitForAttribute(fitnium, name).waitWithMessage();
    }

    /**
     * Wait for provided attribute to be present in current window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | to be present for | sec | seconds |      
     */
    public final static void waitForAttributeToBePresentForSeconds(FitniumFixture fitnium, String name, String secs) {
        new FitniumWaitForAttribute(fitnium, name).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for provided attribute to be present in any window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | from all windows to be present |
     */
    public final static void waitForAttributeFromAllWindowsToBePresent(FitniumFixture fitnium, String name) {
        new FitniumWaitForAttributeFromAllWindows(fitnium, name).waitWithMessage();
    }

    /**
     * Wait for provided attribute to be present in any window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | from all windows to be present  for | sec | seconds | 
     */
    public final static void waitForAttributeFromAllWindowsToBePresentForSeconds(FitniumFixture fitnium, String name, String secs) {
        new FitniumWaitForAttributeFromAllWindows(fitnium, name).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for provided body text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for body text | text | to be present |
     */
    public final static void waitForBodyTextToBePresent(FitniumFixture fitnium, String text) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForBodyText(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for provided body text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for body text | text | to be present for | sec | seconds | 
     */
    public final static void waitForBodyTextToBePresentForSeconds(FitniumFixture fitnium, String text, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForBodyText(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for radio button to be checked
     * @param loc Radio button to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for radio button | name | to be checked |
     */
    public final static void waitForRadioButtonToBeChecked(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForChecked(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for radio button to be checked
     * @param loc Radio button to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for radio button | name | to be checked for | sec | seconds |
     */
    public final static void waitForRadioButtonToBeCheckedForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForChecked(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for check box to be checked
     * @param loc Check box to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for check box | name | to be checked |
     */
    public final static void waitForCheckBoxToBeChecked(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForChecked(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for check box to be checked
     * @param loc Check box to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for check box | name | to be checked for | sec | seconds |
     */
    public final static void waitForCheckBoxToBeCheckedForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForChecked(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for provided cookie to be present
     * @param cookie Cookie to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for cookie | name | to be present |
     */
    public final static void waitForCookieToBePresent(FitniumFixture fitnium, String name) {
        String str = FitniumVariableAPI.replaceAnyVars(name);
        new FitniumWaitForCookiePresent(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for provided cookie to be present
     * @param cookie Cookie to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for cookie | name | to be present for | sec | seconds |
     */
    public final static void waitForCookieToBePresentForSeconds(FitniumFixture fitnium, String name, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(name);
        new FitniumWaitForCookiePresent(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for cursor to get to a specific position in provided element
     * @param loc Element to wait for
     * @param pos Postion to wait for 
     * @return nothing
     *
     * <br/><br/>
     * | wait for cursor in element | loc | to be at position | pos |
     */
    public final static void waitForCursorInElementToBeAtPosition(FitniumFixture fitnium, String loc, String pos) {
        String locator = FitniumVariableAPI.replaceAnyVars(loc);
        String position = FitniumVariableAPI.replaceAnyVars(pos);
        new FitniumWaitForCursorPosition(fitnium, locator, position).waitWithMessage();
    }

    /**
     * Wait for cursor to get to a specific position in provided element
     * @param loc Element to wait for
     * @param pos Postion to wait for 
     * @return nothing
     *
     * <br/><br/>
     * | wait for cursor in element | loc | to be at position | pos | for | sec | seconds |
     */
    public final static void waitForCursorInElementToBeAtPositionForSeconds(FitniumFixture fitnium, String loc, String pos, String secs) {
        String locator = FitniumVariableAPI.replaceAnyVars(loc);
        String position = FitniumVariableAPI.replaceAnyVars(pos);
        new FitniumWaitForCursorPosition(fitnium, locator, position).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to be editable
     * @param loc Element to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be editable |
     */
    public final static void waitForElementToBeEditable(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForEditable(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for given element to be editable
     * @param loc Element to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be editable for | sec | seconds |
     */
    public final static void waitForElementToBeEditableForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForEditable(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to have a certain index
     * @param loc Element to wait for
     * @param ind Index to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have index | index |
     */
    public final static void waitForElementToHaveIndex(FitniumFixture fitnium, String loc, String ind) {
        String locator = FitniumVariableAPI.replaceAnyVars(loc);
        String index = FitniumVariableAPI.replaceAnyVars(ind);
        new FitniumWaitForElementIndex(fitnium, locator, index).waitWithMessage();
    }

    /**
     * Wait for given element to have a certain index
     * @param loc Element to wait for
     * @param ind Index to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have index | index | for | sec | seconds |
     */
    public final static void waitForElementToHaveIndexForSeconds(FitniumFixture fitnium, String loc, String ind, String secs) {
        String locator = FitniumVariableAPI.replaceAnyVars(loc);
        String index = FitniumVariableAPI.replaceAnyVars(ind);
        new FitniumWaitForElementIndex(fitnium, locator, index).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to have a certain height
     * @param loc Element to wait for
     * @param height Height to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have height | height |
     */
    public final static void waitForElementToHaveHeight(FitniumFixture fitnium, String loc, String height) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(height);
        new FitniumWaitForElementHeight(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for given element to have a certain height
     * @param loc Element to wait for
     * @param height Height to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have height | height | for | sec | seconds |
     */
    public final static void waitForElementToHaveHeightForSeconds(FitniumFixture fitnium, String loc, String height, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(height);
        new FitniumWaitForElementHeight(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to have a certain width
     * @param loc Element to wait for
     * @param width Width to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have width | width |
     */
    public final static void waitForElementToHaveWidth(FitniumFixture fitnium, String loc, String width) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(width);
        new FitniumWaitForElementWidth(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for given element to have a certain width
     * @param loc Element to wait for
     * @param width Width to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to have width | width | for | sec | seconds |
     */
    public final static void waitForElementToHaveWidthForSeconds(FitniumFixture fitnium, String loc, String width, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(width);
        new FitniumWaitForElementWidth(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to be at a certain x position
     * @param loc Element to wait for
     * @param left X pos to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be at left position | pos |
     */
    public final static void waitForElementToBeAtLeftPosition(FitniumFixture fitnium, String loc, String left) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(left);
        new FitniumWaitForElementPositionLeft(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for given element to be at a certain x position
     * @param loc Element to wait for
     * @param left X pos to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be at left position | pos | for | sec | seconds |
     */
    public final static void waitForElementToBeAtLeftPositionForSeconds(FitniumFixture fitnium, String loc, String left, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(left);
        new FitniumWaitForElementPositionLeft(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given element to be at a certain y position
     * @param loc Element to wait for
     * @param top Y pos to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be at top position | pos |
     */
    public final static void waitForElementToBeAtTopPosition(FitniumFixture fitnium, String loc, String top) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(top);
        new FitniumWaitForElementPositionTop(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for given element to be at a certain y position
     * @param loc Element to wait for
     * @param top Y pos to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be at top position | pos | for | sec | seconds |
     */
    public final static void waitForElementToBeAtTopPositionForSeconds(FitniumFixture fitnium, String loc, String top, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(top);
        new FitniumWaitForElementPositionTop(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for given evaluation to equal specific value
     * @param expression Expression to evaluate
     * @param value Value to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for evaluation | eval | to equal | value |
     */
    public final static void waitForEvaluationToEqual(FitniumFixture fitnium, String expression, String value) {
        String str1 = FitniumVariableAPI.replaceAnyVars(expression);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForEval(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for given evaluation to equal specific value
     * @param expression Expression to evaluate
     * @param value Value to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for evaluation | eval | to equal | value | for | sec | seconds |
     */
    public final static void waitForEvaluationToEqualForSeconds(FitniumFixture fitnium, String expression, String value, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(expression);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForEval(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for html source to equal given values
     * @param source Souce to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for html source to equal | source |
     */
    public final static void waitForHtmlSourceToContain(FitniumFixture fitnium, String source) {
        String str = FitniumVariableAPI.replaceAnyVars(source);
        new FitniumWaitForHtmlSource(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for html source to equal given values
     * @param source Souce to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for html source to equal | source | for | sec | seconds |
     */
    public final static void waitForHtmlSourceToContainForSeconds(FitniumFixture fitnium, String source, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(source);
        new FitniumWaitForHtmlSource(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for location to be present
     * @param location Location to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for location | location | to be present |
     */
    public final static void waitForLocationToBePresent(FitniumFixture fitnium, String location) {
        String str = FitniumVariableAPI.replaceAnyVars(location);
        new FitniumWaitForLocation(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for location to be present
     * @param location Location to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for location | location | to be present for | sec | seconds |
     */
    public final static void waitForLocationToBePresentForSeconds(FitniumFixture fitnium, String location, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(location);
        new FitniumWaitForLocation(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for element to have specific order
     * @param loc Location to wait for
     * @param value Value to check for order
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | element | to be ordered with value | value |
     */
    public final static void waitForElementToBeOrderedWithValue(FitniumFixture fitnium, String loc, String value) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForOrdered(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for element to have specific order
     * @param loc Location to wait for
     * @param value Value to check for order
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | element | to be ordered with value | value | for | sec | seconds |
     */
    public final static void waitForElementToBeOrderedWithValueForSeconds(FitniumFixture fitnium, String loc, String value, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForOrdered(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for listbox/combo to have provided selected id
     * @param loc Listbox/Combo element to wait for
     * @param id ID to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected id | id |
     */
    public final static void waitForListboxToHaveSelectedId(FitniumFixture fitnium, String loc, String id) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(id);
        new FitniumWaitForSelectedId(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for listbox/combo to have provided selected id
     * @param loc Listbox/Combo element to wait for
     * @param id ID to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected id | id | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedIdForSeconds(FitniumFixture fitnium, String loc, String id, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(id);
        new FitniumWaitForSelectedId(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected list of ids
     * @param loc Listbox/Combo element to wait for
     * @param ids IDs to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected ids | id |
     */
    public final static void waitForListboxToHaveSelectedIds(FitniumFixture fitnium, String loc, String ids) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(ids);
        new FitniumWaitForSelectedIds(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected list of ids
     * @param loc Listbox/Combo element to wait for
     * @param ids IDs to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected ids | id | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedIdsForSeconds(FitniumFixture fitnium, String loc, String ids, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(ids);
        new FitniumWaitForSelectedIds(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected index
     * @param loc Listbox/Combo element to wait for
     * @param index Index to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected index | index |
     */
    public final static void waitForListboxToHaveSelectedIndex(FitniumFixture fitnium, String loc, String index) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(index);
        new FitniumWaitForSelectedIndex(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected index
     * @param loc Listbox/Combo element to wait for
     * @param index Index to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected index | index | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedIndexForSeconds(FitniumFixture fitnium, String loc, String index, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(index);
        new FitniumWaitForSelectedIndex(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected indexes
     * @param loc Listbox/Combo element to wait for
     * @param indexes Indexes to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected indexes | indexes |
     */
    public final static void waitForListboxToHaveSelectedIndexes(FitniumFixture fitnium, String loc, String indexes) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(indexes);
        new FitniumWaitForSelectedIndexes(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected indexes
     * @param loc Listbox/Combo element to wait for
     * @param indexes Indexes to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected indexes | indexes | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedIndexesForSeconds(FitniumFixture fitnium, String loc, String indexes, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(indexes);
        new FitniumWaitForSelectedIndexes(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected label
     * @param loc Listbox/Combo element to wait for
     * @param label Label to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected label | label |
     */
    public final static void waitForListboxToHaveSelectedLabel(FitniumFixture fitnium, String loc, String label) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(label);
        new FitniumWaitForSelectedLabel(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected label
     * @param loc Listbox/Combo element to wait for
     * @param label Label to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected label | label | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedLabelForSeconds(FitniumFixture fitnium, String loc, String label, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(label);
        new FitniumWaitForSelectedLabel(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected labels
     * @param loc Listbox/Combo element to wait for
     * @param labels Labels to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected labels | labels |
     */
    public final static void waitForListboxToHaveSelectedLabels(FitniumFixture fitnium, String loc, String labels) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(labels);
        new FitniumWaitForSelectedLabels(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected labels
     * @param loc Listbox/Combo element to wait for
     * @param labels Labels to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected labels | labels | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedLabelsForSeconds(FitniumFixture fitnium, String loc, String labels, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(labels);
        new FitniumWaitForSelectedLabels(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected value
     * @param loc Listbox/Combo element to wait for
     * @param value Value to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected value | value |
     */
    public final static void waitForListboxToHaveSelectedValue(FitniumFixture fitnium, String loc, String value) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForSelectedValue(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected value
     * @param loc Listbox/Combo element to wait for
     * @param value Value to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected value | value | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedValueForSeconds(FitniumFixture fitnium, String loc, String value, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForSelectedValue(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected values
     * @param loc Listbox/Combo element to wait for
     * @param values Values to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected values | values |
     */
    public final static void waitForListboxToHaveSelectedValues(FitniumFixture fitnium, String loc, String values) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(values);
        new FitniumWaitForSelectedValues(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected values
     * @param loc Listbox/Combo element to wait for
     * @param values Values to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected values | values | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedValuesForSeconds(FitniumFixture fitnium, String loc, String values, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(values);
        new FitniumWaitForSelectedValues(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have provided selected options
     * @param loc Listbox/Combo element to wait for
     * @param options Options to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected options | options |
     */
    public final static void waitForListboxToHaveSelectedOptions(FitniumFixture fitnium, String loc, String options) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(options);
        new FitniumWaitForSelectedOptions(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have provided selected options
     * @param loc Listbox/Combo element to wait for
     * @param options Options to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected options | options | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSelectedOptionsForSeconds(FitniumFixture fitnium, String loc, String options, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(options);
        new FitniumWaitForSelectedOptions(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for Listbox/combo to have anything selected
     * @param loc Listbox/Combo element to wait for
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have something selected  |
     */
    public final static void waitForListboxToHaveSomethingSelected(FitniumFixture fitnium, String loc) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForSomethingSelected(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for Listbox/combo to have anything selected
     * @param loc Listbox/Combo element to wait for
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have something selected  | for | sec | seconds |
     */
    public final static void waitForListboxToHaveSomethingSelectedForSeconds(FitniumFixture fitnium, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForSomethingSelected(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for table to occur
     * @param loc Location of table to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for table | table | to occur |
     */
    public final static void waitForTextInTableToOccur(FitniumFixture fitnium, String text, String loc) {
        String txt = FitniumVariableAPI.replaceAnyVars(text);
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForTable(fitnium, txt, str).waitWithMessage();
    }

    /**
     * Wait for table to occur
     * @param loc Location of table to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for table | table | to occur | for | sec | seconds |
     */
    public final static void waitForTableToOccurForSeconds(FitniumFixture fitnium, String text, String loc, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(loc);
        new FitniumWaitForTable(fitnium, text, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for text to be present
     * @param text Text to wait for???
     * @return nothing
     *
     * <br/><br/>
     * | wait for text | text | to be present |
     */
    public final static void waitForTextToBePresent(FitniumFixture fitnium, String text) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForTextPresent(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for text to be present
     * @param text Text to wait for???
     * @return nothing
     *
     * <br/><br/>
     * | wait for text | text | to be present | for | sec | seconds |
     */
    public final static void waitForTextToBePresentForSeconds(FitniumFixture fitnium, String text, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(text);
        new FitniumWaitForTextPresent(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for title to be present
     * @param title Title to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for title | title | to be present |
     */
    public final static void waitForTitleToBePresent(FitniumFixture fitnium, String title) {
        String str = FitniumVariableAPI.replaceAnyVars(title);
        new FitniumWaitForTitle(fitnium, str).waitWithMessage();
    }

    /**
     * Wait for title to be present
     * @param title Title to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for title | title | to be present | for | sec | seconds |
     */
    public final static void waitForTitleToBePresentForSeconds(FitniumFixture fitnium, String title, String secs) {
        String str = FitniumVariableAPI.replaceAnyVars(title);
        new FitniumWaitForTitle(fitnium, str).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for element value to be present
     * @param loc Element to wait for
     * @param value Value to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | element | with value | value | to be present |
     */
    public final static void waitForElementWithValueToBePresent(FitniumFixture fitnium, String loc, String value) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForValue(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for element value to be present
     * @param loc Element to wait for
     * @param value Value to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | element | value | value | to be present | for | sec | seconds |
     */
    public final static void waitForElementValueToBePresentForSeconds(FitniumFixture fitnium, String loc, String value, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(loc);
        String str2 = FitniumVariableAPI.replaceAnyVars(value);
        new FitniumWaitForValue(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for whether the provided frame matches the expressions
     * @param locator Frame wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether frame | frame | matches expression | expression |
     */
    public final static void waitForWhetherFrameMatchesExpression(FitniumFixture fitnium, String locator, String expression) {
        String str1 = FitniumVariableAPI.replaceAnyVars(locator);
        String str2 = FitniumVariableAPI.replaceAnyVars(expression);
        new FitniumWaitForWhetherFrameMatchesExpression(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for whether the provided frame matches the expressions
     * @param locator Frame wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether frame | frame | matches expression | expression | for | sec | seconds |
     */
    public final static void waitForWhetherFrameMatchesExpressionForSeconds(FitniumFixture fitnium, String locator, String expression, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(locator);
        String str2 = FitniumVariableAPI.replaceAnyVars(expression);
        new FitniumWaitForWhetherFrameMatchesExpression(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for whether the provided window matches the expressions
     * @param locator Window wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether window | window | matches expression | expression |
     */
    public final static void waitForWhetherWindowMatchesExpression(FitniumFixture fitnium, String locator, String expression) {
        String str1 = FitniumVariableAPI.replaceAnyVars(locator);
        String str2 = FitniumVariableAPI.replaceAnyVars(expression);
        new FitniumWaitForWhetherWindowMatchesExpression(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for whether the provided window matches the expressions
     * @param locator Window wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether window | window | matches expression | expression | for | sec | seconds |
     */
    public final static void waitForWhetherWindowMatchesExpressionForSeconds(FitniumFixture fitnium, String locator, String expression, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(locator);
        String str2 = FitniumVariableAPI.replaceAnyVars(expression);
        new FitniumWaitForWhetherWindowMatchesExpression(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Wait for the provided xpath statement to evaluate to a number of elements
     * @param xpath Xpath statement to evaluate
     * @param count Count to wait for
     *
     * <br/><br/>
     * | wait for xpath | xpath | count to be | count |
     */
    public final static void waitForXpathCountToBe(FitniumFixture fitnium, String xpath, String count) {
        String str1 = FitniumVariableAPI.replaceAnyVars(xpath);
        String str2 = FitniumVariableAPI.replaceAnyVars(count);
        new FitniumWaitForXpathCount(fitnium, str1, str2).waitWithMessage();
    }

    /**
     * Wait for the provided xpath statement to evaluate to a number of elements
     * @param xpath Xpath statement to evaluate
     * @param count Count to wait for
     *
     * <br/><br/>
     * | wait for xpath | xpath | count to be | count | for | sec | seconds |
     */
    public final static void waitForXpathCountToBeForSeconds(FitniumFixture fitnium, String xpath, String count, String secs) {
        String str1 = FitniumVariableAPI.replaceAnyVars(xpath);
        String str2 = FitniumVariableAPI.replaceAnyVars(count);
        new FitniumWaitForXpathCount(fitnium, str1, str2).waitWithMessage(Long.parseLong(secs) * 1000L);
    }

    /**
     * Pause processing for n seconds
     * @param time Time in milliseconds to wait
     * 
     * <br/><br/>
     * | wait for | millis | milliseconds | 
     */
    public final static void waitForMilliseconds(FitniumFixture fitnium, String time) {
        long millis = Long.parseLong(FitniumVariableAPI.replaceAnyVars(time));
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    /**
     * Pause processing for n seconds
     * @param time Time in seconds to wait
     * 
     * <br/><br/>
     * | wait for | secs | seconds | 
     */
    public final static void waitForSeconds(FitniumFixture fitnium, String time) {
        long secs = Long.parseLong(FitniumVariableAPI.replaceAnyVars(time));
        try {
            Thread.sleep(secs * 1000L);
        } catch (Exception e) {
        }
    }

    /**
     * Wait for a page to load for n milliseconds
     * @param time Amount of time in milliseconds to wait for page
     * 
     * <br/><br/>
     * | wait for page to load for | millis | milliseconds | 
     */
    public final static void waitForPageToLoadForMilliseconds(FitniumFixture fitnium, String time) {
        long millis = Long.parseLong(FitniumVariableAPI.replaceAnyVars(time));
        fitnium.getSelenium().waitForPageToLoad(String.valueOf(millis));
    }

    /**
     * Wait for a page to load for n seconds
     * @param time Amount of time in seconds to wait for page
     * 
     * <br/><br/>
     * | wait for page to load for | secs | seconds | 
     */
    public final static void waitForPageToLoadForSeconds(FitniumFixture fitnium, String time) {
        long secs = Long.parseLong(FitniumVariableAPI.replaceAnyVars(time));
        fitnium.getSelenium().waitForPageToLoad(String.valueOf(secs * 1000L));
    }

    /**
     * Wait for popup with the specified ID to appear within the specified number of seconds
     * @param id ID of popup to wait for
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | wait for popup with id | id | for | secs | seconds | 
     */
    public final static void waitForPopupWithIdForSeconds(FitniumFixture fitnium, String id, long timeout) {
        fitnium.getSelenium().waitForPopUp(FitniumVariableAPI.replaceAnyVars(id), String.valueOf(timeout * 1000L));
    }

    /**
     * Wait a specified number of seconds for a javascript condition to occur
     * @param seconds Time to wait
     * @param script Name of script to wait for
     * 
     * <br/><br/>
     * | wait for | secs | seconds | for script | script | to be true |
     */
    public final static void waitSecondsForScriptToBeTrue(FitniumFixture fitnium, String seconds, String script) {
        int time = Integer.parseInt(FitniumVariableAPI.replaceAnyVars(seconds));
        fitnium.getSelenium().waitForCondition(script, Integer.toString(time * 1000));
    }
}
