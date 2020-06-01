/**
 * 
 * @author Anis MNASRI
 */
package com.screens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.tools.Constants;
import com.tools.MobileTools;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class ScreenMobile extends AbstractScreenMobile {
	
	static Logger log = LogManager.getLogger(ScreenMobile.class);

	public ScreenMobile(AndroidDriver<WebElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean start() {
		// you can add your conditions here to start this screen
		// now there is any check to do here
		// return is default true
		return true;
	}

	@Override
	public void runGoogleApp() {
		try {
			Activity activity = new Activity(Constants.GOOGLE_CHROME_APK,
					Constants.GOOGLE_CHROME_ACTIVITY);
			driver.startActivity(activity);
		} catch(Exception e) {
			log.error(e);
		}
		
	}

	@Override
	public void openUrl(String url) {
		driver.findElementById(Constants.URL_BAR).sendKeys(url);

	}

}
