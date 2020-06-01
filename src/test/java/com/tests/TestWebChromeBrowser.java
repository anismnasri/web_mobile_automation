/**
 * 
 * @author Anis MNASRI
 */
package com.tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.data.FromYAMLData;
import com.data.SetGetData;
import com.screens.AbstractScreenWeb;
import com.screens.ScreenWeb;
import com.tools.WebTools;

public class TestWebChromeBrowser {

	private WebDriver driver;

	/**
     * This methods will be called at the start of test class execution.
     * All the setup and configuration actions should be performed in this
     * function.
     */
	@BeforeMethod(alwaysRun = true)
    public void initAutomation() throws IOException, InterruptedException {
		// init driver for browser
    	driver = WebTools.initWebDriver();
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
     * This method is a core of selenium test
     * \c precondition : BeforeClass run correctly
     * \c expected : running all steps in the test
     * @throws Exception
     */
    @Test(dataProvider = "Datasets", groups = {"functional"}, enabled = true)
    public void testMethod(FromYAMLData ymlData) throws Exception {
    	Logger log = LogManager.getLogger(TestWebChromeBrowser.class);
    	// set data from YAML
    	String url = ymlData.getURL();
    	log.info("Start test ...");
    	AbstractScreenWeb screen = new ScreenWeb(driver);
    	screen.start();
    	screen.openUrl(url);
    	Thread.sleep(2000);
    }

    /**
     * This method will be called at the end of test class execution.
     * All the cleaned exit actions should be performed in this function.
     */
    @AfterMethod(alwaysRun = true)
    public void quit() {
    	WebTools.quitDrivers();
    }

}
