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
public interface IWriter {
    void writePageHeader(PrintWriter pw, String host, String port, String browser, String url  );
    void writeCommand(PrintWriter pw, String fitCmd);
    void writePageFooter(PrintWriter pw );
}
