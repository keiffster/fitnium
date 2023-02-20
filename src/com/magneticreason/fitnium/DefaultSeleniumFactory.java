/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magneticreason.fitnium;

import com.thoughtworks.selenium.*;

/**
 *
 * @author keith
 */
public class DefaultSeleniumFactory implements ISeleniumFactory {

    public Selenium createSeleniumInstance(String server, int port, String browser, String baseURL) {
        return new
        DefaultSelenium(server,
                port,
                browser,
                baseURL);
    }
}
