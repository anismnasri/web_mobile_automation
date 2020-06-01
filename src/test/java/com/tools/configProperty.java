/**
 * 
 * @author Anis MNASRI
 */
package com.tools;

public enum configProperty {

	/**
     * Settings properties Mobile device
     */
    MOBILE("config_mobile.properties"),

	/**
	 * Settings properties for Web
	 */
    WEB_CONFIG("config_web.properties") ;

	private String name = "";

    /**
     * \brief     class constructor
     * \param     name File name
     */
    configProperty(String name) {
	this.name = name;
    }

    /**
     * Returns the file name corresponding to a key
     */
     @Override
     public String toString() {
	return name;
    }

}
