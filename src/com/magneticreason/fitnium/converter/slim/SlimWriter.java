/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.magneticreason.fitnium.converter.slim;

import java.io.PrintWriter;

import com.magneticreason.fitnium.converter.Writer;

/**
 *
 * @author keith
 */
public class SlimWriter extends Writer {
    @Override
    public void writePageHeader(PrintWriter pw, String host, String port, String browser, String url ) {
        pw.println ( "!define TEST_SYSTEM {slim}");
        pw.println ( );
        pw.println ( "!path ./fitnesse.jar");
        pw.println ( "!path ./fitlibrary.jar");
        pw.println ( "!path ./bin");
        pw.println ( "!path ./lib/*.jar");
        pw.println ( );
        pw.println ( "|import|");
        pw.println ( "|fitnesse.slim.test|");
        pw.println ( "|fitnesse.fixtures|");
        pw.println ( "|com.magneticreason.fitnium|");
        pw.println ( );
        pw.println ( "| scenario | jump to test page |");
        pw.println ( "| the server is located at | "+host+" |");
        pw.println ( "| the server is on port | "+port+" |");
        pw.println ( "| using the browser | "+browser+" | start at | "+url+" |");
        pw.println ( "| check | is selenium initialised | true |");
        pw.println ( "| set speed to |100| milliseconds |");
        pw.println ( "| set timeout to |20000| milliseconds |");
        pw.println ( "| write to debug | starting test |");
        pw.println ( );
        pw.println ( );
        pw.println ( "| script | Base Fitnium Fixture |");
        pw.println ( "| jump to test page |");
    }
    
    @Override
    public void writePageFooter(PrintWriter pw ) {
        pw.println ( );
        pw.println ( "-| script | Base Fitnium Fixture |" );
        pw.println ( "| close the browser |");
 }

}
