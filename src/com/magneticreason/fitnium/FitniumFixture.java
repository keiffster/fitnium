package com.magneticreason.fitnium;
/*
Copyright 2008-2009 Keith Sterling

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.magneticreason.fitnium.api.FitniumAttributeAPI;
import com.magneticreason.fitnium.api.FitniumBrowserAPI;
import com.magneticreason.fitnium.api.FitniumButtonAPI;
import com.magneticreason.fitnium.api.FitniumCheckBoxAPI;
import com.magneticreason.fitnium.api.FitniumCookieAPI;
import com.magneticreason.fitnium.api.FitniumDebugAPI;
import com.magneticreason.fitnium.api.FitniumElementAPI;
import com.magneticreason.fitnium.api.FitniumFileAPI;
import com.magneticreason.fitnium.api.FitniumFormAPI;
import com.magneticreason.fitnium.api.FitniumGeneratorAPI;
import com.magneticreason.fitnium.api.FitniumInputFieldAPI;
import com.magneticreason.fitnium.api.FitniumJavascriptAPI;
import com.magneticreason.fitnium.api.FitniumKeyboardAPI;
import com.magneticreason.fitnium.api.FitniumLinkAPI;
import com.magneticreason.fitnium.api.FitniumListAPI;
import com.magneticreason.fitnium.api.FitniumMouseAPI;
import com.magneticreason.fitnium.api.FitniumRadioButtonAPI;
import com.magneticreason.fitnium.api.FitniumScreenCaptureAPI;
import com.magneticreason.fitnium.api.FitniumSeleniumAPI;
import com.magneticreason.fitnium.api.FitniumTableAPI;
import com.magneticreason.fitnium.api.FitniumTextAPI;
import com.magneticreason.fitnium.api.FitniumTimeAPI;
import com.magneticreason.fitnium.api.FitniumVariableAPI;
import com.magneticreason.fitnium.api.FitniumWaitAPI;
import com.magneticreason.fitnium.api.FitniumWindowAPI;
import com.magneticreason.fitnium.api.FitniumWriterAPI;
import com.magneticreason.fitnium.api.FitniumXPathAPI;
import com.thoughtworks.selenium.*;
import fitlibrary.DoFixture;

/**
 * BaseFitniumFixture - Basic DoFixture which implememts
 * most of the BaseFitniumFixture API with some additional extras
 * To use, specify path the fitnium jar and declare the class 
 * as a Do fixture table
 * <br/><br/>
 * !path path_to_fitnium.jar
 * <br/><br/>
 * !|com.magneticreason.fitnium.BaseFitniumFixture|
 * <br/><br/><br/>
 * 
 * @author Keith Sterling
 */
public class FitniumFixture extends DoFixture {

    private final static String DEFAULT_SERVER = "localhost";
    private final static int DEFAULT_PORT = 4444;
    private final static String DEFAULT_BROWSER = "*firefox";
    private String server = DEFAULT_SERVER;
    private int port = DEFAULT_PORT;
    private String browser = DEFAULT_BROWSER;
    private String baseURL;
    // Only need a single instance of selenium within the VM
    private ISeleniumFactory seleniumFactory;
    private static Selenium selenium = null;

    public FitniumFixture() {
        super();

        this.seleniumFactory = new DefaultSeleniumFactory ();

        this.init();
    }

    public FitniumFixture(Selenium selenium) {
        super();

        FitniumFixture.selenium = selenium;

        this.init();
    }

    public FitniumFixture(ISeleniumFactory factory) {
        super();

        this.seleniumFactory = factory;

        this.init();
    }

    public void setSeleniumFactory ( ISeleniumFactory factory ) {
        this.seleniumFactory = factory;
    }
    
    public Selenium getSelenium() {
        return FitniumFixture.selenium;
    }

    private final void init() {
        this.loadConfigFile();
    }
    final static String configFile = "fitnium.properties";

    /**
     * Load a Fitnium config file
     */
    public final void loadConfigFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFile));
            FitniumVariableAPI.tokens.load(properties);
        } catch (IOException e) {
            System.out.println(configFile + " not found, defaulting...");
        }
    }

    /****************************************************************************
     * Fitnesse
     ****************************************************************************/
    /**
     * Stop the test in an exception is thrown
     * 
     * <br/><br/>
     * | stop on error |
     */
    public void stopOnError() {
        this.setStopOnError(true);
    }

    /**
     * Continue the test even if an exception is thrown
     * 
     * <br/><br/>
     * | continue on error |
     */
    public void continueOnError() {
        this.setStopOnError(false);
    }

    /****************************************************************************
     * Debug
     ****************************************************************************/
    /**
     * Write out a debug message to the console
     * @return nothing
     *
     * <br/><br/>
     * | write to debug | message | 
     */
    public void writeToDebug(String msg) {
        FitniumDebugAPI.writeToDebug(this, msg);
    }

    /**
     * Sets the threshold for browser-side logging messages; log messages beneath this threshold will be discarded. Valid logLevel strings are: "debug", "info", "warn", "error" or "off".
     * @param level - one of the following: "debug", "info", "warn", "error" or "off"
     * 
     * <br/><br/>
     * | set browser log level | debug info warn error off | 
     */
    public void setBrowserLogLevel(String level) {
        FitniumDebugAPI.setBrowserLogLevel(this, level);
    }

    /****************************************************************************
     * Initialisation
     ****************************************************************************/
    /**
     * Tell BaseFitniumFixture where the server is located
     * @return nothing
     * 
     * <br/><br/>
     * | the server is located at | url | 
     */
    public void theServerIsLocatedAt(String server) {
        this.server = FitniumVariableAPI.replaceAnyVars(server);
    }

    public void SeleniumIsLocatedAt(String server) {
        this.server = FitniumVariableAPI.replaceAnyVars(server);
    }

    /**
     * Tell BaseFitniumFixture which port the server is running on
     * @return nothing
     * 
     * <br/><br/>
     * | the server is on port | port_no | 
     */
    public void theServerIsOnPort(String port) {
        this.port = Integer.parseInt(FitniumVariableAPI.replaceAnyVars(port));
    }

    public void FitniumIsOnPort(String port) {
        this.port = Integer.parseInt(FitniumVariableAPI.replaceAnyVars(port));
    }

    /**
     * Tell BaseFitniumFixture to start at a specific URL, using a specific browser
     * @param browser	BaseFitniumFixture name of browser to run tests in ( See BaseFitniumFixture documnetation for list )
     * @param url		URL to start processing form
     * 
     * <br/><br/>
     * | using the browser | browser_type | start at | base_url | 
     */
    public void usingTheBrowserStartAt(String browser, String url) {
        this.browser = FitniumVariableAPI.replaceAnyVars(browser);

        // This is to support SLiM which mangles URLs into HTML with a <a></a> around
        // So for SLiM we expect it not to be present and add it later
        if (!url.toUpperCase().startsWith("HTTP://")) {
            this.baseURL = "http://" + FitniumVariableAPI.replaceAnyVars(url);
        } else {
            this.baseURL = FitniumVariableAPI.replaceAnyVars(url);
        }

        if ( null == FitniumFixture.selenium ) {
            FitniumFixture.selenium = seleniumFactory.createSeleniumInstance(   this.server,
                                                                                this.port,
                                                                                this.browser,
                                                                                this.baseURL);
        }
        FitniumFixture.selenium.start();
    }

    /**
     * Tell BaseFitniumFixture to start at a specific URL, using a specific browser
     * @param browser	BaseFitniumFixture name of browser to run tests in ( See BaseFitniumFixture documnetation for list )
     * @param url		URL to start processing form
     *
     * <br/><br/>
     * | using the browser | browser_type | securely start at | base_url |
     */
    public void usingTheBrowserSecurelyStartAt(String browser, String url) {
        this.browser = FitniumVariableAPI.replaceAnyVars(browser);

        // This is to support SLiM which mangles URLs into HTML with a <a></a> around
        // So for SLiM we expect it not to be present and add it later
        if (!url.toUpperCase().startsWith("HTTPS://")) {
            this.baseURL = "https://" + FitniumVariableAPI.replaceAnyVars(url);
        } else {
            this.baseURL = FitniumVariableAPI.replaceAnyVars(url);
        }

        if ( null == FitniumFixture.selenium ) {
            FitniumFixture.selenium = seleniumFactory.createSeleniumInstance(   this.server,
                                                                                this.port,
                                                                                this.browser,
                                                                                this.baseURL);
        }
        FitniumFixture.selenium.start();
    }

    /**
     * Use a different Selenium than the default one created in the method 
     * 		usingTheBrowserStartAt ()
     * Useful for Unit Testing as you can pass in a Mock BaseFitniumFixture object
     * @param Selenium Instance of Selenium to use
     * 
     */
    protected void usingSelenium(Selenium selenium) {
        FitniumFixture.selenium = selenium;
        FitniumFixture.selenium.start();
    }

    /**
     * Shortcut method to start the browser with firefox
     * @param url		URL to start processing form
     * 
     * <br/><br/>
     * | using firefox | start at | base_url | 
     */
    public void usingFirefoxStartAt(String url) {
        this.usingTheBrowserStartAt("*firefox", FitniumVariableAPI.replaceAnyVars(url));
    }

    /**
     * Shortcut method to start the browser with explorer
     * @param url		URL to start processing form
     * 
     * <br/><br/>
     * | using explorer | start at | base_url | 
     */
    public void usingExplorerStartAt(String url) {
        this.usingTheBrowserStartAt("*iexplore", FitniumVariableAPI.replaceAnyVars(url));
    }

    /**
     * Shortcut method to start the browser with explore in HTA mode
     * @param url		URL to start processing form
     * 
     * <br/><br/>
     * | using explorer in HTA mode | start at | base_url | 
     */
    public void usingExplorerinHTAModeStartAt(String url) {
        this.usingTheBrowserStartAt("*ehta", FitniumVariableAPI.replaceAnyVars(url));
    }

    /**
     * Shortcut method to start the browser with chrome
     * @param url		URL to start processing form
     * 
     * <br/><br/>
     * | using chrome | start at | base_url | 
     */
    public void usingChromeStartAt(String url) {
        this.usingTheBrowserStartAt("*chrome", FitniumVariableAPI.replaceAnyVars(url));
    }

    /**
     * Tell BaseFitniumFixture to start at a specific URL
     * @return True in BaseFitniumFixture is initialised
     * 
     * <br/><br/>
     * | is selenium initialised | 
     */
    public boolean isSeleniumInitialised() {
        return (FitniumFixture.selenium != null);
    }

    /****************************************************************************
     * BaseFitniumFixture
     ****************************************************************************/
    /**
     * Tell BaseFitniumFixture to start at a specific URL
     * @param url URL to start processing at
     * 
     * <br/><br/>
     * |  starting at url | url | 
     */
    public void startingAtURL(String url) {
        FitniumSeleniumAPI.startingAtURL(this, url);
    }

    /**
     * Tell BaseFitniumFixture which speed to run at
     * @param speed Speed in milliseconds between each command
     * 
     * <br/><br/>
     * | set speed to | millis | milliseconds | 
     */
    public void setSpeedToMilliseconds(String speed) {
        FitniumSeleniumAPI.setSpeedToMilliseconds(this, speed);
    }

    /**
     * Tell BaseFitniumFixture which speed to run at
     * @param speed Speed in seconds between each command
     * 
     * <br/><br/>
     * | set speed to | secs | seconds | 
     */
    public void setSpeedToSeconds(String speed) {
        FitniumSeleniumAPI.setSpeedToSeconds(this, speed);
    }

    /**
     * Tell BaseFitniumFixture how to long to wait before timeout
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | set timeout to | millis | milliseconds | 
     */
    public void setTimeoutToMilliseconds(String timeout) {
        FitniumSeleniumAPI.setTimeoutToMilliseconds(this, timeout);
    }

    /**
     * Tell BaseFitniumFixture how to long to wait before timeout
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | set timeout to | secs | seconds | 
     */
    public void setTimeoutToSeconds(String timeout) {
        FitniumSeleniumAPI.setTimeoutToSeconds(this, timeout);
    }

    /**
     * Defines a new function for BaseFitniumFixture to locate elements on the page
     * @param strategyName - the name of the strategy to define; this should use only letters [a-zA-Z] with no spaces or other punctuation.
     * @param functionDefinition - a string defining the body of a function in JavaScript. For example: return inDocument.getElementById(locator);
     * 
     * <br/><br/>
     * | add to location strategy | strategy | function | func |
     */
    public void addToLocationStrategyFunction(String strategyName, String functionDefinition) {
        FitniumSeleniumAPI.addToLocationStrategyFunction(this, strategyName, functionDefinition);
    }

    /****************************************************************************
     * XPath
     ****************************************************************************/
    /**
     * Change the Xpath library used by Fitnium, useful IE where the default Selenium
     * Xpath library is very very slow
     *
     * <br/><br/>
     * | use xpath library | lib |
     * e.g. for Explorer
     * | use xpath library | javascript-xpath |
     */
    public void useXpathLibrary(String lib) {
        FitniumXPathAPI.useXpathLibrary(this, lib);
    }

    /**
     * Specifies that BaseFitniumFixture should use the native in-browser implementation of XPath
     * @return nothing
     *
     * <br/><br/>
     * | allow native xpath |
     */
    public void allowNativeXpath() {
        FitniumXPathAPI.allowNativeXpath(this);
    }

    /**
     * Specifies that BaseFitniumFixture should NOT use the native in-browser implementation of XPath
     * @return nothing
     *
     * <br/><br/>
     * | disallow native xpath |
     */
    public void disallowNativeXpath() {
        FitniumXPathAPI.disallowNativeXpath(this);
    }

    /**
     * Gets the number of nodes matching an xpath statement
     * @param xpath to match
     * @return Number of nodes matching
     * 
     * <br/><br/>
     * | get number of nodes matching xpath | xpath | 
     */
    public int getNumberOfNodesMatchingXpath(String xpath) {
        return FitniumXPathAPI.getNumberOfNodesMatchingXpath(this, xpath);
    }

    /**
     * Checks if the number of nodes matching is as expected
     * @param xpath XPath statement to match
     * @param count Number of matching nodes expected
     * @return true if match is the same
     * 
     * <br/><br/>
     * | number of nodes matching xpath | xpath | is | count |
     */
    public boolean numberOfNodesMatchingXpathIs(String xpath, String count) {
        return FitniumXPathAPI.numberOfNodesMatchingXpathIs(this, xpath, count);
    }

    /**
     * Stores the number of nodes matching xpath statement in the assigned variable
     * @param xpath XPath statment to match
     * @param name Variable to store value in
     * 
     * <br/><br/>
     * | store number of nodes matching xpath | xpath | in | name | 
     */
    public void storeNumberOfNodesMatchingXpathIn(String xpath, String name) {
        FitniumXPathAPI.storeNumberOfNodesMatchingXpathIn(this, xpath, name);
    }

    /****************************************************************************
     * Browser
     ****************************************************************************/
    /**
     * Tell BaseFitniumFixture to stop
     * @return nothing
     *
     * <br/><br/>
     * | close the browser | 
     */
    public void closeTheBrowser() {
        FitniumBrowserAPI.closeTheBrowser(this);
    }

    /**
     * Clicks the close button of the browser, effectively closely the browser window
     * @return nothing
     *
     * 
     * <br/><br/>
     * | click the close button | 
     */
    public void clickTheCloseButton() {
        FitniumBrowserAPI.clickTheCloseButton(this);
    }

    /**
     * Clicks the back button on the browser
     * @return nothing
     *
     * 
     * <br/><br/>
     * | click the back button | 
     */
    public void clickTheBackButton() {
        FitniumBrowserAPI.clickTheBackButton(this);
    }

    /**
     * Clicks the refresh button on the browser
     * @return nothing
     *
     * 
     * <br/><br/>
     * | click the refresh button | 
     */
    public void clickTheRefreshButton() {
        FitniumBrowserAPI.clickTheRefreshButton(this);
    }

    /****************************************************************************
     * Variables
     ****************************************************************************/
    /**
     * Set a named BaseFitniumFixture variable with the value passed
     * @param name Name of variable to set
     * @param value Value to set variable with
     * 
     * <br/><br/>
     * | set BaseFitniumFixture variable called | name | with value | value | 
     */
    public void setSeleniumVariableCalledWithValue(String name, String value) {
        FitniumVariableAPI.setSeleniumVariableCalledWithValue(this, name, value);
    }

    /**
     * Get the value of a BaseFitniumFixture variable
     * @param name Name of the variable to fetch
     * @return Value of the variable 
     * 
     * <br/><br/>
     * | get BaseFitniumFixture variable called | name | 
     */
    public String getSeleniumVariableCalled(String name) {
        return FitniumVariableAPI.getSeleniumVariableCalled(this, name);
    }

    /**
     * Check if to BaseFitniumFixture variables are the same
     * @param var1 First variable to compare
     * @param var2 Second variable to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | is BaseFitniumFixture variable | var1 | equal | var2 | true |
     */
    public boolean doesSeleniumVariableEqual(String var1, String var2) {
        return FitniumVariableAPI.doesSeleniumVariableEqual(this, var1, var2);
    }

    /**
     * Set the value of a fitnium variable 
     * @param name Name of the variable to set
     * @param value Value of the variable to set
     * 
     * <br/><br/>
     * | set fitnium variable called | name | with value | value | 
     */
    public void setFitniumVariableCalledWithValue(String name, String value) {
        FitniumVariableAPI.setFitniumVariableCalledWithValue(this, name, value);
    }

    /**
     * Get the value of a fitnium variable
     * @param name Name of the variable to fetch
     * @return Value of fetched variable
     * 
     * <br/><br/>
     * | get firnium variable called | name | 
     */
    public String getFitniumVariableCalled(String name) {
        return FitniumVariableAPI.getFitniumVariableCalled(name);
    }

    /**
     * Check if to fitnium variables are the same
     * @param var1 First variable to compare
     * @param var2 Second variable to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does fitnium variable | var1 | equal variable | var2 | true |
     */
    public boolean doesFitniumVariableEqualVariable(String var1, String var2) {
        return FitniumVariableAPI.doesFitniumVariableEqualVariable(var1, var2);
    }

    /**
     * Check if to fitnium variables has a value
     * @param var1 Variable to compare
     * @param var2 Value to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does fitnium variable | var1 | equal value | var2 | true |
     */
    public boolean doesFitniumVariableEqualValue(String var1, String var2) {
        return FitniumVariableAPI.doesFitniumVariableEqualValue(var1, var2);
    }

    /**
     * Check if to values are the same ( useful for substituted parameters )
     * @param var1 First value to compare
     * @param var2 Second value to compare, can be a regular expression
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does | var1 | equal | var2 | true |
     */
    public boolean doesEqual(String var1, String var2) {
        return FitniumVariableAPI.doesEqual(var1, var2);
    }

    /**
     * Return the difference between two values by converting them to 
     * integers and subtracting one from the other
     * @param var1 First value
     * @param var2 Second value to subtract for var1
     * @return
     */
    public String differenceBetweenAnd(String var1, String var2) {
        return FitniumVariableAPI.differenceBetweenAnd(var1, var2);
    }

    /**
     * Does a comma seperated list equal another csl. Order is unimportant
     * @param list1 First list to compare
     * @param list2 Second list to compare
     * @return true if all elements in lists are equal
     * 
     * <br/><br/>
     * | does list | list1 | equal list | list2 |
     */
    public boolean doesListEqualList(String list1, String list2) {
        return FitniumVariableAPI.doesListEqualList(list1, list2);
    }

    /**
     * Checks to see if a list contains a specific value
     * @param list List to search
     * @param val Value to search for
     * @return true if value exists in list
     * 
     * <br/><br/>
     * | does list | list1 | contain value | value |
     */
    public boolean doesListContainValue(String list, String val) {
        return FitniumVariableAPI.doesListContainValue(list, val);
    }

    /**
     * Checks if a list contains any of the values from second list
     * @param srcList List to search
     * @param list List of values to search for
     * @return true if list contains any of the values
     * 
     * <br/><br/>
     * | does list | list1 | contain any values | list2 |
     */
    public boolean doesListContainAnyValues(String srcList, String list) {
        return FitniumVariableAPI.doesListContainAnyValues(srcList, list);
    }

    /**
     * Allows you to carry out search and replace on a variable or text element
     * using regular expressions
     * @param find  String to find in the string, could be a regex
     * @param rep String to replace it with
     * @param str String to carry out search and replace in
     * @param var Name of Fitnium variable to store result in
     *
     * <br/><br/>
     * | replace | find | with | rep| in | str | and store in fitnium variable | var |
     */
    public void replaceWithInAndStoreInFitniumVariable ( String find, String rep, String str, String var ) {
        FitniumVariableAPI.replaceWithInAndStoreInFitniumVariable ( find, rep, str, var );
    }

    // Added by Patrick Mead - State Farm 28/6/10
    /**
     * Re
     * @param variableNameForOriginalText Text to replace within
     * @param regex Regular expression pattern to search for
     * @param variableNameForExtractedText Text to replace that matches regex
     *
     * <br/><br/>
     * | extract text in variable | var | matching pattern | pattern | and store in | varname |
     */
    public final void extractTextInVariableMatchingPatternAndStoreIn(String variableNameForOriginalText, String regex, String variableNameForExtractedText) {
        FitniumVariableAPI.extractTextInVariableMatchingPatternAndStoreIn( this, variableNameForOriginalText,  regex,  variableNameForExtractedText);
    }

    /****************************************************************************
     * Writers
     ****************************************************************************/
    /**
     * Load a write into the system and assign it a name we can use
     * @param classpath Classpath of write to load
     * @param name Name to assign it
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | load writer | classpath | into | name 
     */
    public void loadWriterInto(String classpath, String name) throws Exception {
        FitniumWriterAPI.loadWriterInto(classpath, name);
    }

    /**
     * Load a write into the system and assign it a name we can use
     * @param classpath Classpath of write to load
     * @param name Name to assign it
     * @param params Additional data to pass to the writer
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | load writer | classpath | into | name | with params | params |
     */
    public void loadWriterIntoWithParams(String classpath, String name, String params) throws Exception {
        FitniumWriterAPI.loadWriterIntoWithParams(classpath, name, params);
    }

    /**
     * Store a value in the write for saving later
     * @param value Value to save
     * @param name Name of writer to save it in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store value | value | in writer | name |
     */
    public void storeValueInWriter(String value, String name) throws Exception {
        FitniumWriterAPI.storeValueInWriter(value, name);
    }

    /**
     * Store a value in the write for saving later
     * @param value Value to save
     * @param name Name of writer to save it in
     * @param params Additional data to pass to store
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store value | value | with params | params | in writer | name |
     */
    public void storeValueWithParamsInWriter(String value, String params, String name) throws Exception {
        FitniumWriterAPI.storeValueWithParamsInWriter(value, params, name);
    }

    /** 
     * Flush the data from the writer to the storage systm
     * @param name Name of write to flush
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | flush writer | name |
     */
    public void flushWriter(String name) throws Exception {
        FitniumWriterAPI.flushWriter(name);
    }

    /****************************************************************************
     * Generators
     ****************************************************************************/
    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | 
     */
    public void loadUniqueValueGeneratorInto(String classpath, String name) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorInto(classpath, name);
    }

    /**
     * Loads a Value Generator and assigns it a name and initialises it with custom data
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param init Data to initialise it, this is specific you the generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | and initialise with | data |
     */
    public void loadUniqueValueGeneratorIntoAndInitialiseWith(String classpath, String name, String init) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorIntoAndInitialiseWith(classpath, name, init);
    }

    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param value Starting value to assign to generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | with starting value | value |
     */
    public void loadUniqueValueGeneratorIntoWithStartingValue(String classpath, String name, String value) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorIntoWithStartingValue(classpath, name, value);
    }

    /**
     * Store the next value from a generator into a fitnium variable for later use
     * @param genName Name of generator to get next value from 
     * @param name Name of fitnium variable to store value in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store next unique value from | genName | in | name |
     */
    public void storeNextUniqueValueFromIn(String genName, String name) throws Exception {
        FitniumGeneratorAPI.storeNextUniqueValueFromIn( this, genName, name);
    }

    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @throws Exception if the generator is not loaded
     *
     * <br/><br/>
     * | load reader | classpath | into | name |
     */
    public void loadReaderInto(String classpath, String name) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorInto(classpath, name);
    }

    /**
     * Loads a Value Generator and assigns it a name and initialises it with custom data
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param init Data to initialise it, this is specific you the generator
     * @throws Exception if the generator is not loaded
     *
     * <br/><br/>
     * | load reader | classpath | into | name | and initialise with | data |
     */
    public void loadReaderIntoAndInitialiseWith(String classpath, String name, String init) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorIntoAndInitialiseWith(classpath, name, init);
    }

    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param value Starting value to assign to generator
     * @throws Exception if the generator is not loaded
     *
     * <br/><br/>
     * | load reader | classpath | into | name | with starting value | value |
     */
    public void loadReaderIntoWithStartingValue(String classpath, String name, String value) throws Exception {
        FitniumGeneratorAPI.loadUniqueValueGeneratorIntoWithStartingValue(classpath, name, value);
    }

    /**
     * Store the next value from a generator into a fitnium variable for later use
     * @param genName Name of generator to get next value from 
     * @param params Custom paramters to pass to next value function
     * @param name Name of fitnium variable to store value in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store next unique value from | genName | with params | params | in | name |
     */
    public void storeNextUniqueValueFromWithParamsIn(String genName, String params, String name) throws Exception {
        FitniumGeneratorAPI.storeNextUniqueValueFromWithParamsIn( this, genName, params, name);
    }

    /**
     * Get the next unique value
     * @param name Name of the generator to get value from
     * @return Next unique value
     * @throws Exception if something goes wrong
     *
     * <br/><br/>
     * | next unique value from | name |
     */
    public String nextUniqueValueFrom(String name) throws Exception {
        return FitniumGeneratorAPI.nextUniqueValueFrom(name);
    }

    /**
     * Get the next unique value by passing in custom data
     * @param name Name of the generator to get value from
     * @param params Custom data to pass to function
     * @return Next unique value
     * @throws Exception if something goes wrong
     *
     * <br/><br/>
     * | next unique value from | name | with params | params |
     */
    public String nextUniqueValueFromWithParams(String name, String params) throws Exception {
        return FitniumGeneratorAPI.nextUniqueValueFromWithParams(name, params);
    }

    /**
     * Reset the generator ( where application )
     * @param name Name of generator to reset
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | reset unique value generator | name |
     */
    public void resetUniqueValuedGenerator(String name) throws Exception {
        FitniumGeneratorAPI.resetUniqueValuedGenerator(name);
    }

    /**
     * Reset the generator ( where application ) to a given value
     * @param name Name of generator to reset
     * @param value Value to reset data to
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | reset unique value generator | name | back to | value |
     */
    public void resetUniqueValuedGeneratorBackTo(String name, String value) throws Exception {
        FitniumGeneratorAPI.resetUniqueValuedGeneratorBackTo(name, value);
    }

    /****************************************************************************
     * Screen Capture
     ****************************************************************************/
    /**
     * Capture the current screen to a file
     * @param filename Name of file to save screen capture to
     * 
     * <br/><br/>
     * | capture screen to file | filename | 
     */
    public void captureScreenToFile(String filename) {
        FitniumScreenCaptureAPI.captureScreenToFile(this, filename);
    }

    /**
     * Capture the current screen to a string
     * @return The screen shot as as a base 64 encoded string
     * 
     * <br/><br/>
     * | capture screen as string | 
     */
    public String captureScreenAsString() {
        return FitniumScreenCaptureAPI.captureScreenAsString(this);
    }

    /**
     * Stores the screen in a fitnium variable
     * @param name Name of variable to hold screen
     * 
     * <br/><br/>
     * | store screen as string in | name | 
     */
    public void storeScreenAsStringIn(String name) {
        FitniumScreenCaptureAPI.storeScreenAsStringIn(this, name);
    }

    /**
     * Captures the entire html representation of the screen
     * @return html source of screen
     * 
     * <br/><br/>
     * | capture html source |
     */
    public String captureHtmlSource() {
        return FitniumScreenCaptureAPI.captureHtmlSource(this);
    }

    /**
     * Stores the entire html representation of the screen in a fitnium variable
     * @param name Name of variable to store source in
     * 
     * <br/><br/>
     * | store html source in | name |
     */
    public void storeHtmlSourceIn(String name) {
        FitniumScreenCaptureAPI.storeHtmlSourceIn(this, name);
    }

    /****************************************************************************
     * Text
     ****************************************************************************/
    /**
     * Check is text present on the screen
     * @param pattern Pattern of text to search for on screen
     * 
     * <br/><br/>
     * | is text | pattern | present | 
     * @REGEX
     */
    public boolean isTextPresent(String pattern) {
        return FitniumTextAPI.isTextPresent(this, pattern);
    }

    /**
     * Stores true or false if text pattern is present on screen 
     * @param pattern Pattern to look for
     * @param name Variable to store value in 
     *
     * <br/><br/>
     * | store is text | text | present in | name | 
     * @REGEX
     */
    public void storeIsTextPresentIn(String pattern, String name) {
        FitniumTextAPI.storeIsTextPresentIn(this, pattern, name);
    }

    /**
     * Stores the content of the HTML body in a fitnium variable
     * @param name Name of variable to store body in 
     * 
     * <br/><br/>
     * | store body text in | name | 
     */
    public void storeBodyTextIn(String name) {
        FitniumTextAPI.storeBodyTextIn(this, name);
    }

    /**
     * Checks if body text is 
     * @param pattern Text to check for
     * @return true of false if text is available
     * 
     * <br/><br/>
     * | body text is | text |
     * | check | body text is | text | true |
     */
    public boolean bodyTextIs(String pattern) {
        return FitniumTextAPI.bodyTextIs(this, pattern);
    }

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
    public void highlightElement(String loc) {
        FitniumElementAPI.highlightElement(this, loc);
    }

    /**
     * Temporarily sets the "id" attribute of the specified element, so you can locate it in the future using its ID rather than a slow/complicated XPath
     * @param loc - an element locator pointing to an element
     * @param id - a string to be used as the ID of the specified element
     * 
     * <br/><br/>
     * | assign element | location | id | id | 
     */
    public void assignElementId(String loc, String id) {
        FitniumElementAPI.assignElementId(this, loc, id);
    }

    /**
     * Check if an element is present or not
     * @param loc Locator of element
     * @return true if element is present
     * 
     * <br/><br/>
     * | is element | locator | present | 
     */
    public boolean isElementPresent(String loc) {
        return FitniumElementAPI.isElementPresent(this, loc);
    }

    /**
     * Stores in a fitnium variable wether a variable is present
     * @param loc Locator of element
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store is element | locator | present in | name |
     */
    public void storeIsElementPresentIn(String loc, String name) {
        FitniumElementAPI.storeIsElementPresentIn(this, loc, name);
    }

    /**
     * Check is element is visible
     * @param loc Locator of element
     * @return true if element is present
     * 
     * <br/><br/>
     * | is element | locator | visible | 
     */
    public boolean isElementVisible(String loc) {
        return FitniumElementAPI.isElementVisible(this, loc);
    }

    /**
     * Stores whether an element is visible on the screen
     * @param loc Locator of element 
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store is element | locator | visible in | name | 
     */
    public void storeIsElementVisibleIn(String loc, String name) {
        FitniumElementAPI.storeIsElementVisibleIn(this, loc, name);
    }

    /**
     * Returns the text of an element, i.e the text contained between the <xxx> and </xxx> 
     * @param loc Location The BaseFitniumFixture locator string
     * @return text of element
     * 
     * <br/><br/>
     * | text of element | locator | 
     */
    public String textOfElement(String loc) {
        return FitniumElementAPI.textOfElement(this, loc);
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
    public boolean textOfElementIs(String loc, String text) {
        return FitniumElementAPI.textOfElementIs(this, loc, text);
    }

    /**
     * Stores the text of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store text of element | locator | in | name | 
     */
    public void storeTextOfElementIn(String loc, String name) {
        FitniumElementAPI.storeTextOfElementIn(this, loc, name);
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
    public String valueOfElement(String loc) {
        return FitniumElementAPI.valueOfElement(this, loc);
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
    public boolean valueOfElementIs(String loc, String text) {
        return FitniumElementAPI.valueOfElementIs(this, loc, text);
    }

    /**
     * Store the value of an element in a fitnium variable
     * @param loc Locator of element to get value of
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store value of element | locator | in | name | 
     */
    public void storeValueOfElementIn(String loc, String name) {
        FitniumElementAPI.storeValueOfElementIn(this, loc, name);
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
    public int indexOfElement(String loc) {
        return FitniumElementAPI.indexOfElement(this, loc);
    }

    /**
     * Checks whether the value of an element is a specific value
     * @param loc Locator of element
     * @param text Value of index of element to check
     * @return true or false based on value of element
     * 
     * <br/><br/>
     * | index of element | locator | is | text | 
     * @REGEX
     */
    public boolean indexOfElementIs(String loc, String text) {
        return FitniumElementAPI.indexOfElementIs(this, loc, text);
    }

    /**
     * Store the index of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store index of element | locator | in | name | 
     */
    public void storeIndexOfElementIn(String loc, String name) {
        FitniumElementAPI.storeIndexOfElementIn(this, loc, name);
    }

    /**
     * Returns the height in pixels of the element
     * @param loc Element locator
     * @return Height of element
     * 
     * <br/><br/>
     * | element | locator | height | 
     */
    public int elementHeight(String loc) {
        return FitniumElementAPI.elementHeight(this, loc);
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
    public boolean elementHeightIs(String loc, String height) {
        return FitniumElementAPI.elementHeightIs(this, loc, height);
    }

    /**
     * Store the height on an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store element | locator | height in | name | 
     */
    public void storeElementHeightIn(String loc, String name) {
        FitniumElementAPI.storeElementHeightIn(this, loc, name);
    }

    /**
     * Returns the width in pixels of the element
     * @param loc Element locator
     * @return Width of element
     * 
     * <br/><br/>
     * | element | locator | width | 
     */
    public int elementWidth(String loc) {
        return FitniumElementAPI.elementWidth(this, loc);
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
    public boolean elementWidthIs(String loc, String height) {
        return FitniumElementAPI.elementWidthIs(this, loc, height);
    }

    /**
     * Store the width of the element in a fitnium variable
     * @param loc Locator of element to get width of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store element | locator | width in | name | 
     */
    public void storeElementWidthIn(String loc, String name) {
        FitniumElementAPI.storeElementWidthIn(this, loc, name);
    }

    /**
     * Get the position, relative to the containing frame of the left hand side of the element
     * @param loc	Locator of element
     * @return left ( or x position ) of element
     * 
     * <br/><br/>
     * | left position of element | locator | 
     */
    public int leftPositionOfElement(String loc) {
        return FitniumElementAPI.leftPositionOfElement(this, loc);
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
    public boolean leftPositionOfElementIs(String loc, String pos) {
        return FitniumElementAPI.leftPositionOfElementIs(this, loc, pos);
    }

    /**
     * Store the left position of the element in a fitnium variable
     * @param loc Locator of element to get left position of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store left position of element | locator | in | name | 
     */
    public void storeLeftPositionOfElementIn(String loc, String name) {
        FitniumElementAPI.storeLeftPositionOfElementIn(this, loc, name);
    }

    /**
     * Get the position, relative to the containing frame of the top side of the element
     * @param loc	Locator of element
     * @return top ( or y position ) of element
     * 
     * <br/><br/>
     * | top position of element | locator | 
     */
    public int topPositionOfElement(String loc) {
        return FitniumElementAPI.topPositionOfElement(this, loc);
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
    public boolean topPositionOfElementIs(String loc, String pos) {
        return FitniumElementAPI.topPositionOfElementIs(this, loc, pos);
    }

    /**
     * Store the top position of the element in a fitnium variable
     * @param loc Locator of element to get top position of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store top position of element | locator | in | name | 
     */
    public void storeTopPositionOfElementIn(String loc, String name) {
        FitniumElementAPI.storeTopPositionOfElementIn(this, loc, name);
    }

    /****************************************************************************
     * Attributes
     ****************************************************************************/
    /**
     * Check for an atttribute existing with specified name in any window open in browser
     * @return true if attribute exists
     * 
     * <br/><br/>
     * | does attribute with name | name | exist in any window | 
     * @REGEX
     */
    public boolean doesAttributeWithNameExistInAnyWindow(String name) {
        return FitniumAttributeAPI.doesAttributeWithNameExistInAnyWindow(this, name);
    }

    /**
     * Check for an atttribute existing with specified name in any window open in browser
     * @return true if attribute exists
     * 
     * <br/><br/>
     * | does attribute with name | name | and value | value | exist in any window | 
     * @REGEX
     * @REGEX
     */
    public boolean doesAttributeWithNameAndValueExistInAnyWindow(String name, String value) {
        return FitniumAttributeAPI.doesAttributeWithNameAndValueExistInAnyWindow(this, name, value);
    }

    /**
     * Stores the value of every instance of an attribute of a window as a list in a fitnium variable
     * @param attr Name of attribute to store
     * @param name Name of variable to store value in
     * 
     * <br/><br/>
     * | store attribute | attr | froom all windows in | name | 
     */
    public void storeAttributeFromAllWindowsIn(String attr, String name) {
        FitniumAttributeAPI.storeAttributeFromAllWindowsIn(this, attr, name);
    }

    /**
     * Check the value of an attribute is as specific
     * @param loc Locator of element
     * @param value Value to check for
     * @return true if value is as specified
     * 
     * <br/><br/>
     * | value of attribute | locator | is | value | 
     */
    public boolean valueOfAttributeIs(String loc, String value) {
        return FitniumAttributeAPI.valueOfAttributeIs(this, loc, value);
    }

    /**
     * Gets the value of an attribute
     * @param loc Locator of attribute to get value of
     * @return Value of attribute
     *
     * <br/><br/>
     * | attribute | name | value | 
     */
    public String attributeValue(String loc) {
        return FitniumAttributeAPI.attributeValue(this, loc);
    }

    /**
     * Stores value of an attribute in a fitnium variable
     * @param loc Locator of attribute 
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store attribute | attr | value in | name | 
     */
    public void storeAttributeValueIn(String loc, String name) {
        FitniumAttributeAPI.storeAttributeValueIn(this, loc, name);
    }

    /****************************************************************************
     * Cookies
     ****************************************************************************/
    /**
     * Create a Name Value pair cookie
     * @param name	Name of cookie to create
     * @param value Value of cookie to create
     * 
     * <br/><br/>
     * | create a cookie named | name | with value | value || 
     */
    public void createCookieNamedWithValue(String name, String value) {
        FitniumCookieAPI.createCookieNamedWithValue(this, name, value);
    }

    /**
     * Create a name value pair cookie with a path and maximum age
     * @param name	Name of cookie to create
     * @param value Value of cookie to create
     * @param path	Path of cookie to create
     * @param maxAge Max Age of cookie to create
     * 
     * <br/><br/>
     * | create a cookie named | name | with value | value | and path | path | and max age | age || 
     */
    public void createCookieNamedWithValueAndPathAndMaxAge(String name, String value, String path, String maxAge) {
        FitniumCookieAPI.createCookieNamedWithValueAndPathAndMaxAge(this, name, value, path, maxAge);
    }

    /**
     * Stores the option string of a cookie in a fitnium variable
     * @param path Path of cookie
     * @param maxAge Maximum age of cookie
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * |  store cookie with value | value | and max age | age | option string in | name | 
     */
    public void storeCookieWithValueAndMaxAgeOptionStringIn(String path, String maxAge, String name) {
        FitniumCookieAPI.storeCookieWithValueAndMaxAgeOptionStringIn(this, path, maxAge, name);
    }

    /**
     * Delete a cookie with the specified name
     * @param name Name of cookie to delete
     * 
     * <br/><br/>
     * | delete cookie named | name | 
     * @REGEX
     */
    public void deleteCookieNamed(String name) {
        FitniumCookieAPI.deleteCookieNamed(this, name);
    }

    /**
     * Delete a cookie with the specified name and path
     * @param name Name of cookie to delete
     * @param path Path of cookie to delete
     * 
     * <br/><br/>
     * | delete cookie named  | name | with path | path |
     * @REGEX
     * @REGEX
     */
    public void deleteCookieNamedWithPath(String name, String path) {
        FitniumCookieAPI.deleteCookieNamedWithPath(this, name, path);
    }

    /**
     * Get the value of the cookie
     * @return Value of cookie
     * 
     * <br/><br/>
     * | value of all cookies | 
     */
    public String valueOfAllCookies() {
        return FitniumCookieAPI.valueOfAllCookies(this);
    }

    /**
     * Check list of all cookies contains passed in values
     * @param List of cookies to check for
     * @return True if all cookies present
     * 
     * <br/><br/>
     * | cookies | cookies | are present | 
     */
    public boolean cookiesArePresent(String list) {
        return FitniumCookieAPI.cookiesArePresent(this, list);
    }

    /**
     * Store the value of all cookies in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of all cookies in | name | 
     */
    public void storeValueOfAllCookiesIn(String name) {
        FitniumCookieAPI.storeValueOfAllCookiesIn(this, name);
    }

    /**
     * Get the value of the cookie
     * @param name Name of cookie to get value of
     * @return Value of cookie
     * 
     * <br/><br/>
     * | value of cookie named | name | 
     * @REGEX
     */
    public String valueOfCookieNamed(String name) {
        return FitniumCookieAPI.valueOfCookieNamed(this, name);
    }

    /**
     * Check the value of cookie is as specified
     * @param name Name of cookie to check
     * @param value Value to check
     * @return true or false based on value
     * 
     * <br/><br/>
     * | value of cookie named | name | is | value | 
     * @REGEX
     */
    public boolean valueOfCookieNamedIs(String name, String value) {
        return FitniumCookieAPI.cookieNamedIsPresent(this, name);
    }

    /**
     * Store value of cookie in a fitnium variable
     * @param cookieName Name of cookie to store value of
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of cookie named | cookie | in | name |
     * @REGEX
     */
    public void storeValueOfCookieNamedIn(String cookieName, String name) {
        FitniumCookieAPI.storeValueOfCookieNamedIn(this, cookieName, name);
    }

    /**
     * Check if a cookie is present
     * @param name Name of cookie to check for
     * @return True if cookie exists
     * 
     * <br/><br/>
     * | cookie named | name | is present | 
     * @REGEX
     */
    public boolean cookieNamedIsPresent(String name) {
        return FitniumCookieAPI.cookieNamedIsPresent(this, name);
    }

    /**
     * Store if a cookie is present in a fitnium variable
     * @param cookie Name of cookie to check existence
     * @param name Name of variable to set the value of
     * 
     * <br/><br/>
     * | store cookie named | cookie | is present in | name | 
     * @REGEX
     */
    public void storeCookieNamedIsPresentIn(String cookie, String name) {
        FitniumCookieAPI.storeCookieNamedIsPresentIn(this, cookie, name);
    }

    public void deleteAllVisibleCookies () {
    	 FitniumCookieAPI.deleteAllVisibleCookies (this);
    }
    
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
    public void pressKeyDownInElement(String sequence, String loc) {
        FitniumKeyboardAPI.pressKeyDownInElement(this, sequence, loc);
    }

    /**
     * Send the key sequence as a series of key up messages to the element specified by the locator
     * @param sequence Key Sequence to send
     * @param loc Locator of the element
     * 
     * <br/><br/>
     * | release key | key | up in element | locator | 
     */
    public void releaseKeyUpInElement(String sequence, String loc) {
        FitniumKeyboardAPI.releaseKeyUpInElement(this, sequence, loc);
    }

    /**
     * Send the key sequence as a series of key presses ( up / down ) messages to the element specified by the locator
     * @param sequence Key Sequence to send
     * @param loc Locator of the element
     * 
     * <br/><br/>
     * | press key sequence | sequence | in element | locator |
     */
    public void pressKeySequenceInElement(String sequence, String loc) {
        FitniumKeyboardAPI.pressKeySequenceInElement(this, sequence, loc);
    }

    /**
     * Position the cursor to a specific location in the element
     * @param pos Position to set cursor at
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | set cursor at | postion | in element | locator | 
     */
    public void setCursorAtInElement(String pos, String loc) {
        FitniumKeyboardAPI.setCursorAtInElement(this, pos, loc);
    }

    /**
     * Get the cursor position of an element 
     * @param loc Locator of an element
     * @return Position of cursor in element
     * 
     * <br/><br/>
     * | get cursor position in element | locator | 
     */
    public int getCursorPositionInElement(String loc) {
        return FitniumKeyboardAPI.getCursorPositionInElement(this, loc);
    }

    /**
     * Store the cursor position of an element in a fitnium variable
     * @param loc Locator of element
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store cursor position in element | location | in | name | 
     */
    public void storeCursorPositionInElementIn(String loc, String name) {
        FitniumKeyboardAPI.storeCursorPositionInElementIn(this, loc, name);
    }

    /**
     * Simulate holding the Alt key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the alt key down | 
     */
    public void whileHoldingTheAltKeyDown() {
        FitniumKeyboardAPI.whileHoldingTheAltKeyDown(this);
    }

    /**
     * Simulate releasing the alt key
     * @return nothing
     *
     * <br/><br/>
     * | release the alt key |
     */
    public void releaseTheAltKey() {
        FitniumKeyboardAPI.releaseTheAltKey(this);
    }

    /**
     * Simulate holding the Control key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the control key down | 
     */
    public void whileHoldingTheControlKeyDown() {
        FitniumKeyboardAPI.whileHoldingTheControlKeyDown(this);
    }

    /**
     * Simulate releasing the control key
     * @return nothing
     * 
     * <br/><br/>
     * | release the control key | 
     */
    public void releaseTheControlKey() {
        FitniumKeyboardAPI.releaseTheControlKey(this);
    }

    /**
     * Simulate holding the Shift key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the shift key down | 
     */
    public void whileHoldingTheShiftKeyDown() {
        FitniumKeyboardAPI.whileHoldingTheShiftKeyDown(this);
    }

    /**
     * Simulate releasing the shift key
     * @return nothing
     * 
     * <br/><br/>
     * | release the shift key | 
     */
    public void releaseTheShiftKey() {
        FitniumKeyboardAPI.releaseTheShiftKey(this);
    }

    /**
     * Simulate holding the Meta key down
     * @return nothing
     * 
     * <br/><br/>
     * | while holding the meta key down | 
     */
    public void whileHoldingTheMetaKeyDown() {
        FitniumKeyboardAPI.whileHoldingTheMetaKeyDown(this);
    }

    /**
     * Simulate releasing the meta key
     * @return nothing
     * 
     * <br/><br/>
     * | release the meta key | 
     */
    public void releaseTheMetaKey() {
        FitniumKeyboardAPI.releaseTheMetaKey(this);
    }

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
    public void clickElement(String loc) {
        FitniumMouseAPI.clickElement(this, loc);
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
    public void clickElementAtAnd(String loc, String x, String y) {
        FitniumMouseAPI.clickElementAtAnd(this, loc, x, y);
    }

    /**
     * Double click an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | double click element | locator | 
     */
    public void doubleClickElement(String loc) {
        FitniumMouseAPI.doubleClickElement(this, loc);
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
    public void doubleClickElementAtAnd(String loc, String x, String y) {
        FitniumMouseAPI.doubleClickElementAtAnd(this, loc, x, y);
    }

    /**
     * Simulate clicking the mouse down on an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | click mouse down on element | locator | 
     */
    public void clickMouseDownOnElement(String loc) {
        FitniumMouseAPI.clickMouseDownOnElement(this, loc);
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
    public void clickMouseDownOnElementAtAnd(String loc, String x, String y) {
        FitniumMouseAPI.clickMouseDownOnElementAtAnd(this, loc, x, y);
    }

    /**
     * Release the mouse button on an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | release mouse up on element | locator | 
     */
    public void releaseMouseUpOnElement(String loc) {
        FitniumMouseAPI.releaseMouseUpOnElement(this, loc);
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
    public void releaseMouseUpOnElementAtAnd(String loc, String x, String y) {
        FitniumMouseAPI.releaseMouseUpOnElementAtAnd(this, loc, x, y);
    }

    /**
     * Move the mouse in an element
     * @param loc Locator of element 
     * 
     * <br/><br/>
     * | move mouse in element | locator | 
     */
    public void moveMouseInElement(String loc) {
        FitniumMouseAPI.moveMouseInElement(this, loc);
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
    public void moveMouseInElementToAnd(String loc, String x, String y) {
        FitniumMouseAPI.moveMouseInElementToAnd(this, loc, x, y);
    }

    /**
     * Simulates a user moving the mouse pointer away from the specified element.
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | move mouse out of element | locator | 
     */
    public void moveMouseOutOfElement(String loc) {
        FitniumMouseAPI.moveMouseOutOfElement(this, loc);
    }

    /**
     * Simulates a user hovering a mouse over the specified element. 
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | hover mouse over element | locator | 
     */
    public void hoverMouseOverElement(String loc) {
        FitniumMouseAPI.hoverMouseOverElement(this, loc);
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
    public void dragElementHorizontallyPixelsAndVerticallyPixels(String loc, String xOffset, String yOffset) {
        FitniumMouseAPI.dragElementHorizontallyPixelsAndVerticallyPixels(this, loc, xOffset, yOffset);
    }

    /**
     * Drag an element onto another element
     * @param locDraggee Locator of element to drag
     * @param locTarget Element to drop dragee onto
     * 
     * <br/><br/>
     * | drag element | locator | to element | target | 
     */
    public void dragElementToElement(String locDragee, String locTarget) {
        FitniumMouseAPI.dragElementToElement(this, locDragee, locTarget);
    }

    /****************************************************************************
     * Buttons
     ****************************************************************************/
    /**
     * Click a button
     * @param loc Locator of element to click
     * <br/><br/>
     * | click button | locator | 
     */
    public void clickButton(String loc) {
        FitniumButtonAPI.clickButton(this, loc);
    }

    /**
     * Check a button exists with specified Id
     * @return true if button exists
     * 
     * <br/><br/>
     * | does button with id | id | exist |
     * @REGEX
     */
    public boolean doesButtonWithIdExist(String id) {
        return FitniumButtonAPI.doesButtonWithIdExist(this, id);
    }

    /**
     * Checks if buttons with list of ids exist
     * @param ids List of buttons to check existence of
     * @return true or false if buttons exist
     * 
     * <br/><br/>
     * | do buttons with ids | ids | exist | 
     * @REGEX
     */
    public boolean doButtonsWithIdsExist(String ids) {
        return FitniumButtonAPI.doButtonsWithIdsExist(this, ids);
    }

    /**
     * Get ids of all buttons on screen in fitnium variable
     * @param name
     * 
     * <br/><br/>
     * | store all buttons in | name | 
     */
    public void storeAllButtonsIn(String name) {
        FitniumButtonAPI.storeAllButtonsIn(this, name);
    }

    /****************************************************************************
     * Edit Fields
     ****************************************************************************/
    /**
     * Checks to see if an edit field is in fact editable
     * @param loc Locator for edit field
     * @return true if edit field is editable
     * 
     * <br/><br/>
     * | input field | locator | is editable | 
     */
    public boolean inputFieldIsEditable(String loc) {
        return FitniumInputFieldAPI.inputFieldIsEditable(this, loc);
    }

    /**
     * Store if an input field is editable in a fitnium variable
     * @param loc Locator of input field to check
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store is input field | locator | editable in | name | 
     */
    public void storeInputFieldIsEditableIn(String loc, String name) {
        FitniumInputFieldAPI.storeInputFieldIsEditableIn(this, loc, name);
    }

    /**
     * Enters the supplied text into the specified field
     * @param text Text to enter into field
     * @param loc Locator for edit field
     * 
     * <br/><br/>
     * | enter | text | in input field | locator | 
     */
    public void enterInInputField(String text, String loc) {
        FitniumInputFieldAPI.enterInInputField(this, text, loc);
    }

    /**
     * Simulates keystroke events on the specified element, as though you typed the value key-by-key. 
     * @param text - the value to type
     * @param loc - an element locator
     * 
     * <br/><br/>
     * | type | text | in input field | locator | 
     */
    public void typeInInputField(String text, String loc) {
        FitniumInputFieldAPI.typeInInputField(this, text, loc);
    }

    /**
     * Check that the input field with the specified Id exists
     * @param id ID to check for
     * @return true if input field exists
     * 
     * <br/><br/>
     * | does input field with id | id | exist | 
     * @REGEX
     */
    public boolean doesInputFieldWithIdExist(String id) {
        return FitniumInputFieldAPI.doesInputFieldWithIdExist(this, id);
    }

    /**
     * Checks to see if a list of input fields exist
     * @param ids List of input field ids to check existence of
     * @return true of false if all fields exist
     * 
     * <br/><br/>
     * | do input fields with ids | ids | exist | 
     * @REGEX
     */
    public boolean doInputFieldsWithIdsExist(String ids) {
        return FitniumInputFieldAPI.doInputFieldsWithIdsExist(this, ids);
    }

    /**
     * Stores all ids of input fields in fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store all fields in | name |  
     */
    public void storeAllFieldsIn(String name) {
        FitniumInputFieldAPI.storeAllFieldsIn(this, name);
    }

    /****************************************************************************
     * Tables
     ****************************************************************************/
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
    public String textFromCellAtAndInTable(String x, String y, String loc) {
        return FitniumTableAPI.textFromCellAtAndInTable(this, x, y, loc);
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
    public boolean textFromCellAtAndInTableIs(String x, String y, String loc, String text) {
        return FitniumTableAPI.textFromCellAtAndInTableIs(this, x, y, loc, text);
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
    public void storeTextFromCellAtAndInTableIn(String x, String y, String loc, String name) {
        FitniumTableAPI.storeTextFromCellAtAndInTableIn(this, x, y, loc, name);
    }

    /****************************************************************************
     * Links
     ****************************************************************************/
    /**
     * Click a link with the specific text
     * @param text Text of the link to click
     * 
     * <br/><br/>
     * | click link with text | text | 
     */
    public void clickLinkWithText(String text) {
        FitniumLinkAPI.clickLinkWithText(this, text);
    }

    /**
     * Click a link identified by the location
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click link | locator | 
     */
    public void clickLink(String loc) {
        FitniumLinkAPI.clickLink(this, loc);
    }

    /**
     * Check a link with the specified ID exists
     * @param id ID to check for
     * @return true of link with id exists
     * 
     * <br/><br/>
     * | does link with id | id | exist | 
     */
    public boolean doesLinkWithIdExist(String id) {
        return FitniumLinkAPI.doesLinkWithIdExist(this, id);
    }

    /**
     * Checks if a list of links exist on the screen
     * @param ids List of links to check for
     * @return true or false if all links exist
     * 
     * <br/><br/>
     * | do links with ids | ids | exist | 
     */
    public boolean doLinksWithIdsExist(String ids) {
        return FitniumLinkAPI.doLinksWithIdsExist(this, ids);
    }

    /**
     * Stores the ids of all links in a list 
     * @param name Name of variable to store ids
     * 
     * <br/><br/>
     * | store all links in | name | 
     */
    public void storeAllLinksIn(String name) {
        FitniumLinkAPI.storeAllLinksIn(this, name);
    }

    /****************************************************************************
     * Single Select List
     ****************************************************************************/

    /**
     * Select a single option in a list
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     * 
     * <br/><br/>
     * | select option | option | in list | locator | 
     */
    public void selectOptionInList(String optionloc, String elementloc) {
        FitniumListAPI.selectOptionInList(this, optionloc, elementloc);
    }
    /**
     * Select a single item in a list based on the label
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     *
     * <br/><br/>
     * | select label | label | in list | locator |
     */
    public void selectLabelInList(String optionloc, String elementloc) {
        FitniumListAPI.selectLabelInList(this, optionloc, elementloc);
    }
    /**
     * Select a single item in a list based on the value
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     *
     * <br/><br/>
     * | select value | value | in list | locator |
     */
    public void selectValueInList(String optionloc, String elementloc) {
        FitniumListAPI.selectValueInList(this, optionloc, elementloc);
    }
    /**
     * Select a single item in a list based on the Id
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     *
     * <br/><br/>
     * | select id | id | in list | locator |
     */
    public void selectIdInList(String optionloc, String elementloc) {
        FitniumListAPI.selectIdInList(this, optionloc, elementloc);
    }
    /**
     * Select a single item in a list based on the index
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     *
     * <br/><br/>
     * | select index | index | in list | locator |
     */
    public void selectIndexInList(String optionloc, String elementloc) {
        FitniumListAPI.selectIndexInList(this, optionloc, elementloc);
    }

    /**
     * Check to see if a list has anything selected
     * @param loc Locator of element to check
     * 
     * <br/><br/>
     * | does list | locator | have selection | 
     */
    public boolean doesListHaveSelection(String loc) {
        return FitniumListAPI.doesListHaveSelection(this, loc);
    }

    /**
     * Stores whether a list has an item selected in a fitnium variable
     * @param loc Locator of list to check
     * @param name Name of variable to hold value
     * 
     * <br/><br/>
     * | store does list | list | have selection in | 
     */
    public void storeDoesListHaveSelectionIn(String loc, String name) {
        FitniumListAPI.storeDoesListHaveSelectionIn(this, loc, name);
    }

    /**
     * Get the ID of the option selected in the list
     * @param loc Locator of list element
     * 
     * <br/><br/>
     * | list | list | selected item id | 
     */
    public String listSelectedItemId(String loc) {
        return FitniumListAPI.listSelectedItemId(this, loc);
    }

    /**
     * Stores the id of the selected item in the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | selected item id in | name | 
     */
    public void storeListSelectedItemIdIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemIdIn(this, loc, name);
    }

    /**
     * Checks to see if the item selected has the ID as specified
     * @param loc Locator of list element
     * @param id Id to check for
     * 
     * <br/><br/>
     * | list | locator | selected item id is | id | 
     * | check | list | locator | selected item id is | id | true "
     */
    public boolean listSelectedItemIdIs(String loc, String id) {
        return FitniumListAPI.listSelectedItemIdIs(this, loc, id);
    }

    /**
     * Gets the index of the option selected in the list 
     * @param loc Locator of the list element
     * 
     * <br/><br/>
     * | list | locator | selected item index | 
     */
    public String listSelectedItemIndex(String loc) {
        return FitniumListAPI.listSelectedItemIndex(this, loc);
    }

    /**
     * Stores the index of the selected item in the list
     * @param loc Locator of the list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item index in | name || 
     */
    public void storeListSelectedItemIndexIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemIndexIn(this, loc, name);
    }

    /**
     * Checks to see if the index of the option selected is as specified
     * @param loc Locator of list element
     * @param index Index to check  for
     * 
     * <br/><br/>
     * | list | locator | selected item index | index | 
     * | check | list | locator | selected item index | index | true |
     */
    public boolean listSelectedItemIndexIs(String loc, String index) {
        return FitniumListAPI.listSelectedItemIndexIs(this, loc, index);
    }

    /**
     * Get the label of the selected option in the list
     * @param loc locator of list element
     * 
     * <br/><br/>
     * | list | locator | selected item label | 
     */
    public String listSelectedItemLabel(String loc) {
        return FitniumListAPI.listSelectedItemLabel(this, loc);
    }

    /**
     * Stores the label of the selected item in the list in a fitnium variable
     * @param loc Locator of list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item label in | name | 
     */
    public void storeListSelectedItemLabelIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemLabelIn(this, loc, name);
    }

    /**
     * Checks if the label of the selected option in the list is as specified
     * @param loc locator of list element
     * @return true if Label is correct
     * 
     * <br/><br/>
     * | list | locator | selected item label is | label | 
     * | check | list | locator | selected item label is | label | true |
     */
    public boolean listSelectedItemLabelIs(String loc, String label) {
        return FitniumListAPI.listSelectedItemLabelIs(this, loc, label);
    }

    /**
     * Gets the value of the selected list item option
     * @param loc locator of list element
     * 
     * <br/><br/>
     * | list | locator | selected item value | 
     */
    public String listSelectedItemValue(String loc) {
        return FitniumListAPI.listSelectedItemValue(this, loc);
    }

    /**
     * Store the value of the selected item of the list in a fitnium variable
     * @param loc Locator of the list
     * @param name Name of variable to set value in 
     * 
     * <br/><br/>
     * | store list | locator | selected item value in | name | 
     */
    public void storeListSelectedItemValueIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemValueIn(this, loc, name);
    }

    /**
     * Checks if the selected option has the value as specified
     * @param loc locator of list element
     * @param value Value of option to check
     * @return True if value is as specified
     * 
     * <br/><br/>
     * | list | locator | selected item value | value | 
     * | check | list | locator | selected item value is | value | true | 
     */
    public boolean listSelectedItemValueIs(String loc, String value) {
        return FitniumListAPI.listSelectedItemValueIs(this, loc, value);
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
    public void addSelectionOptionToList(String optionloc, String elementloc) {
        FitniumListAPI.addSelectionOptionToList(this, optionloc, elementloc);
    }
    public void addSelectionLabelToList(String optionloc, String elementloc) {
        FitniumListAPI.addSelectionLabelToList(this, optionloc, elementloc);
    }
    public void addSelectionValueToList(String optionloc, String elementloc) {
        FitniumListAPI.addSelectionValueToList(this, optionloc, elementloc);
    }
    public void addSelectionIdToList(String optionloc, String elementloc) {
        FitniumListAPI.addSelectionIdToList(this, optionloc, elementloc);
    }
    public void addSelectionIndexToList(String optionloc, String elementloc) {
        FitniumListAPI.addSelectionIndexToList(this, optionloc, elementloc);
    }

    /**
     * Remove an option from the current set of those selected in the list
     * @param optionloc Locator of the option, normally its name/value
     * @param elementloc Locator of the element ( list )
     * 
     * <br/><br/>
     * | remove selection | selection | option from list | locator | 
     */
    public void removeSelectionOptionFromList(String optionloc, String elementloc) {
        FitniumListAPI.removeSelectionOptionFromList(this, optionloc, elementloc);
    }
    public void removeSelectionLabelFromList(String optionloc, String elementloc) {
        FitniumListAPI.removeSelectionLabelFromList(this, optionloc, elementloc);
    }
    public void removeSelectionValueFromList(String optionloc, String elementloc) {
        FitniumListAPI.removeSelectionValueFromList(this, optionloc, elementloc);
    }
    public void removeSelectionIdFromList(String optionloc, String elementloc) {
        FitniumListAPI.removeSelectionIdFromList(this, optionloc, elementloc);
    }
    public void removeSelectionIndexFromList(String optionloc, String elementloc) {
        FitniumListAPI.removeSelectionIndexFromList(this, optionloc, elementloc);
    }

    /**
     * Get a comma seperated list ids of the selected options
     * @param loc locator of list element
     * @return CSL of selected IDs
     * 
     * <br/><br/>
     * | list | locator | selected items ids | 
     */
    public String listSelectedItemIds(String loc) {
        return FitniumListAPI.listSelectedItemIds(this, loc);
    }

    /**
     * Store the selected item ids of the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | selected item ids in | name | 
     */
    public void storeListSelectedItemIdsIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemIdsIn(this, loc, name);
    }

    /**
     * Check if the specified ids match to the set of selected options in the list
     * @param loc locator of list element
     * @param ids CSL of ids to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected items ids are | ids | 
     * | check | list | locator | selected items ids are | ids | 
     */
    public boolean listSelectedItemIdsAre(String loc, String ids) {
        return FitniumListAPI.listSelectedItemIdsAre(this, loc, ids);
    }

    /**
     * Get a comma seperated list indexes of the selected options
     * @param loc locator of list element
     * @return CSL of selected indexes
     * 
     * <br/><br/>
     * | list | locator | selected item indexes || 
     */
    public String listSelectedItemIndexes(String loc) {
        return FitniumListAPI.listSelectedItemIndexes(this, loc);
    }

    /**
     * Stores the indexes of the selected items in the list in a fitnium variable
     * @param loc Locator of list
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item indexes in | name | 
     */
    public void storeListSelectedItemIndexesIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemIndexesIn(this, loc, name);
    }

    /**
     * Check if the specified indexes match to the set of selected options in the list
     * @param loc locator of list element
     * @param indexes CSL of indexes to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item indexes are | indexes | 
     * | check | list | locator | selected item indexes are | indexes | 
     */
    public boolean listSelectedItemIndexesAre(String loc, String indexes) {
        return FitniumListAPI.listSelectedItemIndexesAre(this, loc, indexes);
    }

    /**
     * Get a comma seperated list labels of the selected options
     * @param loc locator of list element
     * @return CSL of selected labels
     * 
     * <br/><br/>
     * | list | locator | selected item labels | 
     */
    public String listSelectedItemLabels(String loc) {
        return FitniumListAPI.listSelectedItemLabels(this, loc);
    }

    /**
     * Store the labels of the selected items in a fitnium variable
     * @param loc Locator of list
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store list | locator | selected item labels in | name | 
     */
    public void storeListSelectedItemLabelsIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemLabelsIn(this, loc, name);
    }

    /**
     * Check if the specified labels match to the set of selected options in the list
     * @param loc locator of list element
     * @param labels CSL of labels to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item labels are | labels | 
     * | check | list | locator | selected item labels are | labels | true |
     */
    public boolean listSelectedItemLabelsAre(String loc, String labels) {
        return FitniumListAPI.listSelectedItemLabelsAre(this, loc, labels);
    }

    /**
     * Get a comma seperated list values of the selected options
     * @param loc locator of list element
     * @return CSL of selected values
     * 
     * <br/><br/>
     * | list | locator | selected items values | 
     */
    public String listSelectedItemValues(String loc) {
        return FitniumListAPI.listSelectedItemValues(this, loc);
    }

    /**
     * Store the values of the list selected items in a fitnium variable
     * @param loc Locator of list to get values
     * @param name Name of variable to set value in 
     * 
     * <br/><br/>
     * | store list | locator | selected item values in | name | 
     */
    public void storeListSelectedItemValuesIn(String loc, String name) {
        FitniumListAPI.storeListSelectedItemValuesIn(this, loc, name);
    }

    /**
     * Check if the specified values match to the set of selected options in the list
     * @param loc locator of list element
     * @param values CSL of values to check for
     * @return true if all IDs are selected
     * 
     * <br/><br/>
     * | list | locator | selected item values are | values | 
     * | check | list | locator | selected item values are | values | 
     */
    public boolean listSelectedItemValuesAre(String loc, String values) {
        return FitniumListAPI.listSelectedItemValuesAre(this, loc, values);
    }

    /**
     * Get a command seperated list of the options in a List element
     * @param loc	Locator for the list
     * @return Comma Seperated list of options
     * 
     * <br/><br/>
     * | list | locator | options | 
     */
    public String listOptions(String loc) {
        return FitniumListAPI.listOptions(this, loc);
    }

    /**
     * Store the options from the list in a fitnium variable
     * @param loc
     * @param name
     * 
     * <br/><br/>
     * | store list | locator | options in | name | 
     */
    public void storeListOptionsIn(String loc, String name) {
        FitniumListAPI.storeListOptionsIn(this, loc, name);
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
    public boolean doesListContainOptions(String loc, String options) {
        return FitniumListAPI.doesListContainOptions(this, loc, options);
    }

    /**
     * Unselects all of the selected options in a multi-select element.
     * @param loc	Locator for list
     * 
     * <br/><br/>
     * | remove all selections from list | locator | 
     */
    public void removeAllSelectionsFromList(String loc) {
        FitniumListAPI.removeAllSelectionsFromList(this, loc);
    }

    /****************************************************************************
     * Radio Buttons
     ****************************************************************************

    /**
     * Click a Radio Button
     * @param loc Locator of element
     *
     * <br/><br/>
     * | set radio button | locator | to state | checked|unchecked |
     */
    public void setRadioButtonToState(String loc, String state) {
        FitniumRadioButtonAPI.setRadioButtonToState(this, loc, state);
    }

    /**
     * Click a radio button
     * @param loc Locator of element
     *
     * <br/><br/>
     * | set radio button | locator | to click | checked|unchecked |
     */
    public void setRadioButtonToClick(String loc, String state) {
        FitniumRadioButtonAPI.setRadioButtonToClick(this, loc, state);
    }

    /**
     * Click a radio button element
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click the radio button | locator | 
     */
    public void clickRadioButton(String loc) {
        FitniumRadioButtonAPI.clickRadioButton(this, loc);
    }

    /**
     * Unclick a radio button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | unclick the radio button | locator | 
     */
    public void unclickRadioButton(String loc) {
        FitniumRadioButtonAPI.unclickRadioButton(this, loc);
    }

    /**
     * Check if a radio button is checked/clicked or not
     * @param loc Locator of element
     * @return true if radio button is checked
     * 
     * <br/><br/>
     * | is radio button | locator | checked | 
     */
    public boolean isRadioButtonChecked(String loc) {
        return FitniumRadioButtonAPI.isRadioButtonChecked(this, loc);
    }

    /**
     * Stores if radio button is check in a fitnium variable
     * @param loc Locator of radio button to check
     * @param name Name of variable to set value
     * 
     * <br/><br/>
     * | store is radio button | locator | button checked in | name | 
     */
    public void storeIsRadioButtonCheckedIn(String loc, String name) {
        FitniumRadioButtonAPI.storeIsRadioButtonCheckedIn(this, loc, name);
    }

    /****************************************************************************
     * Check Boxes
     ****************************************************************************/

    /**
     * Click a check box
     * @param loc Locator of element
     *
     * <br/><br/>     * | set check box | locator | to  | checked|unchecked |

     * | set check box | locator | to state | checked|unchecked |
     */
    public void setCheckBoxToState(String loc, String state) {
        FitniumCheckBoxAPI.setCheckBoxToState(this, loc, state);
    }

    /**
     * Click a check box
     * @param loc Locator of element
     *
     * <br/><br/>     * | set check box | locator | to  | checked|unchecked |

     * | set check box | locator | to click | checked|unchecked |
     */
    public void setCheckBoxToClick(String loc, String state) {
        FitniumCheckBoxAPI.setCheckBoxToClick(this, loc, state);
    }

    /**
     * Click a check box
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click check box | locator | 
     */
    public void clickCheckBox(String loc) {
        FitniumCheckBoxAPI.clickCheckBox(this, loc);
    }

    /**
     * Unclick a check box
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | unclick check box | locator | 
     */
    public void unclickCheckBox(String loc) {
        FitniumCheckBoxAPI.unclickCheckBox(this, loc);
    }

    /**
     * Check if check box is checked !!
     * @param loc Locator of element
     * @return true if check box is checked
     * 
     * <br/><br/>
     * | is check box checked | locator | 
     */
    public boolean isCheckBoxChecked(String loc) {
        return FitniumCheckBoxAPI.isCheckBoxChecked(this, loc);
    }

    /**
     * Stores if a check box is checked in a fitnim variable
     * @param loc Locator of element
     * @param name Name of the variable to set
     * 
     * <br/><br/>
     * | store is check box | locator | checked in | name | 
     */
    public void storeIsCheckBoxCheckedIn(String loc, String name) {
        FitniumCheckBoxAPI.storeIsCheckBoxCheckedIn(this, loc, name);
    }

    /****************************************************************************
     * Forms
     ****************************************************************************/
    /**
     * Submit the specified form, same as clicking the submit button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | submit form | 
     */
    public void submitForm(String loc) {
        FitniumFormAPI.submitForm(this, loc);
    }

    /**
     * Submit the specified form, same as clicking the submit button
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | click submit | 
     */
    public void clickSubmit(String loc) {
        FitniumFormAPI.clickSubmit(this, loc);
    }

    /****************************************************************************
     * Window Management
     ****************************************************************************/
    /**
     * Open the specified URL in a new windown and assign the specified ID to the window
     * @param url URL to open
     * @param id ID to assign to window
     * 
     * <br/><br/>
     * | open url | url | in window and assign id | id || 
     */
    public void openUrlInWindowAndAssignId(String url, String id) {
        FitniumWindowAPI.openUrlInWindowAndAssignId(this, url, id);
    }

    /**
     * Set the focus to the currently selected window
     * @return nothing
     * 
     * <br/><br/>
     * | set focus to currently selected window | 
     */
    public void setFocusToCurrentlySelectedWindow() {
        FitniumWindowAPI.setFocusToCurrentlySelectedWindow(this);
    }

    /**
     * Maximize the currently selected window
     * @return nothing
     * 
     * <br/><br/>
     * | maximise currently selected window | 
     */
    public void maximiseCurrentlySelectedWindow() {
        FitniumWindowAPI.maximiseCurrentlySelectedWindow(this);
    }

    /**
     * Select the frame specified by the locator
     * @param loc Locator of frame
     * 
     * <br/><br/>
     * | select frame | locator | 
     */
    public void selectFrame(String loc) {
        FitniumWindowAPI.selectFrame(this, loc);
    }

    /**
     * Select the window with the specified ID
     * @param id ID of window to select
     * 
     * <br/><br/>
     * | select window with id | id | 
     */
    public void selectWindowWithId(String id) {
        FitniumWindowAPI.selectWindowWithId(this, id);
    }

    /**
     * Select the window with the specified title
     * @param title Title of window to select
     * 
     * <br/><br/>
     * | select window with title | title | 
     */
    public void selectWindowWithTitle(String title) {
        FitniumWindowAPI.selectWindowWithTitle(this, title);
    }

    /**
     * Select the window with the specified Name
     * @param name Name of window to select
     * 
     * <br/><br/>
     * | select window with name | 
     */
    public void selectWindowWithName(String name) {
        FitniumWindowAPI.selectWindowWithName(this, name);
    }

    /**
     * Select the window with the associated javascript variable
     * @param var Variable of associated window to select
     * 
     * <br/><br/>
     * | select window associated with variable | var | 
     */
    public void selectWindowAssociatedWithVariable(String var) {
        FitniumWindowAPI.selectWindowAssociatedWithVariable(this, var);
    }

    /**
     * Checks to see if a window with the specified ID exits
     * @param id ID to check for
     * @return true if window exists with specified id
     * 
     * <br/><br/>
     * | does window with id | id | exist | 
     */
    public boolean doesWindowWithIdExist(String id) {
        return FitniumWindowAPI.doesWindowWithIdExist(this, id);
    }

    /**
     * Do windows with the ids in the list exist
     * @param ids List of ids of windows to search for
     * @return true or false if all windows exist 
     * 
     * <br/><br/>
     * | do windows with ids | ids | exist | 
     */
    public boolean doWindowsWithIdsExist(String ids) {
        return FitniumWindowAPI.doWindowsWithIdsExist(this, ids);
    }

    /**
     * Stores all window ids in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store all window ids in | name | 
     */
    public void storeAllWindowIdsIn(String name) {
        FitniumWindowAPI.storeAllWindowIdsIn(this, name);
    }

    /**
     * Checks to see if a window with the specified name exits
     * @param name Name to check for
     * @return true if window exists with specified name
     * 
     * <br/><br/>
     * | dows window with name | name | exist | 
     */
    public boolean doesWindowWithNameExist(String name) {
        return FitniumWindowAPI.doesWindowWithNameExist(this, name);
    }

    /**
     * Checks if windows with names exist 
     * @param names List of names to search for
     * @return true or false if all windows exist
     * 
     * <br/><br/>
     * | do windows with names | names | exist | 
     */
    public boolean doWindowsWithNamesExist(String names) {
        return FitniumWindowAPI.doWindowsWithNamesExist(this, names);
    }

    /**
     * Stores the names of all windows in a fitnium variable
     * @param name Name of variable to set value
     * 
     * <br/><br/>
     * | store all window names in | names | 
     */
    public void storeAllWindowNamesIn(String name) {
        FitniumWindowAPI.storeAllWindowNamesIn(this, name);
    }

    /**
     * Checks to see if a window with the specified title exits
     * @param title Title to check for
     * @return true if window exists with specified title
     * 
     * <br/><br/>
     * | does window with title | title | exist | 
     */
    public boolean doesWindowWithTitleExist(String title) {
        return FitniumWindowAPI.doesWindowWithTitleExist(this, title);
    }

    /**
     * Checks if windows with all titles exist 
     * @param titles
     * @return true or false if all windows exist
     * 
     * <br/><br/>
     * | do windows with titles | titles | exist | 
     */
    public boolean doWindowsWithTitlesExist(String titles) {
        return FitniumWindowAPI.doWindowsWithTitlesExist(this, titles);
    }

    /**
     * Stores all windows titles in a fitnium variable
     * @param name Name of variable to set
     *
     * <br/><br/>
     * | store all window titles in | name | 
     */
    public void storeAllWindowTitlesIn(String name) {
        FitniumWindowAPI.storeAllWindowTitlesIn(this, name);
    }

    /**
     * Returns the title of the current page
     * @return title of current page
     * 
     * <br/><br/>
     * | title of current page | 
     */
    public String titleOfCurrentPage() {
        return FitniumWindowAPI.titleOfCurrentPage(this);
    }

    /**
     * Stores the title of the current page in a fitnium variable 
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store title of current page in | name | 
     */
    public void storeTitleOfCurrentPageIn(String name) {
        FitniumWindowAPI.storeTitleOfCurrentPageIn(this, name);
    }

    /**
     * Checks the value of the title of the current page
     * @param text Text to check the title against
     * @return true of text matches title of current page
     * 
     * <br/><br/>
     * | title of current page is | title | 
     */
    public boolean titleOfCurrentPageIs(String text) {
        return FitniumWindowAPI.titleOfCurrentPageIs(this, text);
    }

    /**
     * Get the absolute URL of the current page
     * @return absolute URL of current page
     * 
     * <br/><br/>
     * | absolute url of current page | 
     */
    public String absoluteUrlOfCurrentPage() {
        return FitniumWindowAPI.absoluteUrlOfCurrentPage(this);
    }

    /**
     * Stores the absolute url of current page in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store absolute url of current page in | name | 
     */
    public void storeAbsoluteUrlOfCurrentPageIn(String name) {
        FitniumWindowAPI.storeAbsoluteUrlOfCurrentPageIn(this, name);
    }

    /**
     * Checks that the absolute URL of the current page is as specified
     * @param url URL to check for
     * @return true of URL specified is the absolute of the current page
     * 
     * <br/><br/>
     * | absolute url of current page is | url | 
     */
    public boolean absoluteUrlOfCurrentPageIs(String url) {
        return FitniumWindowAPI.absoluteUrlOfCurrentPageIs(this, url);
    }

    /****************************************************************************
     * Javacript
     ****************************************************************************/
    /**
     * Check to see if the last action to occur was a Javascript Alert window 
     * @return true if Alert occurred
     * 
     * <br/><br/>
     * | has alert occurred | 
     */
    public boolean hasAlertOccurred() {
        return FitniumJavascriptAPI.hasAlertOccurred(this);
    }

    /**
     * Stores if an alert has occured in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store has alert occurred in | name | 
     */
    public void storeHasAlertOccuredIn(String name) {
        FitniumJavascriptAPI.storeHasAlertOccuredIn(this, name);
    }

    /**
     * Check to see if the last action to occur was a Javascript Prompt window 
     * @return true if Prompt occurred
     * 
     * <br/><br/>
     * | has prompted occurred | 
     */
    public boolean hasPromptOccurred() {
        return FitniumJavascriptAPI.hasPromptOccurred(this);
    }

    /**
     * @param name Name of variable to set
     * Stores if a prompt has occured in a fitnium variable
     * 
     * <br/><br/>
     * | store has prompt occurred in | name | 
     */
    public void storeHasPromptOccuredIn(String name) {
        FitniumJavascriptAPI.storeHasPromptOccuredIn(this, name);
    }

    /**
     * Check to see if the last action to occur was a Javascript Confirmation window 
     * @return true if Confirmation occurred
     * 
     * <br/><br/>
     * | has confirmation occurred | 
     */
    public boolean hasConfirmationOccurred() {
        return FitniumJavascriptAPI.hasConfirmationOccurred(this);
    }

    /**
     * Stores if an confirmation has occured in a fitnium variable
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store has confirmation occurred in | name | 
     */
    public void storeHasConfirmationOccuredIn(String name) {
        FitniumJavascriptAPI.storeHasConfirmationOccuredIn(this, name);
    }

    /**
     * On the next javascript Prompt window, answer with the specified text
     * @param answer Text to respond to window with
     * 
     * <br/><br/>
     * |  answer | answer | on next prompt | 
     */
    public void answerOnNextPrompt(String answer) {
        FitniumJavascriptAPI.answerOnNextPrompt(this, answer);
    }

    /**
     * On the next confirmation window choose cancel, otherwise the default would be to choose OK
     * @return nothing
     *
     * <br/><br/>
     * | choose cancel on next confirmation | 
     */
    public void chooseCancelOnNextConfirmation() {
        FitniumJavascriptAPI.chooseCancelOnNextConfirmation(this);
    }

    /**
     * On the next confirmation window choose ok
     * @return nothing
     *
     * <br/><br/>
     * | choose ok on next confirmation | 
     */
    public void chooseOkOnNextConfirmation() {
        FitniumJavascriptAPI.chooseOkOnNextConfirmation(this);
    }

    /**
     * Gets the alert dialog message
     * @return Alert dialog message
     * 
     * <br/><br/>
     * | alert dialog message | 
     */
    public String alertDialogMessage() {
        return FitniumJavascriptAPI.alertDialogMessage(this);
    }

    /**
     * Checks if the alert message is as specified
     * @param message Text of message to check 
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | alert dialog message is | message | 
     */
    public boolean alertDialogMessageIs(String message) {
        return FitniumJavascriptAPI.alertDialogMessageIs(this, message);
    }

    /**
     * Stores alert message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store alert dialog message in | name | 
     */
    public void storeAlertDialogMessageIn(String name) {
        FitniumJavascriptAPI.storeAlertDialogMessageIn(this, name);
    }

    /**
     * Gets the confirmation message
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | confirmation dialog message is | message | 
     */
    public String confirmationDialogMessage() {
        return FitniumJavascriptAPI.confirmationDialogMessage(this);
    }

    /**
     * Stores confirmation message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store confirmation dialog message in | name | 
     */
    public void storeConfirmationDialogMessageIn(String name) {
        FitniumJavascriptAPI.storeConfirmationDialogMessageIn(this, name);
    }

    /**
     * Gets the prompt message
     * @return true or false depending on value 
     * 
     * <br/><br/>
     * | prompt dialog message is | message | 
     */
    public String promptDialogMessage() {
        return FitniumJavascriptAPI.promptDialogMessage(this);
    }

    /**
     * Stores prompt message in a fitnium variable
     * @param name Name of variable to store value in 
     * 
     * <br/><br/>
     * | store prompt dialog message in | name | 
     */
    public void storePromptDialogMessageIn(String name) {
        FitniumJavascriptAPI.storePromptDialogMessageIn(this, name);
    }

    /**
     * Closes the most recent alert dialog
     * @return nothing
     *
     * <br/><br/>
     * | close alert dialog | 
     */
    public void closeAlertDialog() {
        FitniumJavascriptAPI.closeAlertDialog(this);
    }

    /**
     * Close the most recent confirmation dialog
     * @return nothing
     *
     * <br/><br/>
     * | close confirmation dialog | 
     */
    public void closeConfirmationDialog() {
        FitniumJavascriptAPI.closeConfirmationDialog(this);
    }

    /**
     * Closes the most recent prompt dialog
     * @return nothing
     *
     * <br/><br/>
     * | close prompt dialog | 
     */
    public void closePromptDialog() {
        FitniumJavascriptAPI.closePromptDialog(this);
    }

    /**
     * Check the last Confirmation dialog has the following message
     * @param message Text to check for
     * @return true if dialog had the specified message
     * 
     * <br/><br/>
     * | confirmation dialog message is | message | 
     */
    public boolean confirmationDialogMessageIs(String message) {
        return FitniumJavascriptAPI.confirmationDialogMessageIs(this, message);
    }

    /**
     * Check the last Question dialog has the following message
     * @param message Text to check for
     * @return true if dialog had the specified message
     * 
     * <br/><br/>
     * | prompt dialog message is | message | 
     */
    public boolean promptDialogMessageIs(String message) {
        return FitniumJavascriptAPI.promptDialogMessageIs(this, message);
    }

    /**
     * Return the value of evaluating the specified javascript snippet
     * @return Value of executed javascript
     * 
     * <br/><br/>
     * | value of javascript | script | 
     */
    public String valueOfJavascript(String script) {
        return FitniumJavascriptAPI.valueOfJavascript(this, script);
    }

    /**
     * Stores value of a javascript evaluation in a fitnium variable
     * @param script Script to evaluate
     * @param name Name of variable to set
     * 
     * <br/><br/>
     * | store value of javascript | script | in | name | 
     */
    public void storeValueOfJavascriptIn(String script, String name) {
        FitniumJavascriptAPI.storeValueOfJavascriptIn(this, script, name);
    }

    /**
     * Fire a javascript event against an element
     * @param eventName Name of event to fire. Note the element needs to support the event i.e OnMouseDown ()
     * @param loc Locator of element
     * 
     * <br/><br/>
     * | fire event | name | against element | locator | 
     */
    public void fireEventAgainstElement(String eventName, String loc) {
        FitniumJavascriptAPI.fireEventAgainstElement(this, eventName, loc);
    }

    /****************************************************************************
     * Wait Commands
     ****************************************************************************/

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator |
     */
    public void waitForElement(String loc) {
        FitniumWaitAPI.waitForElement(this, loc);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be present |
     */
    public void waitForElementToBePresent(String loc) {
        FitniumWaitAPI.waitForElementToBePresent(this, loc);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be not present |
     */
    public void waitForElementToBeNotPresent(String loc) {
        FitniumWaitAPI.waitForElementToBeNotPresent(this, loc);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be visible |
     */
    public void waitForElementToBeVisible(String loc) {
        FitniumWaitAPI.waitForElementToBeVisible(this, loc);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     * 
     * <br/><br/>
     * | wait for element | locator | to be not visible |
     */
    public void waitForElementToBeNotVisible(String loc) {
        FitniumWaitAPI.waitForElementToBeNotVisible(this, loc);
    }

    /**
     * Waits for all buttons to be present
     * @param buttons Comma Sep list of buttons to wait for
     * 
     * <br/><br/>
     * | wait for all buttons | buttons | present |
     */
    public void waitForButtonsToBePresent(String text) {
        FitniumWaitAPI.waitForButtonsToBePresent(this, text);
    }

    /**
     * Waits for an Alert box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for alert |
     */
    public void waitForAlertToOccur() {
        FitniumWaitAPI.waitForAlertToOccur(this);
    }

    /**
     * Waits for an Prompt box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for prompt |
     */
    public void waitForPromptToOccur() {
        FitniumWaitAPI.waitForPromptToOccur(this);
    }

    /**
     * Waits for an Confirmation box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for confirmation |
     */
    public void waitForConfirmationToOccur() {
        FitniumWaitAPI.waitForConfirmationToOccur(this);
    }

    /**
     * Wait for the provided fields to be present
     * @param fields Fields to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for fields | fields | to be present |
     */
    public void waitForFieldsToBePresent(String fields) {
        FitniumWaitAPI.waitForFieldsToBePresent(this, fields);
    }

    /**
     * Wait for the provided links to be present
     * @param links Links to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for links | links | to be present |
     */
    public void waitForLinksToBePresent(String links) {
        FitniumWaitAPI.waitForLinksToBePresent(this, links);
    }

    /**
     * Wait for the windows with window ids to be present
     * @param ids IDs to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window ids | ids | to be present |
     */
    public void waitForWindowIdsToBePresent(String ids) {
        FitniumWaitAPI.waitForWindowIdsToBePresent(this, ids);
    }

    /**
     * Wait for the windows with window names to be present
     * @param names Names to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window names | names | to be present |
     */
    public void waitForWindowNamesToBePresent(String names) {
        FitniumWaitAPI.waitForWindowNamesToBePresent(this, names);
    }

    /**
     * Wait for the windows with window titles to be present
     * @param titles Titless to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window titles | titles | to be present |
     */
    public void waitForWindowTitlesToBePresent(String titles) {
        FitniumWaitAPI.waitForWindowTitlesToBePresent(this, titles);
    }

    /**
     * Wait for provided attribute to be present in current window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | to be present |
     */
    public void waitForAttributeToBePresent(String name) {
        FitniumWaitAPI.waitForAttributeToBePresent(this, name);
    }

    /**
     * Wait for provided attribute to be present in any window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | from all windows to be present |
     */
    public void waitForAttributeFromAllWindowsToBePresent(String name) {
        FitniumWaitAPI.waitForAttributeFromAllWindowsToBePresent(this, name);
    }

    /**
     * Wait for provided body text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for body text | text | to be present |
     */
    public void waitForBodyTextToBePresent(String text) {
        FitniumWaitAPI.waitForBodyTextToBePresent(this, text);
    }

    /**
     * Wait for radio button to be checked
     * @param loc Radio button to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for radio button | name | to be checked |
     */
    public void waitForRadioButtonToBeChecked(String loc) {
        FitniumWaitAPI.waitForRadioButtonToBeChecked(this, loc);
    }

    /**
     * Wait for check box to be checked
     * @param loc Check box to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for check box | name | to be checked |
     */
    public void waitForCheckBoxToBeChecked(String loc) {
        FitniumWaitAPI.waitForCheckBoxToBeChecked(this, loc);
    }

    /**
     * Wait for provided cookie to be present
     * @param cookie Cookie to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for cookie | name | to be present |
     */
    public void waitForCookieToBePresent(String name) {
        FitniumWaitAPI.waitForCookieToBePresent(this, name);
    }

    /**
     * Wait for cursor to get to a specific position in provided element
     * @param loc Element to wait for
     * @param pos Postion to wait for 
     * @return nothing
     *
     * <br/><br/>
     * | wait for wait for cursor in element | loc | to be at position | pos |
     */
    public void waitForCursorInElementToBeAtPosition(String loc, String pos) {
        FitniumWaitAPI.waitForCursorInElementToBeAtPosition(this, loc, pos);
    }

    /**
     * Wait for given element to be editable
     * @param loc Element to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be editable |
     */
    public void waitForElementToBeEditable(String loc) {
        FitniumWaitAPI.waitForElementToBeEditable(this, loc);
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
    public void waitForElementToHaveIndex(String loc, String ind) {
        FitniumWaitAPI.waitForElementToHaveIndex(this, loc, ind);
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
    public void waitForElementToHaveHeight(String loc, String height) {
        FitniumWaitAPI.waitForElementToHaveHeight(this, loc, height);
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
    public void waitForElementToHaveWidth(String loc, String width) {
        FitniumWaitAPI.waitForElementToHaveWidth(this, loc, width);
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
    public void waitForElementToBeAtLeftPosition(String loc, String left) {
        FitniumWaitAPI.waitForElementToBeAtLeftPosition(this, loc, left);
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
    public void waitForElementToBeAtTopPosition(String loc, String top) {
        FitniumWaitAPI.waitForElementToBeAtTopPosition(this, loc, top);
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
    public void waitForEvaluationToEqual(String expression, String value) {
        FitniumWaitAPI.waitForEvaluationToEqual(this, expression, value);
    }

    /**
     * Wait for html source to equal given values
     * @param source Souce to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for html source to equal | source |
     */
    public void waitForHtmlSourceToContain(String source) {
        FitniumWaitAPI.waitForHtmlSourceToContain(this, source);
    }

    /**
     * Wait for location to be present
     * @param location Location to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for location | location | to be present |
     */
    public void waitForLocationToBePresent(String location) {
        FitniumWaitAPI.waitForLocationToBePresent(this, location);
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
    public void waitForElementToBeOrderedWithValue(String loc, String value) {
        FitniumWaitAPI.waitForElementToBeOrderedWithValue(this, loc, value);
    }

    /**
     * Wait for Listbox/combo to have provided selected id
     * @param loc Listbox/Combo element to wait for
     * @param id ID to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected id | id |
     */
    public void waitForListboxToHaveSelectedId(String loc, String id) {
        FitniumWaitAPI.waitForListboxToHaveSelectedId(this, loc, id);
    }

    /**
     * Wait for Listbox/combo to have provided selected list of ids
     * @param loc Listbox/Combo element to wait for
     * @param ids IDs to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected ids | id |
     */
    public void waitForListboxToHaveSelectedIds(String loc, String ids) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIds(this, loc, ids);
    }

    /**
     * Wait for Listbox/combo to have provided selected index
     * @param loc Listbox/Combo element to wait for
     * @param index Index to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected index | index |
     */
    public void waitForListboxToHaveSelectedIndex(String loc, String index) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIndex(this, loc, index);
    }

    /**
     * Wait for Listbox/combo to have provided selected indexes
     * @param loc Listbox/Combo element to wait for
     * @param indexes Indexes to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected indexes | indexes |
     */
    public void waitForListboxToHaveSelectedIndexes(String loc, String indexes) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIndexes(this, loc, indexes);
    }

    /**
     * Wait for Listbox/combo to have provided selected label
     * @param loc Listbox/Combo element to wait for
     * @param label Label to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected label | label |
     */
    public void waitForListboxToHaveSelectedLabel(String loc, String label) {
        FitniumWaitAPI.waitForListboxToHaveSelectedLabel(this, loc, label);
    }

    /**
     * Wait for Listbox/combo to have provided selected labels
     * @param loc Listbox/Combo element to wait for
     * @param labels Labels to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected labels | labels |
     */
    public void waitForListboxToHaveSelectedLabels(String loc, String labels) {
        FitniumWaitAPI.waitForListboxToHaveSelectedLabels(this, loc, labels);
    }

    /**
     * Wait for Listbox/combo to have provided selected value
     * @param loc Listbox/Combo element to wait for
     * @param value Value to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected value | value |
     */
    public void waitForListboxToHaveSelectedValue(String loc, String value) {
        FitniumWaitAPI.waitForListboxToHaveSelectedValue(this, loc, value);
    }

    /**
     * Wait for Listbox/combo to have provided selected values
     * @param loc Listbox/Combo element to wait for
     * @param values Values to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected values | values |
     */
    public void waitForListboxToHaveSelectedValues(String loc, String values) {
        FitniumWaitAPI.waitForListboxToHaveSelectedValues(this, loc, values);
    }

    /**
     * Wait for Listbox/combo to have provided selected options
     * @param loc Listbox/Combo element to wait for
     * @param options Options to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected options | options |
     */
    public void waitForListboxToHaveSelectedOptions(String loc, String options) {
        FitniumWaitAPI.waitForListboxToHaveSelectedOptions(this, loc, options);
    }

    /**
     * Wait for Listbox/combo to have anything selected
     * @param loc Listbox/Combo element to wait for
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have something selected  |
     */
    public void waitForListboxToHaveSomethingSelected(String loc) {
        FitniumWaitAPI.waitForListboxToHaveSomethingSelected(this, loc);
    }

    /**
     * Wait for table to occur
     * @param loc Location of table to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for table | table | to occur |
     */
    public void waitForTextInTableToOccur(String text, String loc) {
        FitniumWaitAPI.waitForTextInTableToOccur(this, text, loc);
    }

    /**
     * Wait for text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for text | text | to be present |
     */
    public void waitForTextToBePresent(String text) {
        FitniumWaitAPI.waitForTextToBePresent(this, text);
    }

    /**
     * Wait for title to be present
     * @param title Title to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for title | title | to be present |
     */
    public void waitForTitleToBePresent(String title) {
        FitniumWaitAPI.waitForTitleToBePresent(this, title);
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
    public void waitForElementWithValueToBePresent(String loc, String value) {
        FitniumWaitAPI.waitForElementWithValueToBePresent(this, loc, value);
    }

    /**
     * Wait for whether the provided frame matches the expressions
     * @param locator Frame wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether frame | frame | matches expression | expression |
     */
    public void waitForWhetherFrameMatchesExpression(String locator, String expression) {
        FitniumWaitAPI.waitForWhetherFrameMatchesExpression(this, locator, expression);
    }

    /**
     * Wait for whether the provided window matches the expressions
     * @param locator Window wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether window | window | matches expression | expression |
     */
    public void waitForWhetherWindowMatchesExpression(String locator, String expression) {
        FitniumWaitAPI.waitForWhetherWindowMatchesExpression(this, locator, expression);
    }

    /**
     * Wait for the provided xpath statement to evaluate to a number of elements
     * @param xpath Xpath statement to evaluate
     * @param count Count to wait for
     *
     * <br/><br/>
     * | wait for xpath | xpath | count to be | count |
     */
    public void waitForXpathCountToBe(String xpath, String count) {
        FitniumWaitAPI.waitForXpathCountToBe(this, xpath, count);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     *
     * <br/><br/>
     * | wait for element | locator | for | time | seconds |
     */
    public void waitForElementForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForElementForSeconds(this, loc, secs);
    }

    /**
     * Waits for an element to appear on the page
     * @param loc Locator for element
     *
     * <br/><br/>
     * | wait for element | locator | visible |
     */
    public void waitForElementToBeVisibleForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForElementToBeVisibleForSeconds(this, loc, secs);
    }

    /**
     * Waits for all buttons to be present
     * @param buttons Comma Sep list of buttons to wait for
     *
     * <br/><br/>
     * | wait for all buttons | buttons | present |
     */
    public void waitForButtonsToBePresentForSeconds(String text, String secs) {
        FitniumWaitAPI.waitForButtonsToBePresentForSeconds(this, text, secs);
    }

    /**
     * Waits for an Alert box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for alert |
     */
    public void waitForAlertToOccurForSeconds(String secs) {
        FitniumWaitAPI.waitForAlertToOccurForSeconds(this, secs);
    }

    /**
     * Waits for an Prompt box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for prompt |
     */
    public void waitForPromptToOccurForSeconds(String secs) {
        FitniumWaitAPI.waitForPromptToOccurForSeconds(this, secs);
    }

    /**
     * Waits for an Confirmation box to appaer
     * @return nothing
     *
     * <br/><br/>
     * | wait for confirmation |
     */
    public void waitForConfirmationToOccurForSeconds(String secs) {
        FitniumWaitAPI.waitForConfirmationToOccurForSeconds(this, secs);
    }

    /**
     * Wait for the provided fields to be present
     * @param fields Fields to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for fields | fields | to be present |
     */
    public void waitForFieldsToBePresentForSeconds(String fields, String secs) {
        FitniumWaitAPI.waitForFieldsToBePresentForSeconds(this, fields, secs);
    }

    /**
     * Wait for the provided links to be present
     * @param links Links to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for links | links | to be present |
     */
    public void waitForLinksToBePresentForSeconds(String links, String secs) {
        FitniumWaitAPI.waitForLinksToBePresentForSeconds(this, links, secs);
    }

    /**
     * Wait for the windows with window ids to be present
     * @param ids IDs to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window ids | ids | to be present |
     */
    public void waitForWindowIdsToBePresentForSeconds(String ids, String secs) {
        FitniumWaitAPI.waitForWindowIdsToBePresentForSeconds(this, ids, secs);
    }

    /**
     * Wait for the windows with window names to be present
     * @param names Names to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window names | names | to be present |
     */
    public void waitForWindowNamesToBePresentForSeconds(String names, String secs) {
        FitniumWaitAPI.waitForWindowNamesToBePresentForSeconds(this, names, secs);
    }

    /**
     * Wait for the windows with window titles to be present
     * @param titles Titless to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for window titles | titles | to be present |
     */
    public void waitForWindowTitlesToBePresentForSeconds(String titles, String secs) {
        FitniumWaitAPI.waitForWindowTitlesToBePresentForSeconds(this, titles, secs);
    }

    /**
     * Wait for provided attribute to be present in current window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | to be present |
     */
    public void waitForAttributeToBePresentForSeconds(String name, String secs) {
        FitniumWaitAPI.waitForAttributeToBePresentForSeconds(this, name, secs);
    }

    /**
     * Wait for provided attribute to be present in any window
     * @param name Attributes to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for attribute | name | from all windows to be present |
     */
    public void waitForAttributeFromAllWindowsToBePresentForSeconds(String name, String secs) {
        FitniumWaitAPI.waitForAttributeFromAllWindowsToBePresentForSeconds(this, name, secs);
    }

    /**
     * Wait for provided body text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for body text | text | to be present |
     */
    public void waitForBodyTextToBePresentForSeconds(String text, String secs) {
        FitniumWaitAPI.waitForBodyTextToBePresentForSeconds(this, text, secs);
    }

    /**
     * Wait for radio button to be checked
     * @param loc Radio button to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for radio button | name | to be checked |
     */
    public void waitForRadioButtonToBeCheckedForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForRadioButtonToBeCheckedForSeconds(this, loc, secs);
    }

    /**
     * Wait for check box to be checked
     * @param loc Check box to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for check box | name | to be checked |
     */
    public void waitForCheckBoxToBeCheckedForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForCheckBoxToBeCheckedForSeconds(this, loc, secs);
    }

    /**
     * Wait for provided cookie to be present
     * @param cookie Cookie to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for cookie | name | to be present |
     */
    public void waitForCookieToBePresentForSeconds(String name, String secs) {
        FitniumWaitAPI.waitForCookieToBePresentForSeconds(this, name, secs);
    }

    /**
     * Wait for cursor to get to a specific position in provided element
     * @param loc Element to wait for
     * @param pos Postion to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for wait for cursor in element | loc | to be at position | pos |
     */
    public void waitForCursorInElementToBeAtPositionForSeconds(String loc, String pos, String secs) {
        FitniumWaitAPI.waitForCursorInElementToBeAtPositionForSeconds(this, loc, pos, secs);
    }

    /**
     * Wait for given element to be editable
     * @param loc Element to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | loc | to be editable |
     */
    public void waitForElementToBeEditableForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForElementToBeEditableForSeconds(this, loc, secs);
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
    public void waitForElementToHaveIndexForSeconds(String loc, String ind, String secs) {
        FitniumWaitAPI.waitForElementToHaveIndexForSeconds(this, loc, ind, secs);
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
    public void waitForElementToHaveHeightForSeconds(String loc, String height, String secs) {
        FitniumWaitAPI.waitForElementToHaveHeightForSeconds(this, loc, height, secs);
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
    public void waitForElementToHaveWidthForSeconds(String loc, String width, String secs) {
        FitniumWaitAPI.waitForElementToHaveWidthForSeconds(this, loc, width, secs);
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
    public void waitForElementToBeAtLeftPositionForSeconds(String loc, String left, String secs) {
        FitniumWaitAPI.waitForElementToBeAtLeftPositionForSeconds(this, loc, left, secs);
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
    public void waitForElementToBeAtTopPositionForSeconds(String loc, String top, String secs) {
        FitniumWaitAPI.waitForElementToBeAtTopPositionForSeconds(this, loc, top, secs);
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
    public void waitForEvaluationToEqualForSeconds(String expression, String value, String secs) {
        FitniumWaitAPI.waitForEvaluationToEqualForSeconds(this, expression, value, secs);
    }

    /**
     * Wait for html source to equal given values
     * @param source Souce to look for
     * @return nothing
     *
     * <br/><br/>
     * | wait for html source to contain | source |
     */
    public void waitForHtmlSourceToContainForSeconds(String source, String secs) {
        FitniumWaitAPI.waitForHtmlSourceToContainForSeconds(this, source, secs);
    }

    /**
     * Wait for location to be present
     * @param location Location to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for location | location | to be present |
     */
    public void waitForLocationToBePresentForSeconds(String location, String secs) {
        FitniumWaitAPI.waitForLocationToBePresentForSeconds(this, location, secs);
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
    public void waitForElementToBeOrderedWithValueForSeconds(String loc, String value, String secs) {
        FitniumWaitAPI.waitForElementToBeOrderedWithValueForSeconds(this, loc, value, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected id
     * @param loc Listbox/Combo element to wait for
     * @param id ID to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected id | id |
     */
    public void waitForListboxToHaveSelectedIdForSeconds(String loc, String id, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIdForSeconds(this, loc, id, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected list of ids
     * @param loc Listbox/Combo element to wait for
     * @param ids IDs to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected ids | id |
     */
    public void waitForListboxToHaveSelectedIdsForSeconds(String loc, String ids, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIdsForSeconds(this, loc, ids, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected index
     * @param loc Listbox/Combo element to wait for
     * @param index Index to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected index | index |
     */
    public void waitForListboxToHaveSelectedIndexForSeconds(String loc, String index, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIndexForSeconds(this, loc, index, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected indexes
     * @param loc Listbox/Combo element to wait for
     * @param indexes Indexes to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected indexes | indexes |
     */
    public void waitForListboxToHaveSelectedIndexesForSeconds(String loc, String indexes, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedIndexesForSeconds(this, loc, indexes, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected label
     * @param loc Listbox/Combo element to wait for
     * @param label Label to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected label | label |
     */
    public void waitForListboxToHaveSelectedLabelForSeconds(String loc, String label, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedLabelForSeconds(this, loc, label, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected labels
     * @param loc Listbox/Combo element to wait for
     * @param labels Labels to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected labels | labels |
     */
    public void waitForListboxToHaveSelectedLabelsForSeconds(String loc, String labels, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedLabelsForSeconds(this, loc, labels, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected value
     * @param loc Listbox/Combo element to wait for
     * @param value Value to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected value | value |
     */
    public void waitForListboxToHaveSelectedValueForSeconds(String loc, String value, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedValueForSeconds(this, loc, value, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected values
     * @param loc Listbox/Combo element to wait for
     * @param values Values to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected values | values |
     */
    public void waitForListboxToHaveSelectedValuesForSeconds(String loc, String values, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedValuesForSeconds(this, loc, values, secs);
    }

    /**
     * Wait for Listbox/combo to have provided selected options
     * @param loc Listbox/Combo element to wait for
     * @param options Options to wait to be selected
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have selected options | options |
     */
    public void waitForListboxToHaveSelectOptionsForSeconds(String loc, String options, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSelectedOptionsForSeconds(this, loc, options, secs);
    }

    /**
     * Wait for Listbox/combo to have anything selected
     * @param loc Listbox/Combo element to wait for
     *
     * <br/><br/>
     * | wait for Listbox | Listbox | to have something selected  |
     */
    public void waitForListboxToHaveSomethingSelectedForSeconds(String loc, String secs) {
        FitniumWaitAPI.waitForListboxToHaveSomethingSelectedForSeconds(this, loc, secs);
    }

    /**
     * Wait for table to occur
     * @param loc Location of table to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for table | table | to occur |
     */
    public void waitForTextInTableToOccurForSeconds(String loc, String text, String secs) {
        FitniumWaitAPI.waitForTableToOccurForSeconds(this, text, loc, secs);
    }

    /**
     * Wait for text to be present
     * @param text Text to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for text | text | to be present |
     */
    public void waitForTextToBePresentForSeconds(String text, String secs) {
        FitniumWaitAPI.waitForTextToBePresentForSeconds(this, text, secs);
    }

    /**
     * Wait for title to be present
     * @param title Title to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for title | title | to be present |
     */
    public void waitForTitleToBePresentForSeconds(String title, String secs) {
        FitniumWaitAPI.waitForTitleToBePresentForSeconds(this, title, secs);
    }

    /**
     * Wait for element value to be present
     * @param loc Element to wait for
     * @param value Value to wait for
     * @return nothing
     *
     * <br/><br/>
     * | wait for element | element | value | value | to be present |
     */
    public void waitForElementValueToBePresentForSeconds(String loc, String value, String secs) {
        FitniumWaitAPI.waitForElementValueToBePresentForSeconds(this, loc, value, secs);
    }

    /**
     * Wait for whether the provided frame matches the expressions
     * @param locator Frame wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether frame | frame | to match expression | expression |
     */
    public void waitForWhetherFrameMatchesExpressionForSeconds(String locator, String expression, String secs) {
        FitniumWaitAPI.waitForWhetherFrameMatchesExpressionForSeconds(this, locator, expression, secs);
    }

    /**
     * Wait for whether the provided window matches the expressions
     * @param locator Window wait for
     * @param expression Expression to evaluate to true
     *
     * <br/><br/>
     * | wait for whether window | window | to match expression | expression |
     */
    public void waitForWhetherWindowMatchesExpressionForSeconds(String locator, String expression, String secs) {
        FitniumWaitAPI.waitForWhetherWindowMatchesExpressionForSeconds(this, locator, expression, secs);
    }

    /**
     * Wait for the provided xpath statement to evaluate to a number of elements
     * @param xpath Xpath statement to evaluate
     * @param count Count to wait for
     *
     * <br/><br/>
     * | wait for xpath | xpath | count to be | count |
     */
    public void waitForXpathCountToBeForSeconds(String xpath, String count, String secs) {
        FitniumWaitAPI.waitForXpathCountToBeForSeconds(this, xpath, count, secs);
    }

    /**
     * Pause processing for n seconds
     * @param time Time in milliseconds to wait
     * 
     * <br/><br/>
     * | wait for | millis | milliseconds | 
     */
    public void waitForMilliseconds(String time) {
        FitniumWaitAPI.waitForMilliseconds(this, time);
    }

    /**
     * Pause processing for n seconds
     * @param time Time in seconds to wait
     * 
     * <br/><br/>
     * | wait for | secs | seconds | 
     */
    public void waitForSeconds(String time) {
        FitniumWaitAPI.waitForSeconds(this, time);
    }

    /**
     * Wait for a page to load for n milliseconds
     * @param time Amount of time in milliseconds to wait for page
     * 
     * <br/><br/>
     * | wait for page to load for | millis | milliseconds | 
     */
    public void waitForPageToLoadForMilliseconds(String time) {
        FitniumWaitAPI.waitForPageToLoadForMilliseconds(this, time);
    }

    /**
     * Wait for a page to load for n seconds
     * @param time Amount of time in seconds to wait for page
     * 
     * <br/><br/>
     * | wait for page to load for | secs | seconds | 
     */
    public void waitForPageToLoadForSeconds(String time) {
        FitniumWaitAPI.waitForPageToLoadForSeconds(this, time);
    }

    /**
     * Wait for popup with the specified ID to appear within the specified number of seconds
     * @param id ID of popup to wait for
     * @param timeout Time in seconds to wait
     * 
     * <br/><br/>
     * | wait for popup with id | id | for | secs | seconds | 
     */
    public void waitForPopupWithIdForSeconds(String id, long timeout) {
        FitniumWaitAPI.waitForPopupWithIdForSeconds(this, id, timeout);
    }

    /**
     * Wait a specified number of seconds for a javascript condition to occur
     * @param seconds Time to wait
     * @param script Name of script to wait for
     * 
     * <br/><br/>
     * | wait | secs | seconds | for script | script | to be true | 
     */
    public void waitSecondsForScriptToBeTrue(String seconds, String script) {
        FitniumWaitAPI.waitSecondsForScriptToBeTrue(this, seconds, script);
    }

    /****************************************************************************
     * File & Directory IO
     ****************************************************************************/
    /**
     * Create a file 
     * @param file Name of file to create
     * @returns nothing
     * 
     * <br/><br/>
     * | create file named | file |
     */
    public void createFileNamed(String file) {
        FitniumFileAPI.createFileNamed(file);
    }

    /**
     * Creates a directory
     * @param dir Name of directory to create
     * @returns nothing
     * 
     * <br/><br/>
     * | create directory named | dir |
     */
    public void createDirectoryNamed(String dir) {
        FitniumFileAPI.createDirectoryNamed(dir);
    }

    /**
     * Delete a file
     * @param file Name of file to deleye
     * 
     * <br/><br/>
     * | delete file named | file |
     */
    public void deleteFileNamed(String file) {
        FitniumFileAPI.deleteFileNamed(file);
    }

    /**
     * Deletes a directory
     * @param dir Name of directory to delete
     * 
     * <br/><br/>
     * | delete directory named | file |
     */
    public void deleteDirectoryNamed(String dir) {
        FitniumFileAPI.deleteDirectoryNamed(dir);
    }

    /**
     * Copies the contents of a file to another one
     * @param from Name of file to copy from
     * @param to Name of file to create and copy to
     * 
     * <br/><br/>
     * | copy file named | from | to file named | to |
     */
    public void copyFileNamedToFileNamed(String from, String to) {
        FitniumFileAPI.copyFileNamedToFileNamed(from, to);
    }

    /**
     * Moves the contents of a file to another one, deleting the previous file
     * @param from Name of file to move from
     * @param to Name of file to create and move to
     * 
     * <br/><br/>
     * | move file named | from | to file named | to |
     */
    public void moveFileNamedToFileNamed(String from, String to) {
        FitniumFileAPI.moveFileNamedToFileNamed(from, to);
    }

    /**
     * Checks if a file exists
     * @param name Name of file to check for
     * @return True of file exists
     *
     * <br/><br/>
     * | check | file named | file | exists | true |
     */
    public boolean fileNamedExists(String name) {
        return FitniumFileAPI.fileNamedExists(name);
    }

    /**
     * Check if a directory exists
     * @param name Name of directory to check for
     * @return True if directory exists
     *
     * <br/><br/>
     * | check | directory named | dir | exists | true |
     */
    public boolean directoryNamedExists(String name) {
        return FitniumFileAPI.directoryNamedExists(name);
    }

    /****************************************************************************
     * Timing Functions
     ****************************************************************************/
    /**
     * Record the current time in milliseconds
     * Note you can use 'check' here to see the value
     * @returns nothing
     *
     * <br/><br/>
     * | current time |
     */
    public String currentTime() {
        return FitniumTimeAPI.currentTime();
    }

    /**
     * Stores the current time in milliseconds in a fitnium variable
     * As you record the time you can use FitniumVariableAPI commands
     * to check for equal, less than, greater than etc
     * @returns nothing
     *
     * <br/><br/>
     * | store current time in | name |
     */
    public void storeCurrentTimeIn(String name) {
        FitniumTimeAPI.storeCurrentTimeIn( this, name);
    }

    /**
     * Stores the difference between 2 times in a fitnium variable
     * @param time1 Time 1
     * @param time2 Time 2
     * @param var Name of variable to store difference in
     *
     * <br/><br/>
     * | store difference between | time1 | and | time2 | in | name |
     */
    public void storeDifferenceBetweenAndIn(String time1, String time2, String var) {
        FitniumTimeAPI.storeDifferenceBetweenAndIn( this, time1, time2, var);
    }
}



 