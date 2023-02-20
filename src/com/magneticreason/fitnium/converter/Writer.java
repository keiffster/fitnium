/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.magneticreason.fitnium.converter;

import java.io.PrintWriter;

/**
 *
 * @author keith
 */
public abstract class Writer implements IWriter {

    @Override
    public abstract void writePageHeader(PrintWriter pw, String host, String port, String browser, String url  );
    @Override
    public abstract void writePageFooter(PrintWriter pw );

    @Override
    public void writeCommand(PrintWriter pw, String fitCmd) {
        pw.println(fitCmd);
    }
    
}
