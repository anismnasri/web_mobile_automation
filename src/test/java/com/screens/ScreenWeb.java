/**
 * 
 * @author Anis MNASRI
 */
package com.screens;

import org.openqa.selenium.WebDriver;

public class ScreenWeb extends AbstractScreenWeb {

	//private static WebDriver driver;

	public ScreenWeb(WebDriver driver) {
		super(driver);
		loadPage();
	}

	@Override
	public boolean start() {
		// users can add some conditions to start testing
		return true;
	}

	@Override
	public void openUrl(String url) {
		driver.get(url);
	}

}
