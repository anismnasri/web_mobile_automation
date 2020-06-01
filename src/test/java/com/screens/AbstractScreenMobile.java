/**
 * 
 * @author Anis MNASRI
 */
package com.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.common.interfaces.ToStart;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class AbstractScreenMobile implements ToStart {

	/**
	 * Appium Android driver
	 */
	public AndroidDriver<WebElement> driver;

	/**
	 * \brief class constructor \param driver Appium Android driver
	 * 
	 * @return
	 */
	public AbstractScreenMobile(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}

	/**
	 * Load a page
	 */
	public void loadPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Abstract method void to run google application
	 */
	public abstract void runGoogleApp();

	/**
	 * Abstract method to open the URL
	 * @param url
	 */
	public abstract void openUrl(String url);

}
