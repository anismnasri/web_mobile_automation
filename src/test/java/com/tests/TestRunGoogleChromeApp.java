/**
 * 
 * @author Anis MNASRI
 */
package com.tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.data.FromYAMLData;
import com.data.SetGetData;
import com.screens.AbstractScreenMobile;
import com.screens.ScreenMobile;
import com.tools.MobileTools;

import io.appium.java_client.android.AndroidDriver;

public class TestRunGoogleChromeApp {

	private AndroidDriver<WebElement> driver;

	/**
     * This methods will be called at the start of test class execution.
     * All the setup and configuration actions should be performed in this
     * function.
     */
	@BeforeMethod(alwaysRun = true)
    public void initAutomation() throws IOException, InterruptedException {
    	// init tablette driver
		MobileTools.startAppiumServer(4725);
    	driver = MobileTools.initMobileDriver();
    }

	/**
	 * Method to load data from YAML data
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "Datasets")
    public Object[][] getDataFromYaml() throws Exception {
    	return SetGetData.getDataSets("DataProject");
    }

    /**
     * This method is a core of appium test
     * \c precondition : BeforeClass run correctly
     * \c expected : running all steps in the test
     * @throws Exception
     */
    @Test(dataProvider = "Datasets", groups = {"functional"}, enabled = true)
    public void testMethod(FromYAMLData ymlData) throws Exception {
    	Logger log = LogManager.getLogger(TestRunGoogleChromeApp.class);
    	// set data from YAML
    	String url = ymlData.getURL();
    	log.info("Start test ...");
    	// the inheritance screen is best pratice if you want use differente screen
    	// without changing test
    	AbstractScreenMobile screenMob = new ScreenMobile(driver);
    	screenMob.runGoogleApp();
    	screenMob.openUrl(url);

    	
    }

    /**
     * This method will be called at the end of test class execution.
     * All the cleaned exit actions should be performed in this function.
     */
    @AfterMethod(alwaysRun = true)
    public void quit() {
    	MobileTools.quitDrivers();
    }

}
