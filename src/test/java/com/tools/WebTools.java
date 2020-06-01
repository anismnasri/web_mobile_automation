/**
 * 
 * @author Anis MNASRI
 */
package com.tools;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebTools {

	static Logger log = LogManager.getLogger(WebTools.class);

	private static WebDriver driver;
	private static Properties propertyWeb = new Properties();
	private static Properties browserProp = new Properties();
	private static DesiredCapabilities capabilitiesWeb = new DesiredCapabilities();

	/**
	 * web browser name
	 */
	public static String WEB_BROWSER;

	/**
	 * web browser version
	 */
	public static String BROWSER_VERSION;

	/**
	 * os desktop Id
	 */
	public static String OS_ID;

	/**
	 * os desktop version
	 */
	public static String OS_VERSION;

	/**
	 * Initialize WebDriver For testing
	 * @return
	 * @throws MalformedURLException
	 */
	public static WebDriver initWebDriver() throws MalformedURLException {
		try {
			if (driver == null) {
				WebTools.loadConfigPropAndSetCapabilities(configProperty.WEB_CONFIG.toString());
				driver = WebTools.checkBrowser();
				Thread.sleep(1000);
				driver.navigate().refresh();
				driver.manage().deleteAllCookies();
		    	driver.manage().window().maximize();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			quitDrivers();
			return null;
		}
		return driver;

	}

	/**
	 * check which browser used
	 * @return WebDriver
	 */
	private static WebDriver checkBrowser() {

		try {
			browserProp.load(ClassLoader.getSystemResourceAsStream(configProperty.WEB_CONFIG.toString()));
			String config = browserProp.getProperty("web.browser");
			if (config.equalsIgnoreCase("Firefox")) {
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				System.setProperty("webdriver.gecko.driver", getPathProp("Firefox"));
				driver = new FirefoxDriver();
				// driver = new FirefoxDriver();
			} else if (config.equalsIgnoreCase("Chrome")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				System.setProperty("webdriver.chrome.driver", getPathProp("Chrome"));
				driver = new ChromeDriver();
				// driver = new ChromeDriver();
			} else {
				String _msg = "Cannot setProperty for given Browser Name, Please check your config";
				log.error(_msg);
				return null;
			}
		} catch (Exception e) {
			log.error(e);
			return null;
		}
		return driver;
	}

	/**
	 * get absolute path
	 * @param string
	 * @return
	 */
	private static String getPathProp(String string) {

		return (System.getProperty("user.dir") + "\\src\\test\\resources\\apps\\") + string + "driver.exe";

	}

	/**
	 * load and set properties
	 * @param string
	 * @throws IOException
	 */
	private static void loadConfigPropAndSetCapabilities(String string) throws IOException {

		// load file
		propertyWeb.load(ClassLoader.getSystemResourceAsStream(string));
		// set properties
		WEB_BROWSER = propertyWeb.getProperty("web.browser");
		BROWSER_VERSION = propertyWeb.getProperty("web.browser.version");
		capabilitiesWeb.setCapability(CapabilityType.BROWSER_NAME, WebTools.WEB_BROWSER);
		capabilitiesWeb.setCapability(CapabilityType.BROWSER_VERSION, WebTools.BROWSER_VERSION);
		capabilitiesWeb.setCapability("--headless", true);

	}
	/**
	 * Quit running driver
	 */
	public static void quitDrivers() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (NoSuchSessionException e) {
				log.error("no driver running, please check manually");
			}
			driver = null;
		}

	}

}
