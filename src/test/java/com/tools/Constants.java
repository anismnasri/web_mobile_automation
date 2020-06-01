/**
 * 
 * @author Anis Mnasri
 * @date 2020
 * copyright
 * \brief This class is to collect all repetitive constants used in project
 * 		is a standard to give more visibility to the code
 * 		This Class can be split between two specific classes one for mobile
 * 		and next one for web
 * 
 */

package com.tools;

public class Constants {

	private Constants() {
		// this prevents even the native class from calling this ctor as well :
		throw new AssertionError();
	}

	/**
	 * Apk Package google chrome
	 *
	 */
	public static final String GOOGLE_CHROME_APK = "com.android.chrome";

	/**
	 * Apk Activity
	 */
	public static final String GOOGLE_CHROME_ACTIVITY = "com.google.android.apps.chrome.Main";

	/**
	 * Chrome application url bar verion of application is Chrome 81.0.4044.138
	 */
	public static final String URL_BAR = "com.android.chrome:id/url_bar";
}
