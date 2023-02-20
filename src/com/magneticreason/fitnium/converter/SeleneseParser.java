/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.magneticreason.fitnium.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author keith
 */
public class SeleneseParser {

    protected HashMap<String, String> commands = new HashMap<String, String>();

    private IWriter writer = null;
    private boolean verbose = false;

    public SeleneseParser ( IWriter writer, boolean verbose ) {
        this.writer = writer;
        this.verbose = verbose;

        if (verbose) {
            System.out.println("Loading commands table...");
        }
        this.loadCommands();
    }

    protected void parse (String from, String to, String host, String port, String browser, String url ) {
        try {
            if ( verbose ) {
                System.out.println("Loading : " + from);
                System.out.println("Creating : " + to);
            }

            File dir = new File(to);
            if ( !dir.exists() ) {
            	dir.mkdirs();
            }
            
            writePropertiesFile ( to );
            
            writeContentsFile ( from, to, host, port, browser, url );

        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
            if ( verbose )
                System.out.println("Done");
        }
    }

    protected void writePropertiesFile ( String to ) throws IOException {
	    FileWriter fw = new FileWriter(to+"/"+"properties.xml");
	    PrintWriter pw = new PrintWriter(fw);

	    pw.print("<?xml version=\"1.0\"?>");
	    pw.print("<properties>");
	    pw.print("    <Test>true</Test>");
	    pw.print("    <Edit>true</Edit>");
	    pw.print("    <Files>true</Files>");
	    pw.print("    <Properties>true</Properties>");
	    pw.print("    <RecentChanges>true</RecentChanges>");
	    pw.print("    <Refactor>true</Refactor>");
	    pw.print("    <Search>true</Search>");
	    pw.print("    <Versions>true</Versions>");
	    pw.print("    <WhereUsed>true</WhereUsed>");
	    pw.print("</properties>");

	    pw.flush();
	    pw.close();
	    fw.close();
    }

    protected void writeContentsFile ( String from, String to, String host, String port, String browser, String url  ) throws Exception {
	    FileWriter fw = new FileWriter(to+"/"+"content.txt");
	    PrintWriter pw = new PrintWriter(fw);
	
	    writer.writePageHeader(pw, host, port, browser, url );
	
	    Document doc = loadDocument(from);
	
	    if (verbose) {
	        System.out.println("Parsing table...");
	    }
	
	    parseTable(doc, pw);
	
	    writer.writePageFooter(pw );

	    pw.flush();
	    pw.close();
	    fw.close();
    }
    
    protected Document loadDocument(String from) throws Exception {
        DOMParser parser = new DOMParser();
        parser.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                System.out.println("Ignoring " + publicId + ", " + systemId);
                return new InputSource(new StringReader(""));
            }
        });

        parser.parse(from);
        return parser.getDocument();
    }

    protected void parseTable(Document doc, PrintWriter pw) throws Exception {
        NodeList htmlList = doc.getElementsByTagName("html");
        if (htmlList.getLength() != 1) {
            throw new Exception("Invalid HTML, multiple <html> tags");
        }
        Element htmlEl = (Element) htmlList.item(0);

        NodeList bodyList = htmlEl.getElementsByTagName("body");
        if (bodyList.getLength() != 1) {
            throw new Exception("Invalid HTML, multiple <body> tags");
        }
        Element bodyEl = (Element) bodyList.item(0);

        NodeList tableList = bodyEl.getElementsByTagName("table");
        if (tableList.getLength() != 1) {
            throw new Exception("Invalid HTML, <table> missing");
        }
        Element tableEl = (Element) tableList.item(0);

        NodeList tbodyList = tableEl.getElementsByTagName("tbody");
        if (tbodyList.getLength() != 1) {
            throw new Exception("Invalid HTML, <tbody> missing");
        }
        Element tbodyEl = (Element) tbodyList.item(0);

        NodeList trList = tbodyEl.getElementsByTagName("tr");
        for (int n = 0; n < trList.getLength(); n++) {
            Element trEl = (Element) trList.item(n);

            NodeList tdList = trEl.getElementsByTagName("td");
            Element command = (Element) tdList.item(0);
            Element target = (Element) tdList.item(1);
            Element value = (Element) tdList.item(2);

            String fitCmd = this.parseCommand(command.getTextContent(),
                                              target.getTextContent(),
                                              value.getTextContent());

            if (verbose) {
                System.out.println(command + " = " + fitCmd);
            }

            writer.writeCommand(pw, fitCmd);
        }
    }

    protected String parseCommand(String command, String target, String value) {

        String cmd = commands.get(stripAndWait(command));
        if (null == cmd) {
            return command + " - unknown command";
        }

        String str = cmd.replaceAll("\\!\\{target\\}", target);

        str = str.replaceAll("\\!\\{value\\}", value);

        return str;
    }

    protected String stripAndWait(String str) {
        int pos = str.indexOf("AndWait");
        if (pos >= 0) {
            return str.substring(0, pos);
        } else {
            return str;
        }
    }

    private void loadCommands() {

        commands.put("open", "| starting at URL | !{target} |");
        commands.put("setTimeout", "| set timeout to | !{target} |");
        commands.put("setSpeed", "| set speed to | !{target} |");
        commands.put("setMouseSpeed", "| set mouse speed to | !{target} |");

        commands.put("addLocationStrategy", "| add function | !{value} | to location strategy | !{target} |");
        commands.put("allowNativeXpath", "| allow native xpath |");
        commands.put("ignoreAttributesWithoutValue", "WARNING: ignoreAttributesWithoutValue - no suitable Fitnium command implemented");

        commands.put("assignId", "| assign element | !{target} | id | !{value} |");

        commands.put("captureEntirePageScreenShot", "| capture screen to file | !{target} |");

        commands.put("setBrowserLogLevel", "| set browser log level | !{target} |");
        commands.put("echo", "| write to debug | !{target} |");
        commands.put("pause", "| wait for | !{target} | milliseconds |");
        commands.put("fireEvent", "| fire event | !{value} | against element | !{target} |");

        commands.put("openWindow", "| open url | !{target} | in window and assign id | !{value} | ");
        commands.put("goBack", "| press the back button |");
        commands.put("refresh", "| press the refresh button |");
        commands.put("close", "| close the browser |");

        commands.put("deleteCookie", "| delete cookied named | !{target} |");
        commands.put("deleteAllVisibleCookies", "| delete all visible cookies |");
        commands.put("createCookie", "| create cookie named | !{target} | with value | !{value} |");

        commands.put("check", "| click checkbox | !{target} |");
        commands.put("uncheck", "| unclick checkbox | !{target} |");

        commands.put("select", "| select option | !{value} | in list | !{target} |");
        commands.put("addSelection", "| add selection option | !{value} | to list | !{target} |");
        commands.put("removeSelection", "| remove selection option | !{value} | from list | !{target} |");
        commands.put("removeAllSelections", "| remove all selections from list | !{target} |");

        commands.put("type", "| enter | !{value} | in input field | !{target} |");
        commands.put("typeKeys", "| type |!{value} | in input field | {!target} |");
        commands.put("setCursorPosition", "| set cursor at | !{value} | in element | !{target} |");
        commands.put("keyDown", "| press key | !{value} | down in element | !{target} |");
        commands.put("keyPress", "| press key sequence | !{value} | in element | !{target} |");
        commands.put("keyUp", "| release key | !{value} | up in element | !{target} |");
        commands.put("altKeyDown", "| while holding the alt key down |");
        commands.put("altKeyUp", "| release the alt key |");
        commands.put("controlKeyDown", "| while holding the control key down |");
        commands.put("controlKeyUp", "| release the control key |");
        commands.put("metaKeyDown", "| while holding the meta key down |");
        commands.put("metaKeyUp", "| release the meta key |");
        commands.put("shiftKeyDown", "| while holding the shift key down |");
        commands.put("shiftKeyUp", "| release the shift key |");

        commands.put("click", "| click element | !{target} |");
        commands.put("clickAt", "| click element | !{target} | at | !{value} | and | !{value} |");
        commands.put("doubleClick", "| double click element | !{target} |");
        commands.put("doubleClickAt", "| double click element | !{target} | at | !{value} | and | !{value} |");
        commands.put("contextMenu", "| right click on element | !{target} |");
        commands.put("contextMenuAt", "| right click on element | !{target} | at | !{value} | and | !{value} |");
        commands.put("mouseDown", "| click mouse down on element | !{target} |");
        commands.put("mouseDownAt", "| click mouse down on element | !{target} | at | !{value} | and | !{value} |");
        commands.put("mouseMove", "| move mouse on element | !{target} |");
        commands.put("mouseMoveAt", "| move mouse on element | !{target} | at | !{value} | and | !{value} |");
        commands.put("mouseOut", "| move mouse out of element | !{target} |");
        commands.put("mouseOver", "| hover mouse over element | !{target} |");
        commands.put("mouseUp", "| release mouse up on element | !{target} |");
        commands.put("mouseUpAt", "| release mouse up on element | !{target} | at | !{value} | and | !{value} |");
        commands.put("dragAndDrop", "| drag element | !{target} | horizontally | !{value} | pixels and vertically | !{value} | pixels |");
        commands.put("dragAndDropToObject", "| drag element | !{target} | to element | !{value} | ");

        commands.put("submit", "| submit form | !{target} |");
        commands.put("selectFrame", "| select frame | !{target} |");
        commands.put("selectWindow", "| select window with id | !{target} |");
        commands.put("focus", "| set focus to currently selected window |");
        commands.put("highlight", "| highlight element | !{target} |");
        commands.put("windowFocus", "| set focus to currently selected window |");
        commands.put("windowMaximize", "| maximise currently selected window |");
        commands.put("answerOnNextPrompt", "| answer on next prompt | !{target} |");
        commands.put("chooseCancelOnNextConfirmation", "| choose cancel on next confirmation |");
        commands.put("chooseOkOnNextConfirmation", "| choose ok on next confirmation |");

        commands.put("store", "WARNING: store - no suitable Fitnium command implemented");

        commands.put("storeAlert", "| store alert dialog message in | !{target} |");
        commands.put("assertAlert", "| check | is alert dialog message | !{target} | true |");
        commands.put("assertNotAlert", "| check | is alert dialog message | !{target} | false |");
        commands.put("verifyAlert", "| check | is alert dialog message | !{target} | true |");
        commands.put("verifyNotAlert", "| check | is alert dialog message | !{target} | false |");
        commands.put("waitForAlert", "| wait for alert to occur |");
        commands.put("waitForNotAlert", "WARNING: waitForNotAlert - no suitable Fitnium command implemented");

        commands.put("storeAlertPresent", "| store has alert occured in | !{target} |");
        commands.put("assertAlertPresent", "| check | has alert occurred | true |");
        commands.put("assertNotAlertPresent", "| check | has alert occurred | false |");
        commands.put("verifyAlertPresent", "| check | has alert occurred | true |");
        commands.put("verifyNotAlertPresent", "| check | has alert occurred | false |");
        commands.put("waitForAlertPresent", "| wait for alert to occur");
        commands.put("waitForNotAlertPresent", "WARNING: waitForNotAlertPresent");

        commands.put("storePrompt", "| store prompt dialog message in | !{target} |");
        commands.put("assertPrompt", "| check | is prompt dialog message | !{target} | true |");
        commands.put("assertNotPrompt", "| check | is prompt dialog message | !{target} | false |");
        commands.put("verifyPrompt", "| check | is prompt dialog message | !{target} | true |");
        commands.put("verifyNotPrompt", "| check | is prompt dialog message | !{target} | false |");
        commands.put("waitForPrompt", "| wait for prompt to occur |");
        commands.put("waitForNotPrompt", "WARNING: waitForNotPrompt - no suitable Fitnium command implemented");

        commands.put("storePromptPresent", "| store has prompt occured in | !{target} |");
        commands.put("assertPromptPresent", "| check | has prompt occurred | true |");
        commands.put("assertNotPromptPresent", "| check | has prompt occurred | false |");
        commands.put("verifyPromptPresent", "| check | has prompt occurred | true |");
        commands.put("verifyNotPromptPresent", "| check | has prompt occurred | false |");
        commands.put("waitForPromptPresent", "| wait for prompt to occur |");
        commands.put("waitForNotPromptPresent", "WARNING: waitForNotPromptPresent");

        commands.put("storeAllButtons", "| store all buttons in | !{target} |");
        commands.put("assertAllButtons", "| check | do buttons with ids exist | !{target} | true |");
        commands.put("assertNotAllButtons", "| check | do buttons with ids exist | !{target} | false |");
        commands.put("verifyAllButtons", "| check | do buttons with ids exist | !{target} | true |");
        commands.put("verifyNotAllButtons", "| check | do buttons with ids exist | !{target} | false |");
        commands.put("waitForAllButtons", "| wait for buttons | !{target} | to be present| ");
        commands.put("waitForNotAllButtons", "WARNING: waitForNotAllButtons - no suitable Fitnium command implemented");

        commands.put("storeConfirmation", "| store confirmation dialog message in | !{target} |");
        commands.put("assertConfirmation", "| check | is confirmation dialog message | !{target} | true |");
        commands.put("assertNotConfirmation", "| check | is confirmation dialog message | !{target} | false |");
        commands.put("verifyConfirmation", "| check | is confirmation dialog message | !{target} | true |");
        commands.put("verifyNotConfirmation", "| check | is confirmation dialog message | !{target} | false |");
        commands.put("waitForConfirmation", "| wait for confirmation to be present | ");
        commands.put("waitForNotConfirmation", "WARNING: waitForNotConfirmation - no suitable Fitnium command implemented");

        commands.put("storeConfirmationPresent", "| store has Confirmation occured in | !{target} |");
        commands.put("assertConfirmationPresent", "| check | has confirmation occurred | true |");
        commands.put("assertNotConfirmationPresent", "| check | has confirmation occurred | false |");
        commands.put("verifyConfirmationPresent", "| check | has confirmation occurred | true |");
        commands.put("verifyNotConfirmationPresent", "| check | has confirmation occurred | false |");
        commands.put("waitForConfirmationPresent", "| wait for confirmation to be present |");
        commands.put("waitForNotConfirmationPresent", "WARNING: waitForNotConfirmationPresent");

        commands.put("storeAllFields", "| store all fields in | !{target} |");
        commands.put("assertAllFields", "| check | do input fields with ids exist | !{target} | true |");
        commands.put("assertNotAllFields", "| check | do input fields with ids exist | !{target} | false |");
        commands.put("verifyAllFields", "| check | do input fields with ids exist | !{target} | true |");
        commands.put("verifyNotAllFields", "| check | do input fields with ids exist | !{target} | false |");
        commands.put("waitForAllFields", "WARNING: waitForAllFields - no suitable Fitnium command implemented");
        commands.put("waitForNotAllFields", "WARNING: waitForNotAllFields - no suitable Fitnium command implemented");

        commands.put("storeAllLinks", "| store all links in | !{target} |");
        commands.put("assertAllLinks", "| check | do links with ids exist | !{target} | true |");
        commands.put("assertNotAllLinks", "| check | do links with ids exist | !{target} | false |");
        commands.put("verifyAllLinks", "| check | do links with ids exist | !{target} | true |");
        commands.put("verifyNotAllLinks", "| check | do links with ids exist | !{target} | false |");
        commands.put("waitForAllLinks", "WARNING: waitForAllLinks - no suitable Fitnium command implemented");
        commands.put("waitForNotAllLinks", "WARNING: waitForNotAllLinks - no suitable Fitnium command implemented");

        commands.put("storeAllWindowIds", "| store all window ids in | !{target} |");
        commands.put("assertAllWindowIds", "| check | do windows with ids exist | !{target} | true |");
        commands.put("assertNotAllWindowIds", "| check | do windows with ids exist | !{target} | false |");
        commands.put("verifyAllWindowIds", "| check | do windows with ids exist | !{target} | true |");
        commands.put("verifyNotAllWindowIds", "| check | do windows with ids exist | !{target} | false |");
        commands.put("waitForAllWindowIds", "WARNING: waitForAllWindowIds - no suitable Fitnium command implemented");
        commands.put("waitForNotAllWindowIds", "WARNING: waitForNotAllWindowIdsv");

        commands.put("storeAllWindowNames", "| store all window names in | !{target} |");
        commands.put("assertAllWindowNames", "| check | do windows with names exist | !{target} | true |");
        commands.put("assertNotAllWindowNames", "| check | do windows with names exist | !{target} | false |");
        commands.put("verifyAllWindowNames", "| check | do windows with names exist | !{target} | true |");
        commands.put("verifyNotAllWindowNames", "| check | do windows with names exist | !{target} | false |");
        commands.put("waitForAllWindowNames", "WARNING: waitForAllWindowNames - no suitable Fitnium command implemented");
        commands.put("waitForNotAllWindowNames", "WARNING: waitForNotAllWindowNames - no suitable Fitnium command implemented");

        commands.put("storeAllWindowTitles", "| store all window titles in | !{target} |");
        commands.put("assertAllWindowTitles", "| check | do windows with titles exist | !{target} | true |");
        commands.put("assertNotAllWindowTitles", "| check | do windows with titles exist | !{target} | false |");
        commands.put("verifyAllWindowTitles", "| check | do windows with titles exist | !{target} | true |");
        commands.put("verifyNotAllWindowTitles", "| check | do windows with titles exist | !{target} | false |");
        commands.put("waitForAllWindowTitles", "WARNING: waitForAllWindowTitles - no suitable Fitnium command implemented");
        commands.put("waitForNotAllWindowTitles", "WARNING: waitForNotAllWindowTitles - no suitable Fitnium command implemented");

        commands.put("storeAttribute", "| store attribute value in | !{target} | ");
        commands.put("assertAttribute", "| check attribute value is | !{target} | true |");
        commands.put("assertNotAttribute", "| check attribute value is | !{target} | true |");
        commands.put("verifyAttribute", "| check attribute value is | !{target} | true |");
        commands.put("verifyNotAttribute", "| check attribute value is | !{target} | true |");
        commands.put("waitForAttribute", "| wait for attribute | !{target} | to be present |");
        commands.put("waitForNotAttribute", "WARNING: waitForNotAttribute - no suitable Fitnium command implemented");

        commands.put("storeAttributeFromAllWindows", "| store all window attributes in | !{target} |");
        commands.put("assertAttributeFromAllWindows", "| check | does attribute | !{target} | with name exists in any window | true |");
        commands.put("assertNotAttributeFromAllWindows", "| check | does attribute | !{target} | with name exists in any window | false |");
        commands.put("verifyAttributeFromAllWindows", "| check | does attribute | !{target} | with name exists in any window | true |");
        commands.put("verifyNotAttributeFromAllWindows", "| check | does attribute | !{target} | with name exists in any window | false |");
        commands.put("waitForAttributeFromAllWindows", "| wait for attribute | !{target} | from all windows to be present |");
        commands.put("waitForNotAttributeFromAllWindows", "WARNING: waitForNotAttributeFromAllWindows - no suitable Fitnium command implemented");

        commands.put("storeBodyText", "| store body text in | !{target} |");
        commands.put("assertBodyText", "| check | is body text | true |");
        commands.put("assertNotBodyText", "| check | is body text | false |");
        commands.put("verifyBodyText", "| check | is body text | true |");
        commands.put("verifyNotBodyText", "| check | is body text | false |");
        commands.put("waitForBodyText", "| wait for body text | !{target} | to be present |");
        commands.put("waitForNotBodyText", "WARNING: waitForBodyText - no suitable Fitnium command implemented");

        commands.put("storeChecked", "| store is radio button | !{target} | checked | in !{value} |");
        commands.put("assertChecked", "| check | is radio button | !{target} | checked | true |");
        commands.put("assertNotChecked", "| check | is radio button | !{target} | checked | false |");
        commands.put("verifyChecked", "| check | is radio button | !{target} | checked | true |");
        commands.put("verifyNotChecked", "| check | is radio button | !{target} | checked | false |");
        commands.put("waitForChecked", "| wait for radio button | !{target} | to be checked |");
        commands.put("waitForNotChecked", "WARNING: waitForNotChecked - no suitable Fitnium command implemented");

        commands.put("storeCookie", "| store value of cookie named | !{target} | in | !{value} |");
        commands.put("assertCookie", "| check | is value of cookie named | !{target} | !{value} | true |");
        commands.put("assertNotCookie", "| check | is value of cookie named | !{target} | !{value} | false |");
        commands.put("verifyCookie", "| check | is value of cookie named | !{target} | !{value} | true |");
        commands.put("verifyNotCookie", "| check | is value of cookie named | !{target} | !{value} | false |");
        commands.put("waitForCookie", "| wait for cookie | !{target} | to be present |");
        commands.put("waitForNotCookie", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeCookieByName", "| store value of cookie named | !{target} | in | !{value} |");
        commands.put("assertCookieByName", "| check | is value of cookie named | !{target} | !{value} | true |");
        commands.put("assertNotCookieByName", "| check | is value of cookie named | !{target} | !{value} | false |");
        commands.put("verifyCookieByName", "| check | is value of cookie named | !{target} | !{value} | true |");
        commands.put("verifyNotCookieByName", "| check | is value of cookie named | !{target} | !{value} | false |");
        commands.put("waitForCookieByName", "| wait for cookie | !{target} | present |");
        commands.put("waitForNotCookieByName", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeCookiePresent", "| store is cookie | !{target} |  present in| !{target} |");
        commands.put("assertCookiePresent", "| check | is cookie | !{target} | present | true |");
        commands.put("assertNotCookiePresent", "| check | is cookie | !{target} | present | false |");
        commands.put("verifyCookiePresent", "| check | is cookie | !{target} | present | true |");
        commands.put("verifyNotCookiePresent", "| check | is cookie | !{target} | present | false |");
        commands.put("waitForCookiePresent", "| wait for cookie | !{target} | present |");
        commands.put("waitForNotCookiePresent", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeCursorPosition", "| store cursor position in element | !{target} |in | ![target } |");
        commands.put("assertCursorPosition", "| check | get cursor position in element | !{target} | true |");
        commands.put("assertNotCursorPosition", "| check | get cursor position in element | !{target} | false |");
        commands.put("verifyCursorPosition", "| check | get cursor position in element | !{target} | true |");
        commands.put("verifyNotCursorPosition", "| check | get cursor position in element | !{target} | false |");
        commands.put("waitForCursorPosition", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotCursorPosition", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeEditable", "| store is input field | !{target} |editable in | !{target} | ");
        commands.put("assertEditable", "| check | is input field | !{target} | editable | true |");
        commands.put("assertNotEditable", "| check | is input field | !{target} | editable | false |");
        commands.put("verifyEditable", "| check | is input field | !{target} | editable | true |");
        commands.put("verifyNotEditable", "| check | is input field | !{target} | editable | false |");
        commands.put("waitForEditable", "| wait for element | !target} | to be editable |");
        commands.put("waitForNotEditable", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementIndex", "| store index of element | !{target} | in | !{value} |");
        commands.put("assertElementIndex", "| check | is index of element | !{target} | !{value} | true |");
        commands.put("assertNotElementIndex", "| check | is index of element | !{target} | !{value} | false |");
        commands.put("verifyElementIndex", "| check | is index of element | !{target} | !{value} | true |");
        commands.put("verifyNotElementIndex", "| check | is index of element | !{target} | !{value} | false |");
        commands.put("waitForElementIndex",  "| wait for element | !{target} | to have index | !{value} |");
        commands.put("waitForNotElementIndex", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementHeight", "| store element | !{target} | height in | !{value} |");
        commands.put("assertElementHeight", "| check | is element | !{target} | height | !{value} | true |");
        commands.put("assertNotElementHeight", "| check | is element | !{target} | height | !{value} | false |");
        commands.put("verifyElementHeight", "| check | is element | !{target} | height | !{value} | true |");
        commands.put("verifyNotElementHeight", "| check | is element | !{target} | height | !{value} | false |");
        commands.put("waitForElementHeight", "| wait for element | !{target} | to have height | !{value} |");
        commands.put("waitForNotElementHeight", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementWidth", "| store element | !{target} | width in | !{value} |");
        commands.put("assertElementWidth", "| check | is element | !{target} | width | !{value} | true |");
        commands.put("assertNotElementWidth", "| check | is element | !{target} | width | !{value} | false |");
        commands.put("verifyElementWidth", "| check | is element | !{target} | width | !{value} | true |");
        commands.put("verifyNotElementWidth", "| check | is element | !{target} | width | !{value} | false |");
        commands.put("waitForElementWidth", "| wait for element | !{target} | to have width | !{value} |");
        commands.put("waitForNotElementWidth", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementPositionLeft", "| store left position of element | !{target} | in | !{value} |");
        commands.put("assertElementPositionLeft", "| check | is left position of element | !{target} | !{value} | true |");
        commands.put("assertNotElementPositionLeft", "| check | is left position of element | !{target} | !{value} | false |");
        commands.put("verifyElementPositionLeft", "| check | is left position of element | !{target} | !{value} | true |");
        commands.put("verifyNotElementPositionLeft", "| check | is left position of element | !{target} | !{value} | false |");
        commands.put("waitForElementPositionLeft", "| wait for element | !{target} | to be at left position | !{value} |");
        commands.put("waitForNotElementPositionLeft", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementPositionTop", "| store top position of element | !{target} | in | !{value} |");
        commands.put("assertElementPositionTop", "| check | is top position of element | !{target} | !{value} | true |");
        commands.put("assertNotElementPositionTop", "| check | is top position of element | !{target} | !{value} | false |");
        commands.put("verifyElementPositionTop", "| check | is top position of element | !{target} | !{value} | true |");
        commands.put("verifyNotElementPositionTop", "| check | is top position of element | !{target} | !{value} | false |");
        commands.put("waitForElementPositionTop", "| wait for element | !{target} | to be at top position | !{value} |");
        commands.put("waitForNotElementPositionTop", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeElementPresent", "| store is element | !{target} | present in | !{value} |");
        commands.put("assertElementPresent", "| check | is element | !{target} | present | true |");
        commands.put("assertElementNotPresent", "| check | is element | !{target} | present | false |");
        commands.put("verifyElementPresent", "| check | is element | !{target} | present | true |");
        commands.put("verifyElementNotPresent", "| check | is element | !{target} | present | false |");
        commands.put("waitForElementPresent", "| wait for element | !{target} | to be present |");
        commands.put("waitForElementNotPresent", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("assertEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("assertNotEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("verifyEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("verifyNotEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForEval", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotEval", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("assertExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("assertNotExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("verifyExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("verifyNotExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForExpression", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotExpression", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeHtmlSource", "WARNING: storeHtmlSource - no suitable Fitnium command implemented");
        commands.put("assertHtmlSource", "WARNING: assertHtmlSource - no suitable Fitnium command implemented");
        commands.put("assertNotHtmlSource", "WARNING: assertNotHtmlSource - no suitable Fitnium command implemented");
        commands.put("verifyHtmlSource", "WARNING: verifyHtmlSource - no suitable Fitnium command implemented");
        commands.put("verifyNotHtmlSource", "WARNING: verifyNotHtmlSource - no suitable Fitnium command implemented");
        commands.put("waitForHtmlSource", "WARNING: waitForHtmlSource - no suitable Fitnium command implemented");
        commands.put("waitForNotHtmlSource", "WARNING: waitForNotHtmlSource - no suitable Fitnium command implemented");

        commands.put("storeLocation", "| store absolute url of current page | in | !{value} |");
        commands.put("assertLocation", "| check | is absolute url of current page | !{target} | true |");
        commands.put("assertNotLocation", "| check | is absolute url of current page | !{target} | false |");
        commands.put("verifyLocation", "| check | is absolute url of current page | !{target} | true |");
        commands.put("verifyNotLocation", "| check | is absolute url of current page | !{target} | false |");
        commands.put("waitForLocation", "| wait for location | !{target} | to be present |");
        commands.put("waitForNotLocation", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeOrdered", "WARNING: storeOrdered - no suitable Fitnium command implemented");
        commands.put("assertOrdered", "WARNING: assertOrdered - no suitable Fitnium command implemented");
        commands.put("assertNotOrdered", "WARNING: assertNotOrdered - no suitable Fitnium command implemented");
        commands.put("verifyOrdered", "WARNING: verifyOrdered - no suitable Fitnium command implemented");
        commands.put("verifyNotOrdered", "WARNING: verifyNotOrdered - no suitable Fitnium command implemented");
        commands.put("waitForOrdered", "WARNING: waitForOrdered - no suitable Fitnium command implemented");
        commands.put("waitForNotOrdered", "WARNING: waitForNotOrdered - no suitable Fitnium command implemented");

        commands.put("storeSelectedId", "| store List | !{target} | selected item id in | !{value} |");
        commands.put("assertSelectedId", "| check | is list | !{target} | selected item id | !{target} | true |");
        commands.put("assertNotSelectedId", "| check | is list | !{target} | selected item id | !{target} | false |");
        commands.put("verifySelectedId", "| check | is list | !{target} | selected item id | !{target} | true |");
        commands.put("verifyNotSelectedId", "| check | is list | !{target} | selected item id | !{target} | false |");
        commands.put("waitForSelectedId", "| wait for listbox | !{target} | to have selected id | !{value} |");
        commands.put("waitForNotSelectedId", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedIds", "| store list | !{target} | selected item ids in | !{value} |");
        commands.put("assertSelectedIds", "| check | are list | !{target} | selected item ids | !{value} | true |");
        commands.put("assertNotSelectedIds", "| check | are list | !{target} | selected item ids | !{value} | false |");
        commands.put("verifySelectedIds", "| check | are list | !{target} | selected item ids | !{value} | true |");
        commands.put("verifyNotSelectedIds", "| check | are list | !{target} | selected item ids | !{value} | false |");
        commands.put("waitForSelectedIds", "| wait for listbox | !{target} | to have selected id | !{value} |");
        commands.put("waitForNotSelectedIds", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedIndex", "| store list | !{target} | selected item index in | !{value} |");
        commands.put("assertSelectedIndex", "| check | is list| !{target} | selected item index | !{target} | true |");
        commands.put("assertNotSelectedIndex", "| check | is list| !{target} | selected item index | !{target} | false |");
        commands.put("verifySelectedIndex", "| check | is list| !{target} | selected item index | !{target} | true |");
        commands.put("verifyNotSelectedIndex", "| check | is list| !{target} | selected item index | !{target} | false |");
        commands.put("waitForSelectedIndex", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedIndex", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedIndexes", "| store ist | !{target} | selected item indexes in | !{value} |");
        commands.put("assertSelectedIndexes", "| check | are list | !{target} | selected item indexes | !{value} | true |");
        commands.put("assertNotSelectedIndexes", "| check | are list | !{target} | selected item indexes | !{value} | false |");
        commands.put("verifySelectedIndexes", "| check | are list | !{target} | selected item indexes | !{value} | true |");
        commands.put("verifyNotSelectedIndexes", "| check | are list | !{target} | selected item indexes | !{value} | false |");
        commands.put("waitForSelectedIndexes", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedIndexes", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedLabel", "| store list | !{target} | selected item label in | !{value} |");
        commands.put("assertSelectedLabel", "| check | is list | !{target} | selected item label | !{target} | true |");
        commands.put("assertNotSelectedLabel", "| check | is list | !{target} | selected item label | !{target} | false |");
        commands.put("verifySelectedLabel", "| check | is list | !{target} | selected item label | !{target} | true |");
        commands.put("verifyNotSelectedLabel", "| check | is list | !{target} | selected item label | !{target} | false |");
        commands.put("waitForSelectedLabel", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedLabel", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedLabels", "| store list | !{target} | selected item labels in | !{value} |");
        commands.put("assertSelectedLabels", "| check | are list | !{target} | selected item labels | !{value} | true |");
        commands.put("assertNotSelectedLabels", "| check | are list | !{target} | selected item labels | !{value} | false |");
        commands.put("verifySelectedLabels", "| check | are list | !{target} | selected item labels | !{value} | true |");
        commands.put("verifyNotSelectedLabels", "| check | are list | !{target} | selected item labels | !{value} | false |");
        commands.put("waitForSelectedLabels", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedLabels", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedValue", "| store list | !{target} | selected item value in | !{value} |");
        commands.put("assertSelectedValue", "| check | is list| !{target} | selected value id | !{target} | true |");
        commands.put("assertNotSelectedValue", "| check | is list| !{target} | selected value id | !{target} | false |");
        commands.put("verifySelectedValue", "| check | is list| !{target} | selected value id | !{target} | true |");
        commands.put("verifyNotSelectedValue", "| check | is list| !{target} | selected value id | !{target} | false |");
        commands.put("waitForSelectedValue", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedValue", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectedValues", "| store list | !{target} | selected item values in | !{value} |");
        commands.put("assertSelectedValues", "| check | are list | !{target} | selected item values | !{value} | true |");
        commands.put("assertNotSelectedValues", "| check | are list | !{target} | selected item values | !{value} | false |");
        commands.put("verifySelectedValues", "| check | are list | !{target} | selected item values | !{value} | true |");
        commands.put("verifyNotSelectedValues", "| check | are list | !{target} | selected item values | !{value} | false |");
        commands.put("waitForSelectedValues", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectedValues", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSelectOptions", "| store list | !{target} | selected item options in | !{value} |");
        commands.put("assertSelectOptions", "| check | are list | !{target} | selected item options | !{value} | true |");
        commands.put("assertNotSelectOptions", "| check | are list | !{target} | selected item options | !{value} | false |");
        commands.put("verifySelectOptions", "| check | are list | !{target} | selected item options | !{value} | true |");
        commands.put("verifyNotSelectOptions", "| check | are list | !{target} | selected item options | !{value} | false |");
        commands.put("waitForSelectOptions", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSelectOptions", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeSomethingSelected", "| store does list | !{target} | have selection in | in | !{value} |");
        commands.put("assertSomethingSelected", "| check | does list | !{target} | have selection | !{value} | true |");
        commands.put("assertNotSomethingSelected", "| check | does list | !{target} | have selection | !{value} | false |");
        commands.put("verifySomethingSelected", "| check | does list | !{target} | have selection | !{value} | true |");
        commands.put("verifyNotSomethingSelected", "| check | does list | !{target} | have selection | !{value} | false |");
        commands.put("waitForSomethingSelected", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotSomethingSelected", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeTable", "| store text from cell at | !{target} | and | !{target} | in table | !{target} | in | !{value} |");
        commands.put("assertTable", "| check | is text from cell at | !{target} | and | !{target} | in table | !{target} | true |");
        commands.put("assertNotTable", "| check | is text from cell at | !{target} | and | !{target} | in table | !{target} | false |");
        commands.put("verifyTable", "| check | is text from cell at | !{target} | and | !{target} | in table | !{target} | true |");
        commands.put("verifyNotTable", "| check | is text from cell at | !{target} | and | !{target} | in table | !{target} | false |");
        commands.put("waitForTable", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotTable", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeText", "| store text of element | !{target} | in | !{value} |");
        commands.put("assertText", "| check | is text of element | !{target} | !{value} | true |");
        commands.put("assertNotText", "| check | is text of element | !{target} | !{value} | false |");
        commands.put("verifyText", "| check | is text of element | !{target} | !{value} | true |");
        commands.put("verifyNotText", "| check | is text of element | !{target} | !{value} | false |");
        commands.put("waitForText", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotText", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeTextPresent", "| store is text | !{target} | present in | !{value} |");
        commands.put("assertTextPresent", "| check | is text | !{target} | present | !{value} | true |");
        commands.put("assertTextNotPresent", "| check | is text | !{target} | present | !{value} | false |");
        commands.put("verifyTextPresent", "| check | is text | !{target} | present | !{value} | true |");
        commands.put("verifyTextNotPresent", "| check | is text | !{target} | present | !{value} | false |");
        commands.put("waitForTextPresent", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForTextNotPresent", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeTitle", "| store title of current page in | !{target} |");
        commands.put("assertTitle", "| check | is title of current page | !{target} | true |");
        commands.put("assertNotTitle", "| check | is title of current page | !{target} | false |");
        commands.put("verifyTitle", "| check | is title of current page | !{target} | true |");
        commands.put("verifyNotTitle", "| check | is title of current page | !{target} | false |");
        commands.put("waitForTitle", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotTitle", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeValue", "| store value of element | !{target} | in | !{value} |");
        commands.put("assertValue", "| check | is value of element | !{target } | true |");
        commands.put("assertNotValue", "| check | is value of element | !{target } | false |");
        commands.put("verifyValue", "| check | is value of element | !{target } | true |");
        commands.put("verifyNotValue", "| check | is value of element | !{target } | false |");
        commands.put("waitForValue", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotValue", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeVisible", "| store is element | !{target} | visible in | !{value} |");
        commands.put("assertVisible", "| check | is element | !{target} | visible | true |");
        commands.put("assertNotVisible", "| check | is element | !{target} | visible | false |");
        commands.put("verifyVisible", "| check | is element | !{target} | visible | true |");
        commands.put("verifyNotVisible", "| check | is element | !{target} | visible | false |");
        commands.put("waitForVisible", "WARNING: - no suitable Fitnium command implemented");
        commands.put("waitForNotVisible", "WARNING: - no suitable Fitnium command implemented");

        commands.put("storeWhetherThisFrameMatchFrameExpression", "WARNING: storeWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("assertWhetherThisFrameMatchFrameExpression", "WARNING: assertWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("assertNotWhetherThisFrameMatchFrameExpression", "WARNING: assertNotWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("verifyWhetherThisFrameMatchFrameExpression", "WARNING: verifyWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("verifyNotWhetherThisFrameMatchFrameExpression", "WARNING: verifyNotWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("waitForWhetherThisFrameMatchFrameExpression", "WARNING: waitForWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");
        commands.put("waitForNotWhetherThisFrameMatchFrameExpression", "WARNING: waitForNotWhetherThisFrameMatchFrameExpression - no suitable Fitnium command implemented");

        commands.put("storeWhetherThisWindowMatchWindowExpression", "WARNING: storeWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("assertWhetherThisWindowMatchWindowExpression", "WARNING: assertWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("assertNotWhetherThisWindowMatchWindowExpression", "WARNING: assertNotWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("verifyWhetherThisWindowMatchWindowExpression", "WARNING: verifyWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("verifyNotWhetherThisWindowMatchWindowExpression", "WARNING: verifyNotWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("waitForWhetherThisWindowMatchWindowExpression", "WARNING: waitForWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");
        commands.put("waitForNotWhetherThisWindowMatchWindowExpression", "WARNING: waitForNotWhetherThisWindowMatchWindowExpression - no suitable Fitnium command implemented");

        commands.put("storeXpathCount", "| store number of nodes matching xpath | !{target} | in | !{value} | ");
        commands.put("assertXpathCount", "| check | is number of nodes matching xpath | !{target} | is | !{value} | true |");
        commands.put("assertNotXpathCount", "| check | is number of nodes matching xpath | !{target} | is | !{value} | false |");
        commands.put("verifyXpathCount", "| check | is number of nodes matching xpath | !{target} | is | !{value} | true |");
        commands.put("verifyNotXpathCount", "| check | is number of nodes matching xpath | !{target} | is | !{value} | false |");
        commands.put("waitForXpathCount", "WARNING: waitForXpathCount - no suitable Fitnium command implemented");
        commands.put("waitForNotXpathCount", "WARNING: waitForNotXpathCount - no suitable Fitnium command implemented");
    }

}

