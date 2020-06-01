/**
 * 
 * @author Anis MNASRI
 */
package com.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.common.interfaces.ToStart;


public abstract class AbstractScreenWeb implements ToStart {

	/**
	 * Selenium WebDriver
	 */
	public WebDriver driver;

	/**
	 * \brief class constructor \param driver Appium Android driver
	 * 
	 * @return
	 */
	public AbstractScreenWeb(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Load a page
	 * @param driver 
	 */
	 public void loadPage() {
		 PageFactory.initElements(driver, this);
	 }

	 /**
	  * open url
	  */
	 public abstract void openUrl(String url);
}
