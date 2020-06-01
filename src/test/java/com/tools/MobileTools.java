/**
 * 
 * @author Anis Mnasri
 * @date 2020
 * copyright
 * \brief This class is to collect all repetitive method used in project
 * 		is a standard to give more visibility to the code
 * 
 */

package com.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileTools {

	static Logger log = LogManager.getLogger(MobileTools.class);

	private static AndroidDriver<WebElement> mobileDriver;
	private static Properties prop = new Properties();
	private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static URL serverUrl;
    private static String APPIUM_PORT;
    private static AndroidDriver<WebElement> driver;
    
    
    /**
     * Device name
     */
    public static String DEVICE_NAME;

    /**
     * Application package name
     */
    public static String APP_PKG;

    /**
     * Application activity
     */
    public static String APP_ACTIVITY;

    /**
     * Platform Name
     */
    public static String PLATFORM_NAME;

    /**
     * New command time out
     */
    public static String NEW_COMMAND_TIMEOUT;

    /**
     * ADB port
     */
    public static String ADB_PORT;

	/**
	 *
	 * Start mobile Appium server
	 * @throws InterruptedException 
	 */
	public static void startAppiumServer(int port) throws InterruptedException {

		AppiumControlServer appiumsc = new AppiumControlServer();
		if (!appiumsc.checkIfServerIsRunnning(port)) {
			appiumsc.startServer();
		} else {
			log.error("Appium server is occupied");
		}

	}

    /**
     * Quit all drivers running
     */
	public static void quitDrivers() {
		if (mobileDriver != null) {
            try {
            	mobileDriver.quit();
            } catch (NoSuchSessionException e) {
            	log.error(e);
            }
            mobileDriver = null;
        }
	}

	/**
	 * Init Driver to run any test
	 * @return Driver
	 * @throws MalformedURLException
	 */
	public static AndroidDriver<WebElement>
	initMobileDriver() throws MalformedURLException {

		try {
			if (driver == null) {
				MobileTools.loadConfigPropAndSetCapabilities(configProperty.MOBILE.toString());
				serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
		        driver = new AndroidDriver<WebElement>(serverUrl, capabilities);
		        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			}
			return driver;
		} catch (Exception e) {
            log.error(e);
            quitDrivers();
            return null;
        }
	}

    /**
     * Load configuration properties and set capabilities
     * \param propertyConf File name containing properties
     */
    public static void loadConfigPropAndSetCapabilities(String propertyconf)
		throws IOException {

        loadPropertyConf(propertyconf);
        setCapabilities();

	}

	/**
     * Set desired capabilities from configuration.
     */
    public static void setCapabilities() {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, MobileTools.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobileTools.PLATFORM_NAME);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, MobileTools.APP_PKG);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, MobileTools.APP_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
        		MobileTools.NEW_COMMAND_TIMEOUT);
        capabilities.setCapability(AndroidMobileCapabilityType.ADB_PORT, MobileTools.ADB_PORT);
    }

	/**
     * Load configuration
     * \param propertyFileName File containing all the configuration
     * parameters.
     */
    public static void loadPropertyConf(String propertyconf)
		throws IOException {

        prop.load(ClassLoader.getSystemResourceAsStream(propertyconf));
        DEVICE_NAME = prop.getProperty("device.name");
        PLATFORM_NAME = prop.getProperty("platform.name");
        APP_PKG = prop.getProperty("base.pkg");
        APP_ACTIVITY = prop.getProperty("application.activity");
        NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
        APPIUM_PORT = prop.getProperty("appium.server.port");
        ADB_PORT = prop.getProperty("adb.port");
	}
}
