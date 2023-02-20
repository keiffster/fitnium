/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.magneticreason.fitnium.converter.fit;

import java.io.PrintWriter;

import com.magneticreason.fitnium.converter.Writer;

/**
 *
 * @author keith
 */
public class FitWriter extends Writer {

    @Override
    public void writePageHeader(PrintWriter pw, String host, String port, String browser, String url ) {
        pw.println("!|com.magneticreason.fitnium.BaseFitniumFixture|" );
        pw.println ( );
        pw.println ( "!path ./fitnesse.jar");
        pw.println ( "!path ./fitlibrary.jar");
        pw.println ( "!path ./bin");
        pw.println ( "!path ./lib/*.jar");
        pw.println();
        pw.println("Set up the browser and connect to selenium" );
        pw.println();
        pw.println ( "| the server is located at | "+host+" |");
        pw.println ( "| the server is on port | "+port+" |");
        pw.println ( "| using the browser | "+browser+" | start at | "+url+" |");
        pw.println ( "| check | is selenium initialised | true |");
        pw.println ( "| set speed to |100| milliseconds |");
        pw.println ( "| set timeout to |20000| milliseconds |");
        pw.println ( "| write to debug | starting test |");
        pw.println();
    }

    @Override
    public void writePageFooter(PrintWriter pw ) {
        pw.println();
        pw.println("| close the browser |");
        pw.println();
    }
}
