/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.magneticreason.fitnium.test;

/**
 *
 * @author keith
 */
public class SecureLogin {
    @SuppressWarnings("unused")
    private String userName;
    @SuppressWarnings("unused")
	private String password;

    public void setUserName ( String name ) {
        this.userName = name;
    }

    public void setPassword ( String word ) {
        this.password = word;
    }

    public String secureToken () {
        return String.valueOf( System.currentTimeMillis() );
    }
}
